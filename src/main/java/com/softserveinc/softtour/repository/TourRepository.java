package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public interface TourRepository extends JpaRepository<Tour, Long> {
}
