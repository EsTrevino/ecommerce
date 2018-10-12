package com.ectrevin.ecommerce.repository;

import com.ectrevin.ecommerce.model.Role;
import com.ectrevin.ecommerce.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);




}
