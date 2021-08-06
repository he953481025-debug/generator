package com.fengwenyi.codegenerator.bo;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-14
 */
@Data
@Accessors(chain = true)
public class CodeGeneratorBo {

    // 包名
    private String packageName;

    // 数据库类型
    private DbType dbType=DbType.MYSQL;
    // 数据库连接地址
    private String dbUrl="10.0.10.13:23100";
    // 数据库名称
    private String username="root";
    // 数据库密码
    private String password="a123456";
    // 数据库驱动
    private String driver;

    // 表名
    private String [] tableNames;
    // 表前缀
    private String [] tablePrefixes;
    // 字段前缀
    private String [] fieldPrefixes;
    // 排出表的表名
    private String [] excludeTableNames;

    // 作者
    private String author="brandon";
    // 输入目录
    private String outDir;

    // 实体类包名
    private String packageEntity="entity.dbo";
    // mapper包名
    private String packageMapper="dao.mysql.mapper";
    // mapper XML目录名
    private String packageMapperXml;
    // service包名
    private String packageService="service";
    // serviceImpl包名
    private String packageServiceImpl="service.impl";
    // controller包名
    private String packageController="controller";

    // 实体类文件名格式
    private String fileNamePatternEntity="%sDBO";
    // mapper文件名格式
    private String fileNamePatternMapper="%sMapper";
    // mapper XML文件名格式
    private String fileNamePatternMapperXml;
    // service文件名格式
    private String fileNamePatternService="I%sService";
    // serviceImpl文件名格式
    private String fileNamePatternServiceImpl="%sServiceImpl";
    // controller文件名格式
    private String fileNamePatternController="%sController";

    // 逻辑删除字段
    private String fieldLogicDelete="delete_status";
    // 乐观锁字段
    private String fieldVersion="version";

    // 模版引擎
    private String templateEngine;
    // 是否支持Swagger
    private Boolean swaggerSupport=true;
    // JDK版本
    private String jdkVersion;

    // 是否开启Lombok
    private Boolean lombokModel=true;
    // 是否使用构建者模型
    private Boolean lombokChainModel=true;

}
