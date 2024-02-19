package com.github.dreamph.core.utils;

import java.util.HashMap;
import java.util.Map;

public enum MessageCode {

    //COMMONS MESSAGES
    E00001("E00001", "{0} field is required"),
    E00002("E00002", "{0} field is invalid"),

    E00003("E00003", "{0} field must more than 0"),
    E00004("E00004", "{0} field must between {1} and {2}"),
    E00005("E00005", "{0} field must be {1} digits"),
    E00006("E00006", "{0} field must be {1} characters"),
    E00007("E00007", "{0} field must more than or equal {1} characters"),
    E00008("E00008", "{0} field must more than or equal {1}"),

    E00009("E00009", "{0} and {1} field mismatch"),
    E00010("E00010", "{0} {1} already registered"),
    E00011("E00011", "{0} field size not more than {1}"),

    E00012("E00012", "{0} field {1}"),
    E00013("E00013", "{0}"),
    E00014("E00014", "{0} does not exist or status is inactive"),
    E00015("E00015", "{0} field is limit at {1} characters."),
    E00016("E00016", "{0} field not allow to update"),
    //COMMONS MESSAGES

    E99001("E99001", "System Unavailable");

    private static Map<String, String> messages = null;
    private final String code;
    private final String desc;

    MessageCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Map<String, String> getMessages() {
        if (messages == null) {
            messages = new HashMap<>();
            for (MessageCode code : values()) {
                messages.put(code.code, code.desc);
            }
        }
        return messages;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
