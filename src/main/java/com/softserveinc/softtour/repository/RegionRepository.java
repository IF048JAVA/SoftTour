package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
