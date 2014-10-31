package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {

    public Region findByName (String name);

    Region findByItTourId(long itTourId);
}
