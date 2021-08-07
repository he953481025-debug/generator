package ${cfg.daoImplPackage};

import ${package.Entity}.${entity};
import ${cfg.daoPackage}.${cfg.daoName};
import ${package.Mapper}.${table.mapperName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Repository;

/**
 * <p>
 * ${table.comment!} dao实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${cfg.daoImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${cfg.daoName} {

}
</#if>
