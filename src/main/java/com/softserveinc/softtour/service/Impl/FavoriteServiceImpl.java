package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.dao.FavoriteDao;
import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService{

	private FavoriteDao favoriteDao;
	
	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}
	
	@Override
	public void save(Date date, User user, Tour tour) {
		favoriteDao.save(date, user, tour);
	}

	@Override
	public void update(long id, Date date, User user, Tour tour) {
		favoriteDao.update(id, date, user, tour);
	}
	
	@Override
	public void delete(long id) {
		favoriteDao.delete(id);
	}

	@Override
	public Favorite findById(long id) {
		return favoriteDao.findById(id);
	}

	@Override
	public List<Favorite> findByUser(User user) {
		return favoriteDao.findByUser(user);
	}

	@Override
	public List<Favorite> findByTour(Tour tour) {
		return favoriteDao.findByTour(tour);
	}
	
	@Override
	public List<Favorite> getAll() {
		return favoriteDao.getAll();
	}

}
