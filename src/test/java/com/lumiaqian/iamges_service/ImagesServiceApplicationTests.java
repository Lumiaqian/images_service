package com.lumiaqian.iamges_service;

import com.lumiaqian.images_service.ImagesServiceApplication;
import com.lumiaqian.images_service.Service.IpDirRepository;
import com.lumiaqian.images_service.Utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImagesServiceApplication.class)
public class ImagesServiceApplicationTests {

	@Autowired
	private IpDirRepository ipDirRepository;
	private final static Logger logger = LoggerFactory.getLogger(ImagesServiceApplicationTests.class);

	@Test
	public void contextLoads() {
		String ip = "120.236.153.209";
		String pa = "lumia";
		// pathRepository.save(path);
		//logger.debug("第一次从MongoDB中取出的数据：" + pathRepository.findByIP(ip));
		// pathRepository.update(path);
		logger.info("第二次从MongoDB中取出的数据：" + ipDirRepository.findByIP(ip));
		logger.info("UUID:"+ StringUtil.getRandomString(5));

	}

}
