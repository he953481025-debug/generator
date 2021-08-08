package com.fengwenyi.codegenerator.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.fengwenyi.codegenerator.Config;
import com.fengwenyi.codegenerator.bo.CodeGeneratorBo;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

/**
 * @author Erwin Feng
 * @since 2019-04-17 12:04
 */
public class CommonUtils {

    /**
     * 数据库连接信息
     * @param bo {@link CodeGeneratorBo}
     * @return DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig(CodeGeneratorBo bo) {
        return new DataSourceConfig()
                .setDbType(bo.getDbType())
                .setUrl(bo.getDbUrl())
                .setUsername(bo.getUsername())
                .setPassword(bo.getPassword())
                .setDriverName(bo.getDriver())
                ;
    }

    // 配置
    private static GlobalConfig globalConfig(CodeGeneratorBo bo) {
        String outDir = bo.getOutDir();
        if (!StringUtils.hasText(outDir)) {
            outDir = Config.OUTPUT_DIR;
        }else {
            Config.OUTPUT_DIR=outDir;
        }
        DateType dateType = DateType.TIME_PACK;
        if (!"8".equalsIgnoreCase(bo.getJdkVersion())) {
            dateType = DateType.ONLY_DATE;
        }
        return new GlobalConfig()
                .setAuthor(bo.getAuthor())
                .setOutputDir(outDir+"/src/main/java")
                .setFileOverride(true) // 是否覆盖已有文件
                //.setOpen(true) // 是否打开输出目录
                .setDateType(dateType) // 时间采用java 8，（操作工具类：JavaLib => DateTimeUtils）
                .setActiveRecord(false)// 不需要特性的请改为false
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(false)// XML ResultMap
                .setBaseColumnList(false)// XML columList
                .setKotlin(false) //是否生成 kotlin 代码
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName(bo.getFileNamePatternEntity())
                .setMapperName(bo.getFileNamePatternMapper())
                .setXmlName(bo.getFileNamePatternMapperXml())
                .setServiceName(bo.getFileNamePatternService())
                .setServiceImplName(bo.getFileNamePatternServiceImpl())
                .setControllerName(bo.getFileNamePatternController())
                .setIdType(IdType.ASSIGN_ID) // 主键类型
                .setSwagger2(bo.getSwaggerSupport() != null && bo.getSwaggerSupport()) // model swagger2
                ;
//                if (!serviceNameStartWithI)
//                    config.setServiceName("%sService");
    }


    private static StrategyConfig strategyConfig(CodeGeneratorBo bo) {

        return new StrategyConfig()
                .setRestControllerStyle(true)
                .setSuperEntityClass("SpecialBaseDBO")
                .setSuperEntityColumns("id","delete_status","create_time","update_time","create_user_id","update_user_id","tenant_id")
                .setEntitySerialVersionUID(false)
                .setCapitalMode(true) // 全局大写命名 ORACLE 注意
                .setSkipView(false) // 是否跳过视图
                //.setDbColumnUnderline(true)
                .setTablePrefix(bo.getTablePrefixes())// 此处可以修改为您的表前缀(数组)
                .setFieldPrefix(bo.getFieldPrefixes()) // 字段前缀
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                .setInclude(bo.getTableNames())//修改替换成你需要的表名，多个表名传数组
                .setExclude(bo.getExcludeTableNames()) // 排除生成的表
                .setEntityLombokModel(bo.getLombokModel() != null && bo.getLombokModel()) // lombok实体
                .setChainModel(bo.getLombokChainModel() != null && bo.getLombokChainModel()) // 【实体】是否为构建者模型（默认 false）
                .setEntityColumnConstant(false) // 【实体】是否生成字段常量（默认 false）// 可通过常量名获取数据库字段名 // 3.x支持lambda表达式
                .setLogicDeleteFieldName(bo.getFieldLogicDelete()) // 逻辑删除属性名称
                .setVersionFieldName(bo.getFieldVersion()) // 乐观锁字段名
                .setEntityTableFieldAnnotationEnable(false) // 开启实体字段注解
                ;
    }

    // 包信息配置
    private static PackageConfig packageConfig(CodeGeneratorBo bo) {
        return new PackageConfig()
                .setParent(bo.getPackageName())
                .setController(bo.getPackageController())
                .setEntity(bo.getPackageEntity())
                .setMapper(bo.getPackageMapper())
                .setXml(bo.getPackageMapperXml())
                .setService(bo.getPackageService())
                .setServiceImpl(bo.getPackageServiceImpl())
                ;
    }

    /**
     *
     * @param packageConfig
     * @return
     */
    private static InjectionConfig injectionConfig(final PackageConfig packageConfig) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

