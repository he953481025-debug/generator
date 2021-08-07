package ${package.ServiceImpl};


import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;

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
 private ${cfg.daoName} dao;

 @Resource
 private ${cfg.convertName} convert;

 @Override
 public Boolean insert(${cfg.entityName}Param param){
 return dao.save(convert.paramConvertToDbo(param));
 }

 @Override
 public Boolean update(${cfg.entityName}Param param){
 return dao.update(convert.paramConvertToDbo(param));
 }

 @Override
 public ${cfg.entityName}VO findById(Long id){
 return convert.dboConvertToVo(dao.getById(id));
 }

 @Override
 public Boolean deleteById(Long id){
 return dao.removeById(id);
 }

}
</#if>
