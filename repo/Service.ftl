package ${(servicePackage)!};

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


import ${(pkg)!}.${(entity)!}Repository;
import ${(domainPackage)!}.${(entity)!};

@Service
@Slf4j
@RequiredArgsConstructor
public class ${(entity)!}Service  {

    private final ${(entity)!}Repository ${entity?uncap_first}Repository;

    @Transactional(readOnly = true)
    public Optional<${(entity)!}> findById(${(keyJavaType)!} id) {
        return ${entity?uncap_first}Repository.findById(id);
    }
}
