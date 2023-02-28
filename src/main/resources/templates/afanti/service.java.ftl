package ${package.Service};

import ${cfg.voPackage}.${cfg.voName};
import ${cfg.paramPackage}.${cfg.paramName};
import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

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
public interface ${table.serviceName} extends IService<${entity}> {

    /**
     *  新增${table.comment!}
     * @author ${author}
     * @since ${date}
     * @return java.lang.Boolean
     * @param param 新增${table.comment!}入参
     */
    Boolean insert(${cfg.paramName} param);

    /**
     *  更新${table.comment!}
     * @author ${author}
     * @since ${date}
     * @return java.lang.Boolean
     * @param param 更新${table.comment!}入参
     */
    Boolean update(${cfg.paramName} param);

    /**
     *  通过id查询${table.comment!}
     * @author ${author}
     * @since ${date}
     * @return ${cfg.voPackage}.${cfg.voName}
     * @param id ${table.comment!}的id
     */
    ${cfg.voName} findById(Serializable id);

    /**
     *  通过删除查询${table.comment!}
     * @author ${author}
     * @since ${date}
     * @return java.lang.Boolean
     * @param id ${table.comment!}的id
     */
    Boolean deleteById(Serializable id);
}
</#if>
