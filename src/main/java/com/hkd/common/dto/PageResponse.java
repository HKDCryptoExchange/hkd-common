package com.hkd.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应
 *
 * @param <T> 数据类型
 * @author HKD Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    private Integer page;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 数据列表
     */
    private List<T> records;

    /**
     * 是否有下一页
     */
    private Boolean hasNext;

    /**
     * 是否有上一页
     */
    private Boolean hasPrevious;

    /**
     * 构造分页响应
     */
    public static <T> PageResponse<T> of(Integer page, Integer pageSize, Long total, List<T> records) {
        PageResponse<T> response = new PageResponse<>();
        response.setPage(page);
        response.setPageSize(pageSize);
        response.setTotal(total);
        response.setRecords(records);

        // 计算总页数
        int totalPages = (int) Math.ceil((double) total / pageSize);
        response.setTotalPages(totalPages);

        // 是否有下一页/上一页
        response.setHasNext(page < totalPages);
        response.setHasPrevious(page > 1);

        return response;
    }

    /**
     * 空分页响应
     */
    public static <T> PageResponse<T> empty(Integer page, Integer pageSize) {
        return of(page, pageSize, 0L, List.of());
    }
}
