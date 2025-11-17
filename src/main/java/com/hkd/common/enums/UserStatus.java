package com.hkd.common.enums;

import lombok.Getter;

/**
 * 用户状态枚举
 *
 * @author HKD Team
 */
@Getter
public enum UserStatus {

    /**
     * 正常
     */
    ACTIVE(1, "Active"),

    /**
     * 禁用
     */
    DISABLED(2, "Disabled"),

    /**
     * 锁定
     */
    LOCKED(3, "Locked"),

    /**
     * 待激活
     */
    PENDING(4, "Pending"),

    /**
     * 已删除
     */
    DELETED(9, "Deleted");

    private final Integer code;
    private final String description;

    UserStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserStatus fromCode(Integer code) {
        for (UserStatus status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid UserStatus code: " + code);
    }
}
