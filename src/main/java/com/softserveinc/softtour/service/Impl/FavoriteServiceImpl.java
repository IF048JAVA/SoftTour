package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.dao.FavoriteDao;
import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService{

	private FavoriteDao favoriteDao;
	
	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	@Override
	public void save(Date date, long user_id, long tour_id) {
		favoriteDao.save(date, user_id, tour_id);
	}

	@Override
	public void update(long id, Date date, long user_id, long tour_id) {
		favoriteDao.update(id, date, user_id, tour_id);
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
	public List<Favorite> getAll() {
		return favoriteDao.getAll();
	}

}
