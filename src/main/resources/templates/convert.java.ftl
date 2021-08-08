package ${cfg.convertPackage};

import ${cfg.paramPackage}.${cfg.entityName}Param;
import ${cfg.voPackage}.${cfg.entityName}VO;
import ${package.Entity}.${entity};
import org.mapstruct.Mapper;

/**
 * <p>
 * ${table.comment!} 实体属性转换类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper(componentModel = "spring")
public interface ${cfg.convertName}  {

   ${entity} paramConvertToDbo(${cfg.entityName}Param param);

   ${cfg.entityName}VO dboConvertToVo(${entity} dbo);
}

