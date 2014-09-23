package com.softserveinc.softtour.service.Impl;



import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.repository.RoleRepository;
import com.softserveinc.softtour.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;


    @Override
    public void save(Role role){roleRepository.save(role);}
    @Override
    public void update(long id, Role role){roleRepository.saveAndFlush(role);}
    @Override
    public void delete(long id){roleRepository.delete(id);}
    @Override
    public Role findById(long id){return roleRepository.findOne(id);}

    @Override
    public List<Role> getAll(){return roleRepository.findAll();}
}
