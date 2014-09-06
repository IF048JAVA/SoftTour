package com.softserveinc.softtour.dao.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.softserveinc.softtour.dao.FavoriteDao;
import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

/**
 * @author Andriy
 *	Contains the methods for work with table Favorite in the SoftTour database 
 */
public class FavoriteDaoImpl extends HibernateDaoSupport implements FavoriteDao {

	/**
	 * Saves the object favorite to the table Favorite
	 */
	@Override
	public void save(Date date, User user, Tour tour) {
		Favorite favorite = new Favorite(date, user, tour);
		getHibernateTemplate().save(favorite);
	}

	/**
	 *  Updates the object favorite with the specified id
	 *  id - id of the object favorite which will updated
	 */
	@Override
	public void update(long id, Date date, User user, Tour tour) {
		Favorite favorite = (Favorite) getHibernateTemplate().get(
				Favorite.class, id);

		if (favorite != null) {
			favorite.setDate(date);
			favorite.setUser(user);
			favorite.setTour(tour);

			getHibernateTemplate().update(favorite);
		} else {
			System.err.println("Error ! \n No favorite with this ID ! ");
		}
	}

	/**
	 *  Deletes the object favorite with the specified id
	 *  id - id of the object favorite which will deleted
	 */
	@Override
	public void delete(long id) {
		Favorite favorite = (Favorite) getHibernateTemplate().get(
				Favorite.class, id);

		if (favorite != null) {
			getHibernateTemplate().delete(favorite);
		} else {
			System.err.println("Error ! \n No favorite with this ID ! ");
		}
	}

	/**
	 *  Returns the object favorite with the specified id
	 *  id - id of the object favorite which will returned
	 */
	@Override
	public Favorite findById(long id) {
		Favorite favorite = (Favorite) getHibernateTemplate().get(
				Favorite.class, id);

		return favorite;
	}

	/**
	 *  Returns the list of the objects favorite which contain the specified object user 
	 */
	@Override
	public List<Favorite> findByUser(User user) {
		String queryFindByUser = "FROM Favorite WHERE user_id = ?";
		@SuppressWarnings("unchecked")
		List<Favorite> list = (List<Favorite>) getHibernateTemplate().find(
				queryFindByUser, user.getId());

		return list;
	}

	/**
	 *  Returns the list of the objects favorite which contain the specified object tour 
	 */
	@Override
	public List<Favorite> findByTour(Tour tour) {
		String queryFindByTour = "FROM Favorite WHERE tour_id = ?";
		@SuppressWarnings("unchecked")
		List<Favorite> list = (List<Favorite>) getHibernateTemplate().find(
				queryFindByTour, tour.getId());
		
		return list;
	}

	/**
	 *  Returns the list of all objects favorite which are contained in the table Favorite
	 */
	@Override
	public List<Favorite> getAll() {
		String queryGetAll = "From Favorite";
		@SuppressWarnings("unchecked")
		List<Favorite> list = getHibernateTemplate().find(queryGetAll);

		return list;
	}

}