package ${(controllerPackage)!};

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;

import com.turkraft.springfilter.boot.Filter;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import javax.validation.Valid;


import ${(domainPackage)!}.${(entity)!};
import  ${(dtoPackage)!}.${(entity)!}Dto;
import ${(servicePackage)!}.${(entity)!}Service;

@RestController
@RequestMapping(value = "/${entity?uncap_first}s", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class ${(entity)!}Controller  {

    private final ${(entity)!}Service ${entity?uncap_first}Service;

    @GetMapping("/{id}")
    public ${(entity)!}Dto findById(@PathVariable final ${(keyJavaType)!} id) {
        return ${entity?uncap_first}Service.findById(id);
    }

    @GetMapping(value = "/search")
    public Page<${(entity)!}Dto> search(@Filter Specification<${(entity)!}> spec, Pageable page) {
        return ${entity?uncap_first}Service.search(spec, page);
    }

    @GetMapping(value = "/search/sliced")
    public Slice<${(entity)!}Dto> searchBySlice(@Filter Specification<${(entity)!}> spec, Pageable page) {
        return ${entity?uncap_first}Service.searchBySlice(spec, page);
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ${(entity)!}Dto save(@RequestBody @Valid final ${(entity)!}Dto ${entity?uncap_first}Dto) {
        return ${entity?uncap_first}Service.save(${entity?uncap_first}Dto);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable final ${(keyJavaType)!} id,
        @RequestBody @Valid final ${(entity)!}Dto ${entity?uncap_first}Dto) {
        ${entity?uncap_first}Service.update(id,  ${entity?uncap_first}Dto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.MOVED_PERMANENTLY)
    public void delete(@PathVariable final ${(keyJavaType)!} id) {
        ${entity?uncap_first}Service.delete(id);
    }
}
