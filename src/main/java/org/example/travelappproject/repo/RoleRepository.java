package org.example.travelappproject.repo;

import org.example.travelappproject.entity.Role;
import org.example.travelappproject.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}