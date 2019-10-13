package com.phamtrong.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamtrong.app.entity.Role;
import com.phamtrong.app.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(RoleName roleName);

}
