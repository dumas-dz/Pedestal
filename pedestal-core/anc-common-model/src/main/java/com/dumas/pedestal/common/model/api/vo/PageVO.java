package com.dumas.pedestal.common.model.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页型 api 接口返回
 *
 * @author dumas
 * @date 2021/12/06 10:48 AM
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "分页结果")
public class PageVO<T> {
    /**
     * 当前页
     */
    private Long pageNo;
    /**
     * 单页记录数
     */
    private Long pageSize;
    /**
     * 总条数
     */
    @Schema(description = "总条数")
    private Long total;
    /**
     * 分页内容
     */
    @Schema(description = "页数据")
    private List<T> list;

    public <R> PageVO<R> map(Function<? super T, ? extends R> converter) {
        Objects.requireNonNull(converter);

        PageVO<R> newPage = new PageVO<>();
        newPage.setPageNo(this.pageNo);
        newPage.setPageSize(this.pageSize);
        newPage.setTotal(this.total);
        if (Objects.nonNull(list) && !list.isEmpty()) {
            newPage.setList(list.stream().map(converter).collect(Collectors.toList()));
        } else {
            newPage.setList(new ArrayList<>());
        }
        return newPage;
    }

    public PageVO<T> ofEmpty() {
        this.total = 0L;
        this.list = Collections.EMPTY_LIST;
        return this;
    }

    public static <T> PageVO<T> ofPage(Long total, List<T> list, Long pageNo, Long pageSize) {
        PageVO<T> pageVO = new PageVO<>();
        pageVO.setTotal(total);
        pageVO.setList(list);
        pageVO.setPageNo(pageNo);
        pageVO.setPageSize(pageSize);
        return pageVO;
    }
}
