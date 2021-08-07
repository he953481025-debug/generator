package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

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
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
   @Resource
   private ${table.serviceName} service;

   @ApiOperation("新增${table.comment!}")
   @PostMapping
   public BaseResponse<Boolean> insert(@RequestBody @Valid ${cfg.entityName}Param param) {
    return ResponseUtil.returnSuccess(service.insert(param));
    }

   @ApiOperation("更新${table.comment!}")
   @PutMapping
   public BaseResponse<Boolean> update(@RequestBody @Valid ${cfg.entityName}Param param) {
    return ResponseUtil.returnSuccess(service.update(param));
    }

  @ApiOperation("根据id查询${table.comment!}")
  @GetMapping("/{id}")
  public BaseResponse<${cfg.entityName}VO> findById(@PathVariable("id")Long id){
   return ResponseUtil.returnSuccess(service.findById(id));
   }

   @ApiOperation("根据id删除${table.comment!}")
   @DeleteMapping("/{id}")
   public BaseResponse<${cfg.entityName}VO> deleteById(@PathVariable("id")Long id){
    return ResponseUtil.returnSuccess(service.deleteById(id));
    }
}
</#if>
