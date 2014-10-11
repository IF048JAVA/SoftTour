package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.repository.RoleRepository;
import com.softserveinc.softtour.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role){ return roleRepository.save(role);}

    @Override
    public void delete(long id){roleRepository.delete(id);}

    @Override
    public Role findById(long id){return roleRepository.findOne(id);}

    @Override
    public Role findByName(String name){return roleRepository.findByName(name);}

    @Override
    public List<Role> getAll(){return roleRepository.findAll();}
}
