package com.github.dreamph.core.utils;

import com.github.dreamph.core.dto.PageLimit;
import com.github.dreamph.core.dto.PageQuery;
import com.github.dreamph.core.dto.PageResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class DataUtils {
    public static boolean isEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> list) {
        return !isEmpty(list);
    }

    public static boolean isEmpty(String val) {
        return val == null || val.isEmpty();
    }

    public static boolean isNotEmpty(String val) {
        return !isEmpty(val);
    }

    public static int getSize(Collection<?> list) {
        return isEmpty(list) ? 0 : list.size();
    }

    public static <T> T getByIndex(Object[] array, int index, Class<T> requiredType) {
        int len = ArrayUtils.getLength(array);
        if (index <= (len - 1)) {
            return (T) array[index];
        }
        return null;
    }

    public static <T> T getValueByIndex(Object[] array, int index, Class<T> requiredType) {
        int len = ArrayUtils.getLength(array);
        if (index <= (len - 1)) {
            return (T) array[index];
        }
        return (T) "";
    }

    public static void destroyObject(Object object) {
        if (object != null) {
            if (object instanceof Collection<?>) {
                ((Collection<?>) object).clear();
            } else if (object instanceof Map<?, ?>) {
                ((Map<?, ?>) object).clear();
            }
            object = null;
        }
    }

    public static String fileToBase64(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        byte[] bytes = IOUtils.toByteArray(is);
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    public static String getChecksumByFile(File file) {
        String checksum = null;
        try {
            checksum = DigestUtils.md5DigestAsHex(new FileInputStream(file));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return checksum;
    }

    public static String getChecksumByBase64(String base64Data) {
        String checksum = null;
        InputStream in = null;
        try {
            byte[] bytes = base64ToByteArray(base64Data);
            in = new ByteArrayInputStream(bytes);
            checksum = DigestUtils.md5DigestAsHex(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return checksum;
    }

    public static String getChecksumByBytes(byte[] bytes) {
        String checksum = null;
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(bytes);
            checksum = DigestUtils.md5DigestAsHex(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return checksum;
    }

    public static byte[] base64ToByteArray(String base64Data) {
        //return Base64Utils.decodeFromString(base64Data);
        return Base64.getDecoder().decode(base64Data);
    }

    public static boolean isUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public static boolean isNotUUID(String uuid) {
        return !isUUID(uuid);
    }

    public static boolean isNumber(String val) {
        return StringUtils.isNumeric(val);
    }

    public static PageResult getPageResult(PageLimit limit, Long total) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNumber(limit.getPageNumber());
        pageResult.setPageSize(limit.getPageSize());
        pageResult.setTotal(total);
        Long totalPages = total / Long.valueOf(limit.getPageSize());
        if (total.intValue() % limit.getPageSize() != 0) {
            totalPages++;
        }
        pageResult.setTotalPages(totalPages.intValue());
        return pageResult;
    }

    public static PageQuery getPageQuery(PageLimit pageLimit) {
        PageQuery pageResult = new PageQuery();
        pageResult.setPageSize(pageLimit.getPageSize());
        pageResult.setOffset((pageLimit.getPageNumber() - 1) * pageLimit.getPageSize());
        return pageResult;
    }

    public static PageQuery getPageQuery(Integer pageNumber, Integer pageSize) {
        PageQuery pageResult = new PageQuery();
        pageResult.setPageSize(pageSize);
        pageResult.setOffset((pageNumber - 1) * pageSize);
        return pageResult;
    }

    public static boolean getValue(Boolean value, boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static BigDecimal getValue(BigDecimal value, BigDecimal defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static String getValue(String value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    private String formatDouble(Double data) {
        return String.format("%.2f", data);
    }

    private String formatBigDecimal(BigDecimal data) {
        if (data == null) return null;
        return formatDouble(data.setScale(2, RoundingMode.DOWN).doubleValue());
    }
}
