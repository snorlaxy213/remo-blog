package com.remo.gateway.build;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class GatewayAttributesVerify {

    @Resource(name = "verificationBuild")
    private GatewayBuild gatewayBuild;

    public Boolean action(String ipAddress, Map<String, List<String>> attributes) {

        /**
		 * 参数验证
		 */
		return gatewayBuild.toVerifyMap(ipAddress, attributes);
    }

}

