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

package com.kk.arch.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 转换对象工具.
 *
 * @author jin
 */
public final class BeanUtils {

	private BeanUtils() {

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
		return JSON.parseObject(JSON.toJSONString(sourceList), new TypeReference<List<T>>() {

		});
	}
}

