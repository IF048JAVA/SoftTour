package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Andriy
 * Contains the methods for work with table Favorite in the SoftTour database
 */
public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
	
	/**
	 *  Updates the object favorite with the specified id
	 * @param id - id of the object favorite which will be updated
	 * @param date - updated date
	 * @param user - updated user
	 * @param tour - updated tour
	 */
	@Modifying
	@Query("UPDATE Favorite SET date = ?2, user_id = ?3, tour_id = ?4 WHERE id = ?1")
	public void update(long id, Date date, User user, Tour tour);

    /**
     * Returns the list of the favorite's objects from the specified User
     */
    public List<Favorite> findByUser(User user);
	
	/**
	 * Returns the list of the favorite's objects with the specified parameters
	 */
	public List<Favorite> findByIdOrDateOrUserOrTour(
			long id, Date date, User user, Tour tour);
	
}