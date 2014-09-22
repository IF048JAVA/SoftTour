package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query("UPDATE Role SET name=?2 WHERE id=?1")
    public void update(long id, String name);

    public List<Role> findByIdOrName(long id, String name);
}
