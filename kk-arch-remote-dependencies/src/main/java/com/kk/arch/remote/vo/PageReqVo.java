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

package com.kk.arch.remote.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author Zal
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PageReqVo<T> implements Serializable {

	private long pageNum = 1; // 默认第一页
	private long pageSize = 10; // 默认每页10条记录
	private String sortField; // 排序字段
	private String sortOrder; // 排序方式（asc, desc）
	private T param;

	public static <T> PageReqVo<T> of(long pageNum, long pageSize, T param) {
		PageReqVo<T> response = new PageReqVo<>();
		response.setPageNum(pageNum);
		response.setPageSize(pageSize);
		response.setParam(param);
		return response;
	}

}
