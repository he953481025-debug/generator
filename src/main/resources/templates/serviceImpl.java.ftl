package ${package.ServiceImpl};


import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import ${cfg.voPackage}.${cfg.voName};
import ${cfg.paramPackage}.${cfg.paramName};
import ${cfg.daoPackage}.${cfg.daoName};
import ${cfg.convertPackage}.${cfg.convertName};

import javax.annotation.Resource;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Resource
    private ${cfg.daoName} ${cfg.camelName}Dao;

    @Resource
    private ${cfg.convertName} ${cfg.camelName}Convert;

    @Override
    public Boolean insert(${cfg.paramName} param){
        return ${cfg.camelName}Dao.save(${cfg.camelName}Convert.paramConvertToDbo(param));
    }

    @Override
    public Boolean update(${cfg.paramName} param){
        return ${cfg.camelName}Dao.updateById(${cfg.camelName}Convert.paramConvertToDbo(param));
    }

    @Override
    public ${cfg.voName} findById(Long id){
        return ${cfg.camelName}Convert.dboConvertToVo(${cfg.camelName}Dao.getById(id));
    }

    @Override
    public Boolean deleteById(Long id){
        return ${cfg.camelName}Dao.removeById(id);
    }

}
</#if>
