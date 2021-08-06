package com.fengwenyi.codegenerator.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础租户类
 *
 * @Author: lizhongbao
 * @Date: 2020/10/23 10:36
 */
@Data
public class BaseTenant implements Serializable {

    /**
     * 租户id
     * （hidden = true）因为租户id统一放在header，所以接口文档不需要生成这个字段
     **/
    private Integer tenantId;

}
