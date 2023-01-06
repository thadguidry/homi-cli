package com.homihq.blog

import ${package};
import org.springframework.data.jpa.repository.JpaRepository;

public interface ${pojoName}Repository extends JpaRepository<${pojoName}, Long>, CustomUserRepository {

}
