package com.softserveinc.softtour.service;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.entity.Favorite;

public interface FavoriteService {
	public void save(Date date, long user_id, long tour_id);
	public void update(long id, Date date, long user_id, long tour_id);
	public void delete(long id);
	public Favorite findById(long id);
	public List<Favorite> getAll();	
}
