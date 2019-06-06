package com.lumiaqian.images_service.Service;

import com.lumiaqian.images_service.Model.IpDir;

public interface IpDirRepository {
    void save(IpDir ipDir);
    IpDir findByIP(String ip);
    long update(IpDir ipDir);
    void deleteByIP(String ip);
}
