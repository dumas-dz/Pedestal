package com.dumas.pedestal.common.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Objects;

/**
 * 分页参数：默认从1开始
 *
 * @author dumas
 * @date 2021/12/06 10:46 AM
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageParam {
    @Schema(description = "分页参数：当前页[pageNo必须为正整数]", requiredMode = Schema.RequiredMode.REQUIRED)
    @PositiveOrZero(message = "分页参数错误：pageNo必须为正整数")
    Integer pageNo;

    @Schema(description = "分页参数：一页大小[最多每页 100 条数据]", requiredMode = Schema.RequiredMode.REQUIRED)
    @Max(value = 100, message = "每页参数错误：最多每页 100 条数据")
    Integer pageSize;

    public Integer getPageNo() {
        if (Objects.isNull(pageNo)) {
            pageNo = 1;
        }
        return pageNo;
    }
}
