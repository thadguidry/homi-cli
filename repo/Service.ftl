package ${(servicePackage)!};

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${(exceptionPackage)!}.NoDataFoundProblem;

import  ${(dtoPackage)!}.${(entity)!}Dto;
import ${(pkg)!}.${(entity)!}Repository;
import ${(domainPackage)!}.${(entity)!};

@Service
@Slf4j
@RequiredArgsConstructor
public class ${(entity)!}Service  {

    private final ${(entity)!}Repository ${entity?uncap_first}Repository;

    @Transactional(readOnly = true)
    public ${(entity)!}Dto findById(${(keyJavaType)!} id) {
        return ${entity?uncap_first}Repository.findById(id)
                .map(${entity?uncap_first} -> toDto(${entity?uncap_first} , new ${(entity)!}Dto()))
                .orElseThrow(() -> new NoDataFoundProblem());
    }

    @Transactional(readOnly = true)
    public Page<${(entity)!}Dto> search(Specification<${(entity)!}> spec, Pageable page) {
        return ${entity?uncap_first}Repository.findAll(spec, page).map(
            ${entity?uncap_first} -> toDto(${entity?uncap_first} , new ${(entity)!}Dto())
        );
    }

    @Transactional(readOnly = true)
    public Slice<${(entity)!}Dto> searchBySlice(Specification<${(entity)!}> spec, Pageable page) {
        return ${entity?uncap_first}Repository.findAll(spec, page).map(
            ${entity?uncap_first} -> toDto(${entity?uncap_first}, new ${(entity)!}Dto())
        );
    }

    @Transactional
    public ${(entity)!}Dto save(final ${(entity)!}Dto ${entity?uncap_first}Dto) {
        final ${(entity)!} ${entity?uncap_first} = new ${(entity)!}();
        toEntity(${entity?uncap_first}Dto, ${entity?uncap_first});
        return toDto(${entity?uncap_first}Repository.save(${entity?uncap_first}), new ${(entity)!}Dto());
    }

    private ${(entity)!}Dto toDto(final ${(entity)!} ${entity?uncap_first} , final ${(entity)!}Dto ${entity?uncap_first}Dto) {
        <#list items as item>
        ${entity?uncap_first}Dto.set${(item.name?cap_first)!}(${entity?uncap_first}.get${(item.name?cap_first)!}());
        </#list>
        return ${entity?uncap_first}Dto;
    }

    private ${(entity)!} toEntity(final ${(entity)!}Dto ${entity?uncap_first}Dto, final ${(entity)!} ${entity?uncap_first}){
        <#list items as item>
        ${entity?uncap_first}.set${(item.name?cap_first)!}(${entity?uncap_first}Dto.get${(item.name?cap_first)!}());
        </#list>
        return ${entity?uncap_first};
    }

}
