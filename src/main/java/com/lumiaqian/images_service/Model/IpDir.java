package com.lumiaqian.images_service.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Path
 * @Package: com.lumiaqian.images_service.Model
 * @Description: 图片保存路径
 * @date 2019-05-06 16:27
 **/
@Data
@Getter
@Setter
public class IpDir implements Serializable {
     /**
       * 访问者的IP
      **/
    String ip;
    /**
     * 路径
     **/
    String dir;
}
