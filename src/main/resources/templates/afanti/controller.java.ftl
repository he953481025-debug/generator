package ${package.Controller};

import ${cfg.voPackage}.${cfg.voName};
import ${cfg.paramPackage}.${cfg.paramName};
import ${package.Service}.${table.serviceName};
import com.afanticar.common.core.api.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
<#if swagger2>
import io.swagger.annotations.*;
</#if>

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Validated
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if swagger2>
@Api(tags="${table.comment!} 前端控制器")
</#if>
@RequestMapping("/sysAdmin<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${cfg.camelName}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Resource
    private ${table.serviceName} ${cfg.camelName}Service;

    @ApiOperation("新增${table.comment!}")
    @PostMapping
    public CommonResult<Boolean> insert(@RequestBody @Valid ${cfg.entityName}Param param) {
        return CommonResult.success(${cfg.camelName}Service.insert(param));
    }

    @ApiOperation("更新${table.comment!}")
    @PutMapping
    public CommonResult<Boolean> update(@RequestBody @Valid ${cfg.entityName}Param param) {
        return CommonResult.success(${cfg.camelName}Service.update(param));
    }

    @ApiOperation("根据id查询${table.comment!}")
    @GetMapping("/{id}")
    @ApiImplicitParam(value = "${table.comment!}id", dataTypeClass = Long.class, example = "1")
    public CommonResult<${cfg.voName}> findById(@NotNull(message="id不能为空") @PathVariable("id")Long id){
        return CommonResult.success(${cfg.camelName}Service.findById(id));
    }

    @ApiOperation("根据id删除${table.comment!}")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(value = "${table.comment!}id", dataTypeClass = Long.class, example = "1")
    public CommonResult<Boolean> deleteById(@NotNull(message="id不能为空") @PathVariable("id")Long id){
        return CommonResult.success(${cfg.camelName}Service.deleteById(id));
    }
}
</#if>
