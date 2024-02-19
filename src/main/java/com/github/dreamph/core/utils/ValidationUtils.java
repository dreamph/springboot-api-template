package com.github.dreamph.core.utils;


import com.github.dreamph.core.dto.PageLimit;
import com.github.dreamph.core.exceptions.ValidationException;
import com.google.common.collect.Iterables;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ValidationUtils {

    private static boolean isNullOrEmpty(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof String) {
            return StringUtils.isEmpty((String) value);
        }
        if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        }
        return false;
    }

    public static boolean isNotEmpty(Object value) {
        return !isNullOrEmpty(value);
    }

    public static boolean isNotEmpty(List<?> value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isNotEmpty(Set<?> value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isNotEmpty(Iterable<?> value) {
        return !Iterables.isEmpty(value);
    }

    public static boolean isNotEmptyAll(Object... values) {
        for (Object value : values) {
            if (isNullOrEmpty(value)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(Object value) {
        return isNullOrEmpty(value);
    }

    public static boolean isBetween(BigDecimal value, BigDecimal value1, BigDecimal value2) {
        return (value.compareTo(value1) >= 0 && value.compareTo(value2) <= 0);
    }

    public static boolean isNotBetween(BigDecimal value, BigDecimal value1, BigDecimal value2) {
        return !isBetween(value, value1, value2);
    }

    public static boolean isCorrectEmailFormat(String email) {
        // RFC2822 Email Validation
        String regex = "[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?";
        return email.matches(regex);
    }

    public static boolean isCorrectPasswordFormat(String password) {
        // Including Uppercase, Lowercase and numeric.
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        return password.matches(regex);
    }

    public static void requiredLimit(PageLimit pageLimit) throws ValidationException {
        required(isNotEmpty(pageLimit), MessageCode.E00001, "limit");

        required(isNotEmpty(pageLimit.getPageSize()), MessageCode.E00001, "limit.pageSize");
        required(pageLimit.getPageSize() > 0, MessageCode.E00003, "limit.pageSize");

        required(isNotEmpty(pageLimit.getPageNumber()), MessageCode.E00001, "limit.pageNumber");
        required(pageLimit.getPageNumber() > 0, MessageCode.E00003, "limit.pageNumber");
    }

    public static void required(boolean result, MessageCode errorCode, String... errorMessage) throws ValidationException {
        if (!result) {
            String finalMessage = MessageUtils.buildMessage(errorCode, errorMessage);
            throw new ValidationException(finalMessage);
        }
    }

    public static void required(boolean result, String errorCode, String... errorMessage) throws ValidationException {
        if (!result) {
            String finalMessage = MessageUtils.buildMessage(errorCode, errorMessage);
            throw new ValidationException(finalMessage);
        }
    }

    public static void validate(List<String> allErrors, boolean result, MessageCode errorCode, String... errorMessage) {
        if (!result) {
            String finalMessage = MessageUtils.buildMessage(errorCode, errorMessage);
            allErrors.add(finalMessage);
        }
    }
}
