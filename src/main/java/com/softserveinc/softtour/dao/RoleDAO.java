package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Role;

import java.awt.List;

public interface RoleDAO {
    public void saveRole(String name);
    public void updateRole(long id, String name);
    public void deleteRole(long id);
    public Role findById(long id);
    public List<Role> findByName(String name);
    public List<Role> getAll();
}