                // to do nothing
            }
            @Override
            public void initTableMap(TableInfo tableInfo){
                Map<String, Object> map = new HashMap<>();
                String capitalFirst = NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableInfo.getName()));
                map.put("camelName",NamingStrategy.underlineToCamel(tableInfo.getName()));
                map.put("entityName", capitalFirst);
                map.put("paramPackage", packageConfig.getParent()+".entity.param");
                map.put("voPackage", packageConfig.getParent()+".entity.vo");
                map.put("daoPackage",packageConfig.getParent()+".repository.mysql");
                map.put("daoImplPackage",packageConfig.getParent()+".repository.mysql.impl");
                map.put("daoName",String.format("I%sDao",capitalFirst));
                map.put("daoImplName",String.format("%sDaoImpl",capitalFirst));
                map.put("convertName",String.format("%sConvert",capitalFirst));
                map.put("voName",String.format("%sVO",capitalFirst));
                map.put("paramName",String.format("%sParam",capitalFirst));
                map.put("convertPackage",packageConfig.getParent()+".convert");
                this.setMap(map);
            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        fileOutConfigList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                if (StringUtils.isEmpty(packageConfig.getModuleName())) {
                    return Config.OUTPUT_DIR + "/src/main/resources/mapper/" + tableInfo.getXmlName() + StringPool.DOT_XML;
                }else {
                    return Config.OUTPUT_DIR + "/src/main/resources/mapper/" + packageConfig.getModuleName() + "/" + tableInfo.getXmlName() + StringPool.DOT_XML;
                }
            }
        });

        fileOutConfigList.add(new FileOutConfig("/templates/dao.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String parent = packageConfig.getParent();
                String parentPath = parent.replace('.', '/');
                // 自定义输入文件名称
                String daoFile = String.format(Config.OUTPUT_DIR + "/src/main/java/"+parentPath+"/repository/mysql" + File.separator + "I%sDao" + StringPool.DOT_JAVA
                        , NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableInfo.getName())));
                return daoFile;
            }
        });
        fileOutConfigList.add(new FileOutConfig("/templates/daoImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String parent = packageConfig.getParent();
                String parentPath = parent.replace('.', '/');
                // 自定义输入文件名称
                String daoFile = String.format(Config.OUTPUT_DIR + "/src/main/java/"+parentPath+"/repository/mysql/impl" + File.separator + "%sDaoImpl" + StringPool.DOT_JAVA
                        , NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableInfo.getName())));
                return daoFile;
            }
        });

        fileOutConfigList.add(new FileOutConfig("/templates/param.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String parent = packageConfig.getParent();
                String parentPath = parent.replace('.', '/');
                // 自定义输入文件名称
                String daoFile = String.format(Config.OUTPUT_DIR + "/src/main/java/"+parentPath+"/entity/param" + File.separator + "%sParam" + StringPool.DOT_JAVA
                        , NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableInfo.getName())));
                return daoFile;
            }
        });

        fileOutConfigList.add(new FileOutConfig("/templates/vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String parent = packageConfig.getParent();
                String parentPath = parent.replace('.', '/');
                // 自定义输入文件名称
                String daoFile = String.format(Config.OUTPUT_DIR + "/src/main/java/"+parentPath+"/entity/vo" + File.separator + "%sVO" + StringPool.DOT_JAVA
                        , NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableInfo.getName())));
                return daoFile;
            }
        });

        fileOutConfigList.add(new FileOutConfig("/templates/convert.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String parent = packageConfig.getParent();
                String parentPath = parent.replace('.', '/');
                // 自定义输入文件名称
                String daoFile = String.format(Config.OUTPUT_DIR + "/src/main/java/"+parentPath+"/convert" + File.separator + "%sConvert" + StringPool.DOT_JAVA
                        , NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableInfo.getName())));
                return daoFile;
            }
        });

        injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    /**
     * 获取模板引擎
     * @return 模板引擎 {@link AbstractTemplateEngine}
     */
    private static AbstractTemplateEngine getTemplateEngine(CodeGeneratorBo bo) {
        String templateEngine = bo.getTemplateEngine();
        switch (templateEngine) {
            case "velocity":
                return new VelocityTemplateEngine();
            case "freemarker":
                return new FreemarkerTemplateEngine();
            case "beetl":
                return new BeetlTemplateEngine();
        }
        return new VelocityTemplateEngine();
    }

    public static void execute(CodeGeneratorBo bo) {
        GlobalConfig globalConfig = globalConfig(bo);
        //DataSourceConfig dataSourceConfig = dataSourceConfig(dbType, dbUrl, username, password, driver);
        DataSourceConfig dataSourceConfig = dataSourceConfig(bo);
        StrategyConfig strategyConfig = strategyConfig(bo);
        PackageConfig packageConfig = packageConfig(bo);
        InjectionConfig injectionConfig = injectionConfig(packageConfig);
        AbstractTemplateEngine templateEngine = getTemplateEngine(bo);
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController("templates/controller.java");
        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(templateEngine)
                .setTemplate(templateConfig)
                .setCfg(injectionConfig)
                .execute();
    }

}
