package com.remo.seckill.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessLimitAspect {

    @Pointcut("@annotation(com.remo.annotation.AccessLimit)")
    public void pointcut() {
    }

    /*@Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        MiaoshaUser user = getUser(request, response);
        UserContext.setUser(user);
        AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
        if(accessLimit == null) {
            return true;
        }
        int seconds = accessLimit.seconds();
        int maxCount = accessLimit.maxCount();
        boolean needLogin = accessLimit.needLogin();
        String key = request.getRequestURI();
        if(needLogin) {
            if(user == null) {
                render(response, SESSION_ERROR);
                return false;
            }
            key += "_" + user.getNickname();
        }else {
            //do nothing
        }
        AccessKey ak = AccessKey.withExpire(seconds);
        Integer count = redisService.get(ak, key, Integer.class);
        if(count  == null) {
            redisService.set(ak, key, 1);
        }else if(count < maxCount) {
            redisService.incr(ak, key);
        }else {
            render(response, ACCESS_LIMIT_REACHED);
            return false;
        }
    }*/

    /*private MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(MiaoShaUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, MiaoShaUserService.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return userService.getByToken(response, token);
    }*/
}
