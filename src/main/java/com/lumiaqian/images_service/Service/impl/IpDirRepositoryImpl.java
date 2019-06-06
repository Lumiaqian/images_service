package com.lumiaqian.images_service.Service.impl;

import com.lumiaqian.images_service.Model.IpDir;
import com.lumiaqian.images_service.Service.IpDirRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * @author qian
 * @version V1.0
 * @Title: ipDirRepositoryImpl
 * @Package: com.lumiaqian.images_service.Service.impl
 * @Description: TOTO
 * @date 2019-05-06 16:41
 **/
@Component
public class IpDirRepositoryImpl implements IpDirRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(IpDir ipDir) {
        mongoTemplate.save(ipDir);
    }

    @Override
    public IpDir findByIP(String ip) {
        Query query = new Query(Criteria.where("ip").is(ip));
        IpDir ipDir = mongoTemplate.findOne(query,IpDir.class);
        return ipDir;
    }

    @Override
    public long update(IpDir ipDir) {
        Query query = new Query(Criteria.where("ip").is(ipDir.getIp()));
        Update update = new Update().set("ip",ipDir.getIp()).set("dir",ipDir.getDir());
        //更新查询返回结果集的第一条
        UpdateResult result = mongoTemplate.updateFirst(query,update,IpDir.class);
        if (result!=null){
            return result.getMatchedCount();
        }
        return 0;
    }

    @Override
    public void deleteByIP(String ip) {
        Query query = new Query(Criteria.where("ip").is(ip));
        mongoTemplate.remove(query,IpDir.class);
    }
}
