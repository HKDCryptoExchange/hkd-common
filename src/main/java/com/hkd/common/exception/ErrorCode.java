package com.hkd.common.exception;

import lombok.Getter;

/**
 * 错误码枚举
 *
 * @author HKD Team
 */
@Getter
public enum ErrorCode {

    // ========== 通用错误码 (1000-1999) ==========
    SUCCESS(200, "Success"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    PARAM_INVALID(1001, "Invalid Parameter"),
    PARAM_MISSING(1002, "Missing Required Parameter"),
    PARAM_TYPE_ERROR(1003, "Parameter Type Error"),
    VALIDATION_ERROR(1004, "Validation Error"),

    // ========== 用户相关错误码 (2000-2999) ==========
    USER_NOT_FOUND(2001, "User Not Found"),
    USER_ALREADY_EXISTS(2002, "User Already Exists"),
    USER_DISABLED(2003, "User Account Disabled"),
    USER_LOCKED(2004, "User Account Locked"),
    PASSWORD_ERROR(2005, "Incorrect Password"),
    PASSWORD_TOO_WEAK(2006, "Password Too Weak"),
    EMAIL_ALREADY_EXISTS(2007, "Email Already Exists"),
    PHONE_ALREADY_EXISTS(2008, "Phone Already Exists"),

    // ========== 认证相关错误码 (3000-3999) ==========
    TOKEN_INVALID(3001, "Invalid Token"),
    TOKEN_EXPIRED(3002, "Token Expired"),
    TOKEN_MISSING(3003, "Missing Token"),
    REFRESH_TOKEN_INVALID(3004, "Invalid Refresh Token"),
    MFA_REQUIRED(3005, "MFA Required"),
    MFA_CODE_INVALID(3006, "Invalid MFA Code"),
    SESSION_EXPIRED(3007, "Session Expired"),

    // ========== KYC相关错误码 (4000-4999) ==========
    KYC_NOT_VERIFIED(4001, "KYC Not Verified"),
    KYC_LEVEL_INSUFFICIENT(4002, "KYC Level Insufficient"),
    KYC_ALREADY_SUBMITTED(4003, "KYC Already Submitted"),
    KYC_UNDER_REVIEW(4004, "KYC Under Review"),
    KYC_REJECTED(4005, "KYC Rejected"),
    DOCUMENT_INVALID(4006, "Invalid Document"),

    // ========== 资产相关错误码 (5000-5999) ==========
    ACCOUNT_NOT_FOUND(5001, "Account Not Found"),
    INSUFFICIENT_BALANCE(5002, "Insufficient Balance"),
    WALLET_NOT_FOUND(5003, "Wallet Not Found"),
    WALLET_LOCKED(5004, "Wallet Locked"),
    DEPOSIT_FAILED(5005, "Deposit Failed"),
    WITHDRAW_FAILED(5006, "Withdraw Failed"),
    WITHDRAW_LIMIT_EXCEEDED(5007, "Withdraw Limit Exceeded"),
    INVALID_ADDRESS(5008, "Invalid Wallet Address"),

    // ========== 交易相关错误码 (6000-6999) ==========
    ORDER_NOT_FOUND(6001, "Order Not Found"),
    ORDER_ALREADY_CANCELLED(6002, "Order Already Cancelled"),
    ORDER_ALREADY_FILLED(6003, "Order Already Filled"),
    INVALID_ORDER_PRICE(6004, "Invalid Order Price"),
    INVALID_ORDER_QUANTITY(6005, "Invalid Order Quantity"),
    TRADING_PAIR_NOT_FOUND(6006, "Trading Pair Not Found"),
    TRADING_SUSPENDED(6007, "Trading Suspended"),
    PRICE_LIMIT_EXCEEDED(6008, "Price Limit Exceeded"),

    // ========== 风控相关错误码 (7000-7999) ==========
    RISK_ALERT(7001, "Risk Alert Triggered"),
    SUSPICIOUS_ACTIVITY(7002, "Suspicious Activity Detected"),
    TRANSACTION_BLOCKED(7003, "Transaction Blocked by Risk Control"),
    AML_CHECK_FAILED(7004, "AML Check Failed"),
    DAILY_LIMIT_EXCEEDED(7005, "Daily Limit Exceeded"),
    FRAUD_DETECTED(7006, "Fraud Detected"),

    // ========== 系统相关错误码 (9000-9999) ==========
    SERVICE_UNAVAILABLE(9001, "Service Unavailable"),
    DATABASE_ERROR(9002, "Database Error"),
    CACHE_ERROR(9003, "Cache Error"),
    MESSAGE_QUEUE_ERROR(9004, "Message Queue Error"),
    THIRD_PARTY_API_ERROR(9005, "Third Party API Error");

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
