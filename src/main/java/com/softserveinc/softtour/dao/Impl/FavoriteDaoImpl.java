package com.softserveinc.softtour.dao.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.softserveinc.softtour.dao.FavoriteDao;
import com.softserveinc.softtour.entity.Favorite;

public class FavoriteDaoImpl extends HibernateDaoSupport implements FavoriteDao {

	@Override
	public void save(Date date, long user_id, long tour_id) {
		Favorite favorite = new Favorite(date, user_id, tour_id);
		getHibernateTemplate().save(favorite);
	}

	@Override
	public void update(long id, Date date, long user_id, long tour_id) {
		Favorite favorite = (Favorite) getHibernateTemplate().get(Favorite.class, id);
		
		if (favorite != null) {
			favorite.setDate();
			favorite.setUser_id();
			favorite.setTour_id();
			
			getHibernateTemplate().update(favorite);
		} else {
			System.err.println("Error ! \n Favorite is null !");
		}
	}

	@Override
	public void delete(long id) {
		Favorite favorite = (Favorite) getHibernateTemplate().get(Favorite.class, id);
		
		if (favorite != null) {
			getHibernateTemplate().delete(favorite);
		} else {
			System.err.println("Error ! \n Favorite is null !");
		}
	}

	@Override
	public Favorite findById(long id) {
		Favorite favorite = (Favorite) getHibernateTemplate().get(Favorite.class, id);
		return favorite;
	}

	@Override
	public List<Favorite> getAll() {
		String queryGetAll = "From Favorite";
		@SuppressWarnings("unchecked")
		List<Favorite> list = getHibernateTemplate().find(queryGetAll);
		
		return list;
	}

}
