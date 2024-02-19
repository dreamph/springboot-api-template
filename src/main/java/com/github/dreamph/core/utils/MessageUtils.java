package com.github.dreamph.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Map;

public class MessageUtils {
    private static final Map<String, String> MESSAGE_DESC = MessageCode.getMessages();

    public static String getMessage(String errorCode, String... messages) {
        if (MESSAGE_DESC.containsKey(errorCode)) {
            return MessageFormat.format(MESSAGE_DESC.get(errorCode), (Object[]) messages);
        }
        return StringUtils.EMPTY;
    }

    public static String getMessage(String errorCode) {
        if (MESSAGE_DESC.containsKey(errorCode)) {
            return MESSAGE_DESC.get(errorCode);
        }
        return StringUtils.EMPTY;
    }

    public static String format(String message, String... messages) {
        return MessageFormat.format(message, (Object[]) messages);
    }

    public static String buildMessage(String errorCode, String[] errorMessage) {
        StringBuilder builder = new StringBuilder(errorCode).append(" : ");
        if (errorMessage == null) {
            builder.append(MessageUtils.getMessage(errorCode));
        } else {
            builder.append(MessageUtils.getMessage(errorCode, errorMessage));
        }
        return builder.toString();
    }

    public static String buildMessage(MessageCode errorCode, String[] errorMessage) {
        StringBuilder builder = new StringBuilder(errorCode.getCode()).append(" : ");
        if (errorMessage == null) {
            builder.append(errorCode.getDesc());
        } else {
            builder.append(MessageUtils.format(errorCode.getDesc(), errorMessage));
        }
        return builder.toString();
    }
}
