package ${(dtoPackage)!};

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${(entity)!}Dto  {

    <#list items as item>
    private ${item.javaType} ${(item.name)!};
    </#list>

}
