package com.kali.springboot.common.response;

/**
 * @author Elliot
 */
public enum ResponseEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "common.response.success"),
    /**
     * 失败
     */
    FAILURE(500, "common.response.failure"),
    /**
     * 未找到
     */
    NOTFOUND(404, "common.response.notfound"),
    /**
     * 非法请求
     */
    ILLEGAL(403, "common.response.illegal"),
    /**
     * 用户被锁定
     */
    USER_LOCK(601, "common.response.userLock"),
    /**
     * 用户未分配权限
     */
    USER_ILLEGAL(603, "common.response.userIllegal"),
    /**
     * 保存成功
     */
    SAVE_SUCCESS(200, "common.response.save.success"),
    /**
     * 保存失败
     */
    SAVE_FAILURE(500, "common.response.save.failure"),
    /**
     * 修改成功
     */
    UPDATE_SUCCESS(200, "common.response.update.success"),
    /**
     * 修改失败
     */
    UPDATE_FAILURE(500, "common.response.update.failure"),
    /**
     * 删除成功
     */
    DELETE_SUCCESS(200, "common.response.delete.success"),
    /**
     * 删除失败
     */
    DELETE_FAILURE(500, "common.response.delete.failure"),
    /**
     * 未登录
     */
    NOT_LOGIN(500, "common.response.not.login"),
    /**
     * Token错误
     */
    TOKEN_ERROR(500, "common.response.token.error"),
    /**
     * Token失效
     */
    TOKEN_TIMEOUT(403, "common.response.token.timeOut");

    private final int code;

    private final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseEnum match(Integer code) {
        if (code != null) {
            for (ResponseEnum response : ResponseEnum.values()) {
                if (code.equals(response.code)) {
                    return response;
                }
            }
        }
        return ResponseEnum.FAILURE;
    }
}