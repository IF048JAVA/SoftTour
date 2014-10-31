package com.softserveinc.softtour.service;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

/**
 * @author Andriy
 * Contains the methods for work with table Favorite in the SoftTour database
 */
public interface FavoriteService {
	
	/**
	 * Saves the object favorite to the table Favorite
	 */
	public void save(Favorite favorite);
	
	/**
	 * Updates the object favorite with the specified id
	 * @param id - id of the object favorite which will be updated
	 * @param favorite - it's the object with the new values
	 */
	public void update(long id, Favorite favorite);
	
	/**
	 *  Deletes the object favorite with the specified id
	 *  id - id of the object favorite which will be deleted
	 */
	public void delete(long id);
	
	/**
	 *  Returns the object favorite with the specified id
	 *  id - id of the object favorite which will be returned
	 */
	public Favorite findById(long id);

    /**
     * Returns the list of the favorite's objects from the specified User
     */
    public List<Favorite> findByUser(User user);
	
	/**
	 * Returns the list of the favorite's objects with the specified parameters
	 */
	public List<Favorite> findByAnyParameters(long id, Date date, User user, Tour tour);
	
	/**
	 *  Returns the list of all favorite's objects which are contained in the table Favorite
	 */
	public List<Favorite> findAll();

    public Favorite findByUserAndTour (User user, Tour tour);
	
}
