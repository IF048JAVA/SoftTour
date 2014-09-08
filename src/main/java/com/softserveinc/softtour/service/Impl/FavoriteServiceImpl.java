package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.dao.FavoriteDao;
import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.FavoriteService;

/**
 * @author Andriy
 * Contains the methods for work with table Favorite in the SoftTour database
 */
public class FavoriteServiceImpl implements FavoriteService{

	private FavoriteDao favoriteDao;
	
	/**
	 * Sets the favoriteDao object
	 * @param favoriteDao - object of the class FavoriteDaoImpl
	 */
	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}
	
	/**
	 * Saves the object favorite to the table Favorite
	 */
	@Override
	public void save(Date date, User user, Tour tour) {
		favoriteDao.save(date, user, tour);
	}

	/**
	 *  Updates the object favorite with the specified id
	 *  id - id of the object favorite which will be updated
	 */
	@Override
	public void update(long id, Date date, User user, Tour tour) {
		favoriteDao.update(id, date, user, tour);
	}
	
	/**
	 *  Deletes the object favorite with the specified id
	 *  id - id of the object favorite which will be deleted
	 */
	@Override
	public void delete(long id) {
		favoriteDao.delete(id);
	}

	/**
	 *  Returns the object favorite with the specified id
	 *  id - id of the object favorite which will be returned
	 */
	@Override
	public Favorite findById(long id) {
		return favoriteDao.findById(id);
	}
	
	/**
	 * Returns the list of the objects favorite which contain the specified date
	 * @param date - date of the objects which will be added to the list
	 */
	@Override
	public List<Favorite> findByDate(Date... date) {
		return favoriteDao.findByDate(date);
	}

	/**
	 *  Returns the list of the objects favorite which contain the specified object user 
	 */
	@Override
	public List<Favorite> findByUser(User...user) {
		return favoriteDao.findByUser(user);
	}

	/**
	 *  Returns the list of the objects favorite which contain the specified object tour 
	 */
	@Override
	public List<Favorite> findByTour(Tour...tour) {
		return favoriteDao.findByTour(tour);
	}
	
	/**
	 *  Returns the list of all objects favorite which are contained in the table Favorite
	 */
	@Override
	public List<Favorite> getAll() {
		return favoriteDao.getAll();
	}

}
