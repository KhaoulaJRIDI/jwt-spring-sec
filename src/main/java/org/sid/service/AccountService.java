package org.sid.service;

import java.util.Collection;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(String username,String password,String confirmedPassword);
    public AppRole save(AppRole role);
    public AppUser saveUser(AppUser user);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);
    public Collection<AppUser> getAllUsers();
}
