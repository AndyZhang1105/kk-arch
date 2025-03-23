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

package com.kk.arch.dubbo.common.util;

import com.kk.arch.remote.vo.ResponseData;

import static com.kk.arch.remote.vo.ResponseData.FAIL;
import static com.kk.arch.remote.vo.ResponseData.SUCCESS;

/**
 * @author Zal
 */
public final class ResponseUtils {

	private ResponseUtils() {

	}

	/**
	 * 默认的成功.
	 */
	public static <T> ResponseData<T> success() {
		return new ResponseData<>(SUCCESS, "");
	}

	/**
	 * 成功.
	 */
	public static <T> ResponseData<T> success(T data) {
		return new ResponseData<T>(SUCCESS, "", data);
	}

	/**
	 * 默认的失败.
	 */
	public static <T> ResponseData<T> fail(String msg) {
		return new ResponseData<T>(FAIL, msg, null);
	}

	/**
	 * 失败.
	 */
	public static <T> ResponseData<T> fail(T data) {
		return new ResponseData<T>(FAIL, "", data);
	}

}
