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
import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PageRespVo<T> implements Serializable {

	private long total; // 总记录数
	private long pageNum; // 当前页码
	private long pageSize; // 每页记录数
	private List<T> list; // 当前页的数据列表

	public static <T> PageRespVo<T> of(long total, long pageNum, long pageSize, List<T> list) {
		PageRespVo<T> response = new PageRespVo<>();
		response.setTotal(total);
		response.setPageNum(pageNum);
		response.setPageSize(pageSize);
		response.setList(list);
		return response;
	}

}
