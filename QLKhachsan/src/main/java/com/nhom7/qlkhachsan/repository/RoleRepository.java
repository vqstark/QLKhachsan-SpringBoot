package com.nhom7.qlkhachsan.repository;

import com.nhom7.qlkhachsan.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
