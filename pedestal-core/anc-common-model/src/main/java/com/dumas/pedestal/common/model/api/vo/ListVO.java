package com.dumas.pedestal.common.model.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

/**
 * 列表型接口返回
 *
 * @author dumas
 * @date 2021/12/06 10:54 AM
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "列表结果")
public class ListVO<T> {
    /**
     * 总条数
     */
    @Schema(description = "列表项总数")
    Integer total;
    /**
     * 列表项
     */
    @Schema(description = "列表")
    List<T> list;

    public ListVO<T> ofEmpty() {
        this.total = 0;
        this.list = Collections.EMPTY_LIST;
        return this;
    }

    public static <T> ListVO<T> fromList(List<T> eleList) {
        ListVO<T> listVO = new ListVO<>();
        listVO.setTotal(eleList.size());
        listVO.setList(eleList);
        return listVO;
    }
}
