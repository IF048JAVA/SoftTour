package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andriy
 * Contains the methods for work with table Favorite in the SoftTour database
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
	
	/**
	 * Saves the object favorite to the table Favorite
	 */
	//@Query
	public void save(Date date, User user, Tour tour);
	
	/**
	 *  Updates the object favorite with the specified id
	 *  id - id of the object favorite which will be updated
	 */
	//@Query
	public void update(long id, Date date, User user, Tour tour);
	
	/**
	 *  Deletes the object favorite with the specified id
	 *  id - id of the object favorite which will be deleted
	 */
	//@Query
	public void delete(long id);
	
	/**
	 *  Returns the object favorite with the specified id
	 *  id - id of the object favorite which will be returned
	 */
	public Favorite findById(long id);
	
	/**
	 * Returns the list of the objects favorite which contain the specified date
	 * @param date - date of the objects which will be added to the list
	 */
	public List<Favorite> findByDate(Date...date);
	
	/**
	 *  Returns the list of the objects favorite which contain the specified object user 
	 */
	public List<Favorite> findByUser (User...user);
	
	/**
	 *  Returns the list of the objects favorite which contain the specified object tour 
	 */
	public List<Favorite> findByTour (Tour...tour);
	
	/**
	 *  Returns the list of all objects favorite which are contained in the table Favorite
	 */
	public List<Favorite> getAll();	
	
}