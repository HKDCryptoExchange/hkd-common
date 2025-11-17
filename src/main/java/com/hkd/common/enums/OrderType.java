package com.hkd.common.enums;

import lombok.Getter;

/**
 * 订单类型枚举
 *
 * @author HKD Team
 */
@Getter
public enum OrderType {

    /**
     * 市价单
     */
    MARKET(1, "Market Order"),

    /**
     * 限价单
     */
    LIMIT(2, "Limit Order"),

    /**
     * 止损单
     */
    STOP_LOSS(3, "Stop Loss"),

    /**
     * 止盈单
     */
    TAKE_PROFIT(4, "Take Profit"),

    /**
     * 止损限价单
     */
    STOP_LOSS_LIMIT(5, "Stop Loss Limit"),

    /**
     * 止盈限价单
     */
    TAKE_PROFIT_LIMIT(6, "Take Profit Limit"),

    /**
     * 跟踪止损单
     */
    TRAILING_STOP(7, "Trailing Stop"),

    /**
     * 冰山委托
     */
    ICEBERG(8, "Iceberg Order"),

    /**
     * 时间加权平均价格
     */
    TWAP(9, "TWAP Order"),

    /**
     * 成交量加权平均价格
     */
    VWAP(10, "VWAP Order"),

    /**
     * 只做Maker
     */
    POST_ONLY(11, "Post Only");

    private final Integer code;
    private final String description;

    OrderType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static OrderType fromCode(Integer code) {
        for (OrderType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid OrderType code: " + code);
    }
}
