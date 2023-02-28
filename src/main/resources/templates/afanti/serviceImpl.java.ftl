package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

import java.io.Serializable;

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
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public Boolean insert(${cfg.paramName} param) {
      ${entity} entity = BeanUtil.toBean(param, ${entity}.class);
      return SqlHelper.retBool(baseMapper.insert(entity));
    }

    @Override
    public Boolean update(${cfg.paramName} param) {
        ${entity} entity = BeanUtil.toBean(param, ${entity}.class);
        return SqlHelper.retBool(baseMapper.updateById(entity));
    }

    @Override
    public ${cfg.voName} findById(Serializable id) {
        ${entity} entity = baseMapper.selectById(id);
        return BeanUtil.toBean(entity, ${cfg.voName}.class);
    }

    @Override
    public Boolean deleteById(Serializable id) {
        return SqlHelper.retBool(baseMapper.deleteById(id));
    }
}
</#if>
