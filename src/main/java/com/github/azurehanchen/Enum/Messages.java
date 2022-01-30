package com.github.azurehanchen.Enum;

import com.github.azurehanchen.Utils.CommonUtils;

public enum Messages {

    PREFIX(CommonUtils.format("&eKarixTab &7| &r")),
    NO_PERMISSION(CommonUtils.format("&c您没有执行该操作的权限")),
    NOT_PLAYER(CommonUtils.format("&e您不是一名玩家")),
    WRONG_USAGE(CommonUtils.format("&e用法错误")),
    FATAL_ERROR(CommonUtils.format("&c插件出现致命错误"));

    private final String message;
    Messages(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
