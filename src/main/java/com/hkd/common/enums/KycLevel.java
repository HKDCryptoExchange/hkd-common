package com.hkd.common.enums;

import lombok.Getter;

/**
 * KYC等级枚举
 *
 * @author HKD Team
 */
@Getter
public enum KycLevel {

    /**
     * L0 - 未认证（仅邮箱/手机号）
     */
    L0(0, "Unverified", 0L, "Only email/phone registered"),

    /**
     * L1 - 基础认证（姓名+身份证）
     */
    L1(1, "Basic", 10000L, "Name + ID card"),

    /**
     * L2 - 高级认证（人脸识别+地址证明）
     */
    L2(2, "Advanced", 100000L, "Face recognition + Address proof"),

    /**
     * L3 - 专业认证（视频认证+资产证明）
     */
    L3(3, "Professional", Long.MAX_VALUE, "Video verification + Asset proof");

    /**
     * 等级代码
     */
    private final Integer level;

    /**
     * 等级名称
     */
    private final String name;

    /**
     * 日提现限额（USDT）
     */
    private final Long dailyWithdrawLimit;

    /**
     * 描述
     */
    private final String description;

    KycLevel(Integer level, String name, Long dailyWithdrawLimit, String description) {
        this.level = level;
        this.name = name;
        this.dailyWithdrawLimit = dailyWithdrawLimit;
        this.description = description;
    }

    public static KycLevel fromLevel(Integer level) {
        for (KycLevel kycLevel : values()) {
            if (kycLevel.getLevel().equals(level)) {
                return kycLevel;
            }
        }
        throw new IllegalArgumentException("Invalid KYC level: " + level);
    }
}
