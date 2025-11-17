package com.hkd.common.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author HKD Team
 */
@Getter
public enum OrderStatus {

    /**
     * 待成交
     */
    PENDING(1, "Pending"),

    /**
     * 部分成交
     */
    PARTIAL_FILLED(2, "Partial Filled"),

    /**
     * 完全成交
     */
    FILLED(3, "Filled"),

    /**
     * 已取消
     */
    CANCELLED(4, "Cancelled"),

    /**
     * 已拒绝
     */
    REJECTED(5, "Rejected"),

    /**
     * 已过期
     */
    EXPIRED(6, "Expired");

    private final Integer code;
    private final String description;

    OrderStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static OrderStatus fromCode(Integer code) {
        for (OrderStatus status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code: " + code);
    }

    /**
     * 是否为终态
     */
    public boolean isFinalState() {
        return this == FILLED || this == CANCELLED || this == REJECTED || this == EXPIRED;
    }
}
