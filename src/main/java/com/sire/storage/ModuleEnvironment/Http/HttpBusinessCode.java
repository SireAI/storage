package com.sire.storage.ModuleEnvironment.Http;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/8/29
 * Author:Sire
 * Description:
 * ==================================================
 */
public interface HttpBusinessCode {
    /**
     * 操作成功
     */
    int OK = 200;
    /**
     * 数据校验错误
     */
    int DATA_VERIFY_ERROR = 201;
    /**
     * 未定义异常
     */
    int UNDEFINED_ERROR = 202;
    /**
     * 操作失败
     */
    int FAILED = 0;
    /**
     * 未登录
     */
    int NOT_LOGIN_STATE = 203;
    /**
     * 登陆状态过期
     */
    int LOGIN_STATE_EXPIRED = 204;
    /**
     * 认证账户存在，密码错误
     */
    int ACCOUNT_EXISTS_PWD_ERROR = 205;
    /**
     * 认证信息不存在
     */
    int AUTH_INFO_NOT_EXIST = 206;

    int RESOURCE_NOT_EXIST = 207;

}
