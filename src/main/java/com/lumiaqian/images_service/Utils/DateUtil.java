package com.lumiaqian.images_service.Utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: DateUtil
 * @Package: com.caoyuqian.blog.utils
 * @Description: 时间工具类
 * @date 2018/8/14 下午2:43
 **/
public class DateUtil {

    /**
      * @Param:
      * @return:
      * @Author: qian
      * @Description: 获取当前时间
      * @Date: 2018/9/21 下午3:51
     **/
    public static Date getNow(){
        LocalDateTime now=LocalDateTime.now();
        return localDateTimeToDate(now);
    }
     /**
       * @Param: LocalDateTime
       * @return:
       * @Author: qian
       * @Description: LocalDateTime转Date
       * @Date: 2018/8/14 下午3:32
      **/
    public static Date localDateTimeToDate(LocalDateTime dateTime){
        Date date;
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = dateTime.atZone(zoneId);
        date = Date.from(zdt.toInstant());
        return date;
    }

     /**
       * @Param: Date
       * @return:
       * @Author: qian
       * @Description: Date转LocalDateTime
       * @Date: 2018/8/14 下午3:56
      **/
    public static LocalDateTime DateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }
    /**
      * @Param: Date N
      * @return: Date
      * @Author: qian
      * @Description: date类型时间减少N小时
      * @Date: 2019-01-09 15:08
     **/
    public static Date dateSubHour(Date date , int n){
        LocalDateTime localDateTime = DateToLocalDateTime(date);
        localDateTime=localDateTime.minusHours(n);
        return localDateTimeToDate(localDateTime);
    }
    /**
      * @Param: Data
      * @return: String
      * @Author: qian
      * @Description: 将Data转化成String类型
      * @Date: 2018/8/14 下午3:58
     **/
    public static String dateToString(Date date){
        LocalDateTime localDateTime= DateUtil.DateToLocalDateTime(date);
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String str;
        str=dtf.format(localDateTime);
        return str;
    }
}
