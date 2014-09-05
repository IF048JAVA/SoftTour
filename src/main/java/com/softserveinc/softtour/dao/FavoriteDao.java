package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

import java.sql.Date;
import java.util.List;

public interface FavoriteDao {
	
	public void save(Date date, User user, Tour tour);
	public void update(long id, Date date, User user, Tour tour);
	public void delete(long id);
	public Favorite findById(long id);
	public List<Favorite> findByUser (User user);
	public List<Favorite> getAll();	
}
