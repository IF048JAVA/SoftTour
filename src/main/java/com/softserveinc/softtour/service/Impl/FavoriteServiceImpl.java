package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.softtour.repository.FavoriteDao;
import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.FavoriteService;

/**
 * @author Andriy
 * Contains the methods for work with table Favorite in the SoftTour database
 * Supports a transaction
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
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
	 * Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(Date date, User user, Tour tour) {
		favoriteDao.save(date, user, tour);
	}

	/**
	 *  Updates the object favorite with the specified id
	 *  id - id of the object favorite which will be updated
	 *  Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(long id, Date date, User user, Tour tour) {
		favoriteDao.update(id, date, user, tour);
	}
	
	/**
	 *  Deletes the object favorite with the specified id
	 *  id - id of the object favorite which will be deleted
	 *  Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(long id) {
		favoriteDao.delete(id);
	}

	/**
	 *  Returns the object favorite with the specified id
	 *  id - id of the object favorite which will be returned
	 *  Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Favorite findById(long id) {
		return favoriteDao.findById(id);
	}
	
	/**
	 * Returns the list of the objects favorite which contain the specified date
	 * @param date - date of the objects which will be added to the list
	 * Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Favorite> findByDate(Date... date) {
		return favoriteDao.findByDate(date);
	}

	/**
	 *  Returns the list of the objects favorite which contain the specified object user
	 *  Supports a transaction 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Favorite> findByUser(User...user) {
		return favoriteDao.findByUser(user);
	}

	/**
	 *  Returns the list of the objects favorite which contain the specified object tour
	 *  Supports a transaction 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Favorite> findByTour(Tour...tour) {
		return favoriteDao.findByTour(tour);
	}
	
	/**
	 *  Returns the list of all objects favorite which are contained in the table Favorite
	 *  Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Favorite> getAll() {
		return favoriteDao.getAll();
	}

}
