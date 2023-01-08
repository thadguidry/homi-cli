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
import static ${(dtoPackage)!}.${(entity)!}Dto.toDto;
import static ${(dtoPackage)!}.${(entity)!}Dto.toEntity;

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
                .map(${entity?uncap_first} -> toDto(${entity?uncap_first}))
                .orElseThrow(() -> new NoDataFoundProblem());
    }

    @Transactional(readOnly = true)
    public Page<${(entity)!}Dto> search(Specification<${(entity)!}> spec, Pageable page) {
        return ${entity?uncap_first}Repository.findAll(spec, page).map(
            ${entity?uncap_first} -> toDto(${entity?uncap_first})
        );
    }

    @Transactional(readOnly = true)
    public Slice<${(entity)!}Dto> searchBySlice(Specification<${(entity)!}> spec, Pageable page) {
        return ${entity?uncap_first}Repository.findAll(spec, page).map(
            ${entity?uncap_first} -> toDto(${entity?uncap_first})
        );
    }

}
