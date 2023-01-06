package ${(pkg)!};


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ${(domainPackage)!}.${(entity)!};


public interface ${(entity)!}Repository extends JpaRepository<${(entity)!}, ${(pk)!}>, JpaSpecificationExecutor<${(entity)!}> {
}
