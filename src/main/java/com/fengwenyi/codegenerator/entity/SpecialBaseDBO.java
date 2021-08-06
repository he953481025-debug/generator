package com.fengwenyi.codegenerator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;

/**
 * 
 * @Author: brandon 
 * @Date: 2020/11/4 10:38
 */
@ToString(callSuper = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialBaseDBO<T> extends BaseDBO{

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Instant createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Instant updateTime;

    /**
     * 修改人ID
     */
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;

    /**
     * 创建人ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Long createUserId;

    /**
     * 逻辑删除，如果表里没这个字段会报错
     */
    @TableLogic
    @TableField(value = "delete_status", fill = FieldFill.INSERT)
    private Integer deleteStatus;

}
