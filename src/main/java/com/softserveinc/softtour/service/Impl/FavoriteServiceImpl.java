package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.softtour.repository.FavoriteRepository;
import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.FavoriteService;

/**
 * @author Andriy
 * Contains the methods for work with table Favorite in the SoftTour database
 * Supports a transaction
 */
@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	private FavoriteRepository favoriteRepository;
	
	/**
	 * Saves the object favorite to the table Favorite
	 * Supports a transaction
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Favorite favorite) {
		favoriteRepository.save(favorite);
	}
	
	/**
	 * Updates the object favorite with the specified id
	 * @param id - id of the object favorite which will be updated
	 * @param favorite - it's the object with the new values
	 */
	@Override
	@Transactional(readOnly=false)
	public void update(long id, Favorite favorite) {
		favoriteRepository.update(id, favorite.getDate(), favorite.getUser(), favorite.getTour());
	}
	
	/**
	 *  Deletes the object favorite with the specified id
	 *  id - id of the object favorite which will be deleted
	 *  Supports a transaction
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(long id) {
		favoriteRepository.delete(id);
	}
	
	/**
	 *  Returns the object favorite with the specified id
	 *  id - id of the object favorite which will be returned
	 *  Supports a transaction
	 */
	@Override
	public Favorite findById(long id) {
		return favoriteRepository.findOne(id);
	}
	
	/**
	 * Returns the list of the favorite's objects with the specified parameters
	 */
	@Override
	public List<Favorite> findByAnyParameters(long id, Date date, User user, Tour tour) {
		return favoriteRepository.findByIdOrDateOrUserOrTour(id, date, user, tour);
	}
	
	/**
	 *  Returns the list of all favorite's objects which are contained in the table Favorite
	 */
	@Override
	public List<Favorite> findAll() {
		return favoriteRepository.findAll();
	}

}
