package com.remo.gateway.mapper.entity;

import org.apache.ibatis.annotations.Select;

public interface BlacklistMapper {

    @Select(" select ID AS ID ,ip_addres AS ipAddres,restriction_type as restrictionType, availability as availability from blacklist where ip_addres =#{ipAddres} and restriction_type='1' ")
    Blacklist findBlacklist(String ipAddress);
}
