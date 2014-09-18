package com.softserveinc.softtour.service.Impl;


import com.softserveinc.softtour.repository.RoleDao;
import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao (RoleDao roleDao){this.roleDao = roleDao;}
    @Override
    public void save(String name){roleDao.save(name);}
    @Override
    public void update(long id, String name){roleDao.update(id, name);}
    @Override
    public void delete(long id){roleDao.delete(id);}
    @Override
    public Role findById(long id){return roleDao.findById(id);}
    @Override
    public List<Role> findByName(String name){return roleDao.findByName(name);}
    @Override
    public List<Role> getAll(){return roleDao.getAll();}
}
