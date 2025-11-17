package com.hkd.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式ID生成器 (Snowflake算法)
 *
 * 64位ID结构:
 * 1位符号位 + 41位时间戳 + 10位机器ID + 12位序列号
 *
 * - 符号位: 0 (固定)
 * - 时间戳: 41位,可用69年 (2^41 / (1000*60*60*24*365) ≈ 69)
 * - 机器ID: 10位,支持1024个节点
 * - 序列号: 12位,每毫秒可生成4096个ID
 *
 * @author HKD Team
 */
@Slf4j
public class IdGenerator {

    /**
     * 起始时间戳 (2024-01-01 00:00:00 UTC)
     */
    private static final long EPOCH = 1704067200000L;

    /**
     * 机器ID位数
     */
    private static final long WORKER_ID_BITS = 10L;

    /**
     * 序列号位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 最大机器ID (1024-1)
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 最大序列号 (4096-1)
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    /**
     * 机器ID左移位数
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 时间戳左移位数
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 机器ID
     */
    private final long workerId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 单例实例（默认workerId=0）
     */
    private static volatile IdGenerator instance;

    /**
     * 构造函数
     */
    public IdGenerator(long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("Worker ID must be between 0 and %d", MAX_WORKER_ID));
        }
        this.workerId = workerId;
        log.info("IdGenerator initialized with workerId: {}", workerId);
    }

    /**
     * 获取单例实例
     */
    public static IdGenerator getInstance() {
        if (instance == null) {
            synchronized (IdGenerator.class) {
                if (instance == null) {
                    // 默认使用workerId=0,生产环境应该从配置中获取
                    instance = new IdGenerator(0);
                }
            }
        }
        return instance;
    }

    /**
     * 获取单例实例（指定workerId）
     */
    public static IdGenerator getInstance(long workerId) {
        if (instance == null) {
            synchronized (IdGenerator.class) {
                if (instance == null) {
                    instance = new IdGenerator(workerId);
                }
            }
        }
        return instance;
    }

    /**
     * 生成下一个ID
     */
    public synchronized long nextId() {
        long timestamp = currentTimeMillis();

        // 时钟回拨检测
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", offset));
        }

        // 同一毫秒内
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 序列号溢出,等待下一毫秒
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            // 新的毫秒,重置序列号
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // 组装ID
        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    /**
     * 生成字符串ID
     */
    public String nextIdStr() {
        return String.valueOf(nextId());
    }

    /**
     * 等待下一毫秒
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     */
    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 解析ID中的时间戳
     */
    public static long parseTimestamp(long id) {
        return (id >> TIMESTAMP_SHIFT) + EPOCH;
    }

    /**
     * 解析ID中的机器ID
     */
    public static long parseWorkerId(long id) {
        return (id >> WORKER_ID_SHIFT) & MAX_WORKER_ID;
    }

    /**
     * 解析ID中的序列号
     */
    public static long parseSequence(long id) {
        return id & MAX_SEQUENCE;
    }
}
