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
	 * Returns the list of the objects favorite which contains the specified date
	 * @param date - date of the objects which will be added to the list
	 */
	@Override
	public List<Favorite> findByDate(Date... date) {
		String queryFindByDate = createQuery("date", date.length);

		List<Favorite> list = (List<Favorite>) getHibernateTemplate().find(queryFindByDate, date);
		return list;
	}
	
	/**
	 *  Returns the list of the objects favorite which contain the specified object user 
	 */
	@Override
	public List<Favorite> findByUser(User...user) {
		String queryFindByUser = createQuery("user_id", user.length);

		List<Favorite> list = (List<Favorite>) getHibernateTemplate().find(queryFindByUser, user);
		return list;
	}

	/**
	 *  Returns the list of the objects favorite which contain the specified object tour 
	 */
	@Override
	public List<Favorite> findByTour(Tour...tour) {
		String queryFindByTour = createQuery("tour_id", tour.length);

		List<Favorite> list = (List<Favorite>) getHibernateTemplate().find(queryFindByTour, tour);
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
	
	/**
	 * Creates the query for finding objects 
	 * @param columnName - name of the colunm which we write into condition WHERE
	 * @param length - length of vararg's elements
	 * @return query for finding objects 
	 */
	private String createQuery(String columnName, int length) {
		StringBuilder queryStringBuilder = new StringBuilder("FROM Favorite WHERE ");
		
		for (int i = 0; i < length; i++) {
			queryStringBuilder.append(columnName + " = ? OR ");
		}
		queryStringBuilder.delete(queryStringBuilder.length()-4, queryStringBuilder.length());
		String queryString = queryStringBuilder.toString();
		return queryString;
	}

}