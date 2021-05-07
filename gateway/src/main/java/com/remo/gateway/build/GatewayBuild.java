package com.remo.gateway.build;

import java.util.List;
import java.util.Map;

/**
 * description: 网关行为建造者
 */
public interface GatewayBuild {

    /**
     * 黑名单拦截
     *
     * @param ipAddress IP地址
     */
    Boolean blackBlock(String ipAddress);

    /**
	 * 参数验证
	 */
	Boolean toVerifyMap(String ipAddres, Map<String, List<String>> attributes);

}
