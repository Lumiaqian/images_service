package com.lumiaqian.images_service.Utils;

import java.util.Random;

/**
 * @author qian
 * @version V1.0
 * @Title: StringUtil
 * @Package: com.lumiaqian.images_service.Utils
 * @Description: 字符串工具类
 * @date 2019-05-07 13:33
 **/
public class StringUtil {

    public static String getRandomString(int length) {
        String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i <= length; i++) {
            int num = random.nextInt(string.length());
            stringBuffer.append(string.charAt(num));
        }
        return stringBuffer.toString();
    }
}
