package com.pant.loki.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {

    private static final String PARAMS_REGEX = "([?&])([^(?|&|=|\\s)]+)=([^(?|&|=|\\s)]+)";

    /**
     * @description 获取uri地址中的参数
     * @params [uri]
     * @returnType java.util.Map<java.lang.String,java.lang.String>
     * @author pantao
     * @date 2018/7/6 14:42
     */
    public static Map<String, String> getParamsMap(String uri) {
        Map<String, String> params = new HashMap<>();
        Pattern pattern = Pattern.compile(PARAMS_REGEX);
        Matcher matcher = pattern.matcher(uri);
        while (matcher.find()) {
            if (matcher.groupCount() < 3) {
                continue;
            }

            String paramName = matcher.group(2);
            String paramValue = matcher.group(3);
            params.put(paramName, paramValue);
        }

        return params;
    }
}
