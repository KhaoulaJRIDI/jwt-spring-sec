package org.sid.dao;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    public AppRole findByRoleName(String roleName);
}
