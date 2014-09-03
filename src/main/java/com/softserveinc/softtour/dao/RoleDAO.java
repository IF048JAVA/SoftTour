package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Role;

import java.util.List;

public interface RoleDao {
    public void save(String name);
    public void update(long id, String name);
    public void delete(long id);
    public Role findById(long id);
    public List<Role> findByName(String name);
    public List<Role> getAll();
}
