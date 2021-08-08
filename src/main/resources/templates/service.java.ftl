package ${package.Service};

import ${cfg.voPackage}.${cfg.voName};
import ${cfg.paramPackage}.${cfg.paramName};

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

    Boolean insert(${cfg.paramName} param);

    Boolean update(${cfg.paramName} param);

    ${cfg.voName} findById(Long id);

    Boolean deleteById(Long id);
}
</#if>
