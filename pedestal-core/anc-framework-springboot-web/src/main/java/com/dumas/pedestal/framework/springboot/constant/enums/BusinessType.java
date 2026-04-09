package com.dumas.pedestal.framework.springboot.constant.enums;

import jakarta.annotation.Nullable;

/**
 * 业务操作类型
 *
 * @author andaren
 * @version V1.0
 * @since 2020-11-19 19:56
 */
public enum BusinessType {
    // 操作类型[0:查询，1：新增，2：修改，3：删除，4:其他]
    /**
     * 查询
     */
    SEARCH(0),
    /**
     * 其它
     */
    OTHER(4),

    /**
     * 新增
     */
    INSERT(1),

    /**
     * 修改
     */
    UPDATE(2),

    /**
     * 删除
     */
    DELETE(3),

    /**
     * 导出
     */
    EXPORT(12),

    /**
     * 导入
     */
    IMPORT(11),

    /**
     * 授权
     */
    GRANT(99),
    ;

    private Integer code;
    BusinessType(Integer code) {
        this.code = code;
    }

    @Nullable
    public static BusinessType getByCode(Integer code) {
        for (BusinessType type : BusinessType.values()) {
            if (code.equals(type.code())) {
                return type;
            }
        }
        return null;
    }

    public Integer code() {
        return code;
    }
}
