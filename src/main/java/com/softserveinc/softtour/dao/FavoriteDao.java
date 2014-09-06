package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

import java.sql.Date;
import java.util.List;

/**
 * @author Andriy
 * Contains the methods for work with table Favorite in the SoftTour database
 */
public interface FavoriteDao {
	
	/**
	 * Saves the object favorite to the table Favorite
	 */
	public void save(Date date, User user, Tour tour);
	
	/**
	 *  Updates the object favorite with the specified id
	 *  id - id of the object favorite which will updated
	 */
	public void update(long id, Date date, User user, Tour tour);
	
	/**
	 *  Deletes the object favorite with the specified id
	 *  id - id of the object favorite which will deleted
	 */
	public void delete(long id);
	
	/**
	 *  Returns the object favorite with the specified id
	 *  id - id of the object favorite which will returned
	 */
	public Favorite findById(long id);
	
	/**
	 *  Returns the list of the objects favorite which contain the specified object user 
	 */
	public List<Favorite> findByUser (User user);
	
	/**
	 *  Returns the list of the objects favorite which contain the specified object tour 
	 */
	public List<Favorite> findByTour (Tour tour);
	
	/**
	 *  Returns the list of all objects favorite which are contained in the table Favorite
	 */
	public List<Favorite> getAll();	
	
}