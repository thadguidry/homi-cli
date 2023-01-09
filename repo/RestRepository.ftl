package ${(pkg)!};


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ${(domainPackage)!}.${(entity)!};

public interface ${(entity)!}Repository extends JpaRepository<${(entity)!}, ${(keyJavaType)!}>, JpaSpecificationExecutor<${(entity)!}> {
}
