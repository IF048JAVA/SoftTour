package com.softserveinc.softtour.dao.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.softserveinc.softtour.dao.FavoriteDao;
import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

public class FavoriteDaoImpl extends HibernateDaoSupport implements FavoriteDao {

	@Override
	public void save(Date date, User user, Tour tour) {
		Favorite favorite = new Favorite(date, user, tour);
		getHibernateTemplate().save(favorite);
	}

	@Override
	public void update(long id, Date date, User user, Tour tour) {
		Favorite favorite = (Favorite) getHibernateTemplate().get(Favorite.class, id);
		
		if (favorite != null) {
			favorite.setDate(date);
			favorite.setUser(user);
			favorite.setTour(tour);
			
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
    public List<Favorite> findByUser(User user) {
        @SuppressWarnings("unchecked")
		List<Favorite> list = (List<Favorite>) getHibernateTemplate().find("FROM Favorite WHERE user_id = ?", user.getId());
        return list;
    }


    @Override
	public List<Favorite> getAll() {
		@SuppressWarnings("unchecked")
		List<Favorite> list = getHibernateTemplate().find("From Favorite");
		
		return list;
	}


}
