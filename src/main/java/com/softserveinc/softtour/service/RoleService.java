package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.Role;

import java.util.List;

public interface RoleService {
    public void save(Role role);
    public void delete(long id);
    public Role findById(long id);
    public Role findByName(String name);
    public List<Role> getAll();
}
