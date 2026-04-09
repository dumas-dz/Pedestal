package com.dumas.pedestal.common.model.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * NormalR 基类
 *
 * @author dumas
 * @date 2021/12/06 10:40 AM
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "ResponseVO")
public class R<T> {
    /**
     * 错误码
     */
    @Schema(description = "错误码[0: 成功]", requiredMode = Schema.RequiredMode.REQUIRED)
    String code;
    /**
     * 错误信息
     */
    @Schema(description = "错误信息", requiredMode = Schema.RequiredMode.REQUIRED)
    String msg;
    /**
     * 是否调用成功
     */
    @Schema(description = "是否调用成功", requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean success;

    /**
     * 返回的数据
     */
    @Schema(description = "返回的数据[调用失败时，为空]")
    T data;
}
