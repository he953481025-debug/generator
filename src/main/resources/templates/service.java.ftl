package ${package.Service};



/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName}  {

  Boolean insert(${cfg.entityName}Param param);

  Boolean update(${cfg.entityName}Param param);

  ${cfg.entityName}VO findById(Long id);

  Boolean deleteById(Long id);
}
</#if>
