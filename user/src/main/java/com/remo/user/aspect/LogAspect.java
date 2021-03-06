package com.remo.user.aspect;

import com.remo.basic.annotation.RemoLog;
import com.remo.user.client.LogServiceClient;
import com.remo.user.client.po.SysLog;
import com.remo.user.utils.HttpContextUtil;
import com.remo.user.utils.IPUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogServiceClient logServiceClient;

    @Pointcut("@annotation(com.remo.basic.annotation.RemoLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            String className = point.getTarget().getClass().getName();
            String methodName = signature.getName();
            LOGGER.error(className + "." + methodName + "()记录日志错误");
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        RemoLog logAnnotation = method.getAnnotation(RemoLog.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }

        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtil.getIpAddr(request));
        sysLog.setIp("localhost");
        // 模拟一个用户名
        sysLog.setUsername("remo");
        sysLog.setTime((int) time);
        sysLog.setCreateTime(new Date());
        sysLog.setCreateUser("remo");
        // 保存系统日志
        logServiceClient.saveSysLog(sysLog);
    }
}
