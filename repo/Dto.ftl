package ${(dtoPackage)!};

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import ${(domainPackage)!}.${(entity)!};

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${(entity)!}Dto  {

    <#list items as item>
    private ${item.javaType} ${(item.name)!};
    </#list>

    public static ${(entity)!}Dto toDto(${(entity)!} ${entity?uncap_first}) {
        ${(entity)!}Dto dto = new ${(entity)!}Dto();
        <#list items as item>
        dto.set${(item.name?cap_first)!}(${entity?uncap_first}.get${(item.name?cap_first)!}());
        </#list>
        return dto;
    }

    public static ${(entity)!} toEntity(${(entity)!}Dto dto){
        ${(entity)!} entity = new ${(entity)!}();
        <#list items as item>
        entity.set${(item.name?cap_first)!}(dto.get${(item.name?cap_first)!}());
        </#list>

        return entity;
    }


}
