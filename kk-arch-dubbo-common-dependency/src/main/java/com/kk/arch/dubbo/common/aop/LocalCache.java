package com.kk.arch.dubbo.common.aop;

import java.lang.annotation.*;

/**
 * @author Zal
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalCache {

    String key() default ""; // 缓存键

    /**
     * 单位是毫秒, 默认缓存时间60秒
     */
    long ttl() default 60 * 1000;

}