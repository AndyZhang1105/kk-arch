/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kk.arch.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.kk.arch.common.vo.PageReqVo;
import com.kk.arch.common.vo.PageRespVo;

/**
 * 转换对象工具.
 *
 * @author jin
 */
public final class JsonUtils {

	private JsonUtils() {

	}

	/**
	 * 使用 fastjson 将一个对象转换为另一个对象.
	 *
	 * @param source      源对象
	 * @param targetClass 目标对象的类
	 * @param <S>         源对象的类型
	 * @param <T>         目标对象的类型
	 * @return 转换后的目标对象
	 */
	public static <S, T> T toObject(S source, Class<T> targetClass) {
		if (source == null) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(source), targetClass);
	}

	/**
	 * 使用 fastjson 将一个 Map 转换为对象.
	 *
	 * @param map         源 Map
	 * @param targetClass 目标对象的类
	 * @param <T>         目标对象的类型
	 * @return 转换后的目标对象
	 */
	public static <T> T toObject(Map<String, Object> map, Class<T> targetClass) {
		if (map == null) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(map), targetClass);
	}


	/**
	 * 使用 fastjson 将一个列表中的对象转换为另一个类型的列表.
	 *
	 * @param sourceList  源对象列表
	 * @param targetClass 目标对象的类
	 * @param <S>         源对象的类型
	 * @param <T>         目标对象的类型
	 * @return 转换后的目标对象列表
	 */
	public static <S, T> List<T> toList(List<S> sourceList, Class<T> targetClass) {
		if (sourceList == null || sourceList.isEmpty()) {
			return new ArrayList<>();
		}
		return JSON.parseArray(JSON.toJSONString(sourceList), targetClass);
	}

	/**
	 * 将对象转成json字符串
	 * @param param 转换的源对象
	 * @return 转换后的字符串
	 */
	public static String toJsonString(Object param) {
		return JSON.toJSONString(param);
	}

	/**
	 * 将对象转成json字符串
	 * @param param 转换的源对象
	 * @return 转换后的map
	 */
	public static Map<String, Object> toMap(Object param) {
		String jsonString = JSON.toJSONString(param);
		return JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>() {});
	}

	public static <T> T mapToObject(Map<String, Object> map, Class<T> targetClass) {
		if (map == null || map.keySet().isEmpty()) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(map), targetClass);
	}

	/**
	 * 将一种类型的分页类请求对象转换成另一种
	 */
	public static <T, S> PageReqVo<T> toPageReqVo(PageReqVo<S> pageReqVo, Class<T> destClass) {
		final T destParam = JsonUtils.toObject(pageReqVo.getParam(), destClass);
		return PageReqVo.of(pageReqVo.getPageNum(), pageReqVo.getPageSize(), destParam);
	}

	/**
	 * 将一种类型的分页类结果对象转换成另一种
	 */
	public static <T, S> PageRespVo<T> toPageRespVo(PageRespVo<S> dataPageVo, Class<T> destClass) {
		final List<T> destVoList = JsonUtils.toList(dataPageVo.getList(), destClass);
        return PageRespVo.of(dataPageVo.getTotal(), dataPageVo.getPageNum(), dataPageVo.getPageSize(), destVoList);
	}
}

