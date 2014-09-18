package com.softserveinc.softtour.dao.Impl;

import com.softserveinc.softtour.dao.RoleDao;
import com.softserveinc.softtour.entity.Role;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import java.util.List;

public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {
    @Override
    public void save(String name){
        Role role = new Role(name);
        getHibernateTemplate().save(role);
    }
    @Override
    public void update(long id, String name){
        Role role = (Role) getHibernateTemplate().get(Role.class, id);
        if (role != null) {
            role.setName(name);
            getHibernateTemplate().update(role);
        } else {
            System.err.println("Error! \n you don't have role");
        }
    }
    @Override
    public void delete(long id){
        Role role = (Role) getHibernateTemplate().get(Role.class, id);
        if (role != null) {
            getHibernateTemplate().delete(role);
        } else {
            System.err.println("Error! \n you don't have role");
        }
    }
    @Override
    public Role findById(long id){
        Role role = (Role) getHibernateTemplate().get(Role.class, id);
        return role;
    }
    @Override
    public List<Role> findByName(String name){
        List<Role> list = (List<Role>) getHibernateTemplate().find("FROM Role WHERE name = ?", name);
        return list;
    }
    @Override
    public List<Role> getAll(){
        List<Role> list = (List<Role>) getHibernateTemplate().find("FROM Role");
        return list;
    }


}
