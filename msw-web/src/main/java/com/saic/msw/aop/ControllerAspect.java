package com.saic.msw.aop;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.saic.msw.annotation.NoLogin;
import com.saic.msw.constants.MessageConstants;
import com.saic.msw.error.ApiException;
import com.saic.msw.utils.Cache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: zzz
 * @CREATE_DATE: 2021/7/23 14:06
 * @UPDATE_DATE：2021/7/23 14:06
 * @Version 1.0
 * @Description：
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(public * com.saic.msw.controller.*.*(..))")
    public void pointcut(){}

  /*  @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
    }
*/
    /**
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        // 获取方法, 直接getClass获取对象可能为代理对象
        Method method = methodSignature.getMethod();
        if (null != method && !method.isAnnotationPresent(NoLogin.class)) {
            if(!checkToken(token)){
                throw new ApiException(MessageConstants.NO_LOGIN_CODE,MessageConstants.NO_LOGIN_MSG);
            }
            replaceToken(token);
        }
        String threadId = Thread.currentThread().getId() + "" + System.currentTimeMillis();
        reqLog(proceedingJoinPoint, threadId);
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        respLog(result,startTime,threadId);
        return result;
    }


    public boolean checkToken(String token){
        return Cache.checkCacheName(token);
    }

    public boolean replaceToken(String token){
        Cache.putSynDate(token,Cache.CACHE_HOLD_TIME_30M);
        return true;
    }


    public void reqLog(JoinPoint joinPoint,String threadId) {
        // 接收到请求，记录请求内容

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("********************************************");
        log.info("NO：{}, 请求URL：{}" ,threadId, request.getRequestURL());
        log.info("NO：{}, 请求方式：{}" ,threadId, request.getMethod());
        log.info("NO：{}, 客户端IP：[{}]" ,threadId, getIp(request));
        log.info("NO：{}, 请求映射:{}",threadId,"【" + joinPoint.getSignature().getDeclaringTypeName() + "】类的【" + joinPoint.getSignature().getName() + "】方法");

        // 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
        if (joinPoint.getArgs().length > 0) {
            for (Object o : joinPoint.getArgs()) {
                if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                    continue;
                }
                log.info("NO：{}, 请求参数 :{} ",threadId,JSONUtil.toJsonStr(o));
            }
        }
    }


    public void respLog(Object result,long startTime,String threadId) {
        // 打印出参
        log.info("NO：{}, 出参: {}",threadId, JSONUtil.toJsonStr(result));
        // 执行耗时
        log.info("NO：{}, 执行耗时: {} ms",threadId, System.currentTimeMillis() - startTime);
    }


    public static String getIp(HttpServletRequest request){
        //代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        //如果没有代理，则获取真实ip
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }


}
