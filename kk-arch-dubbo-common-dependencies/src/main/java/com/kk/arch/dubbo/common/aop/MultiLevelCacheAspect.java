package com.kk.arch.dubbo.common.aop;

import com.kk.arch.dubbo.common.conf.MultiLevelCacheHelper;
import com.kk.arch.dubbo.common.conf.TenantContextHolder;
import com.kk.arch.dubbo.common.util.JsonUtils;
import com.kk.arch.dubbo.common.util.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Zal
 */
@Slf4j
@Aspect
@Component
public class MultiLevelCacheAspect {

    @Around("@annotation(multiLevelCache)")
    public Object multiLevelCacheAdvice(ProceedingJoinPoint joinPoint, MultiLevelCache multiLevelCache) throws Throwable {
        String key = multiLevelCache.key();
        if (key.isEmpty()) {
            key = generateKey(joinPoint);
        }

        return MultiLevelCacheHelper.get(key, multiLevelCache.ttl(), () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String generateKey(ProceedingJoinPoint joinPoint) {
        // 生成唯一的缓存键
        // return joinPoint.getSignature().toShortString() + Arrays.toString(joinPoint.getArgs());
        return TenantContextHolder.getTenantId() + "_" + Md5Utils.md5(joinPoint.getSignature().toShortString() + "_" + JsonUtils.toJsonString(joinPoint.getArgs()));
    }
}