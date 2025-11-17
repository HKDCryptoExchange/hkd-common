package com.hkd.common.constant;

/**
 * 通用常量
 *
 * @author HKD Team
 */
public class Constants {

    // ========== HTTP相关 ==========
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_TOKEN = "X-Access-Token";
    public static final String HEADER_REFRESH_TOKEN = "X-Refresh-Token";
    public static final String HEADER_USER_ID = "X-User-Id";
    public static final String HEADER_REQUEST_ID = "X-Request-Id";
    public static final String BEARER_PREFIX = "Bearer ";

    // ========== 缓存Key前缀 ==========
    public static final String CACHE_PREFIX = "hkd:";
    public static final String CACHE_USER_PREFIX = CACHE_PREFIX + "user:";
    public static final String CACHE_TOKEN_PREFIX = CACHE_PREFIX + "token:";
    public static final String CACHE_SESSION_PREFIX = CACHE_PREFIX + "session:";
    public static final String CACHE_KYC_PREFIX = CACHE_PREFIX + "kyc:";
    public static final String CACHE_WALLET_PREFIX = CACHE_PREFIX + "wallet:";
    public static final String CACHE_ACCOUNT_PREFIX = CACHE_PREFIX + "account:";
    public static final String CACHE_ORDER_PREFIX = CACHE_PREFIX + "order:";
    public static final String CACHE_MARKET_PREFIX = CACHE_PREFIX + "market:";

    // ========== Redis Lock前缀 ==========
    public static final String LOCK_PREFIX = "lock:";
    public static final String LOCK_USER_PREFIX = LOCK_PREFIX + "user:";
    public static final String LOCK_WALLET_PREFIX = LOCK_PREFIX + "wallet:";
    public static final String LOCK_ORDER_PREFIX = LOCK_PREFIX + "order:";
    public static final String LOCK_WITHDRAW_PREFIX = LOCK_PREFIX + "withdraw:";

    // ========== 缓存过期时间（秒） ==========
    public static final long CACHE_EXPIRE_SHORT = 300L;          // 5分钟
    public static final long CACHE_EXPIRE_MEDIUM = 1800L;        // 30分钟
    public static final long CACHE_EXPIRE_LONG = 3600L;          // 1小时
    public static final long CACHE_EXPIRE_DAY = 86400L;          // 1天
    public static final long CACHE_EXPIRE_WEEK = 604800L;        // 7天

    // ========== Token过期时间 ==========
    public static final long ACCESS_TOKEN_EXPIRE = 3600L;        // 1小时
    public static final long REFRESH_TOKEN_EXPIRE = 604800L;     // 7天
    public static final long MFA_CODE_EXPIRE = 300L;             // 5分钟

    // ========== 分页默认值 ==========
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;

    // ========== 密码相关 ==========
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 64;
    public static final int BCRYPT_STRENGTH = 12;

    // ========== KYC等级 ==========
    public static final int KYC_LEVEL_0 = 0;  // 未认证
    public static final int KYC_LEVEL_1 = 1;  // 基础认证
    public static final int KYC_LEVEL_2 = 2;  // 高级认证
    public static final int KYC_LEVEL_3 = 3;  // 专业认证

    // ========== 资产相关 ==========
    public static final String DEFAULT_CURRENCY = "USDT";
    public static final int ASSET_DECIMAL_PLACES = 8;
    public static final String ASSET_FREEZE_REASON_WITHDRAW = "WITHDRAW";
    public static final String ASSET_FREEZE_REASON_ORDER = "ORDER";

    // ========== 订单相关 ==========
    public static final int ORDER_PRICE_PRECISION = 8;
    public static final int ORDER_QUANTITY_PRECISION = 8;

    // ========== MQ Topic ==========
    public static final String TOPIC_USER_REGISTER = "user.register";
    public static final String TOPIC_KYC_SUBMIT = "kyc.submit";
    public static final String TOPIC_DEPOSIT_CONFIRMED = "deposit.confirmed";
    public static final String TOPIC_WITHDRAW_SUBMIT = "withdraw.submit";
    public static final String TOPIC_ORDER_CREATED = "order.created";
    public static final String TOPIC_ORDER_MATCHED = "order.matched";
    public static final String TOPIC_TRADE_EXECUTED = "trade.executed";

    // ========== 系统相关 ==========
    public static final String SYSTEM_USER = "SYSTEM";
    public static final String DEFAULT_LOCALE = "en_US";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Constants() {
        // 私有构造函数,防止实例化
    }
}
