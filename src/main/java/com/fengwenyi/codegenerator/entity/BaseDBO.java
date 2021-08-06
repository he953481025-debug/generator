package com.fengwenyi.codegenerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 基础DBO
 *
 * @author: create by lizhongbao
 * @date: 2020/10/21
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BaseDBO extends BaseTenant {

    /**
     * 主键
     * TODO 统一用mybatis plus之后应该把@Id注解去掉
     **/
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

}
