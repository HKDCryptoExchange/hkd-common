package com.hkd.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页请求
 *
 * @author HKD Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码（从1开始）
     */
    @Min(value = 1, message = "页码必须大于0")
    private Integer page = 1;

    /**
     * 每页大小
     */
    @Min(value = 1, message = "每页大小必须大于0")
    @Max(value = 100, message = "每页大小不能超过100")
    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    private String sortBy;

    /**
     * 排序方向 (ASC/DESC)
     */
    private String sortOrder = "DESC";

    /**
     * 计算偏移量（用于数据库查询）
     */
    public int getOffset() {
        return (page - 1) * pageSize;
    }

    /**
     * 获取限制数量（用于数据库查询）
     */
    public int getLimit() {
        return pageSize;
    }
}
