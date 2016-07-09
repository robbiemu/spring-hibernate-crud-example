package com.cooksys.spring.hibernate.db.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.spring.hibernate.db.dao.FilmDao;
import com.cooksys.spring.hibernate.db.model.Actor;
import com.cooksys.spring.hibernate.db.model.Category;
import com.cooksys.spring.hibernate.db.model.Film;
import com.cooksys.spring.hibernate.db.model.FilmActor;
import com.cooksys.spring.hibernate.db.model.FilmActorId;
import com.cooksys.spring.hibernate.db.model.FilmCategory;
import com.cooksys.spring.hibernate.db.model.FilmCategoryId;

@Transactional
@Repository
public class FilmDaoImpl implements FilmDao {
	private static Logger log = LoggerFactory.getLogger(FilmDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Film> readFilms() {
		Session s = sessionFactory.getCurrentSession();

		String hql = "from Film";

		Query q = s.createQuery(hql);	
		return q.list();
	}
	
	@Override
	public Film readFilm(Short pk) {
		return sessionFactory.getCurrentSession().get(Film.class, pk);
	}

	@Override
	public Film createFilm(Film film) {
		Serializable s = sessionFactory.getCurrentSession().save(film);

		return sessionFactory.getCurrentSession().get(Film.class, s);
	}

	@Override
	public Film updateFilm(Short pk, Film film) {
		film.setFilmId(pk);
		
		sessionFactory.getCurrentSession().update(film);
		
		return sessionFactory.getCurrentSession().get(Film.class, pk);
	}

	@Override
	public Film deleteFilm(Short pk) {
		Film f = sessionFactory.getCurrentSession().get(Film.class, pk);
		
		sessionFactory.getCurrentSession().delete(f);
		
		return f;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> readActors(Short pk) {
		Session s = sessionFactory.getCurrentSession();
		
		String hql = "select fa.actor from FilmActor fa where fa.film.filmId = :filmId";
		Query q = s.createQuery(hql);
		q.setShort("filmId", pk);

		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> readCategories(Short pk) {
		Session s = sessionFactory.getCurrentSession();
		
		String hql = "select fc.category from FilmCategory fc where fc.film.filmId = :filmId";
		Query q = s.createQuery(hql);
		q.setShort("filmId", pk);
 		
		return q.list();
	}

	@Override
	public List<Actor> createFilmActor(Short pk, Actor actor) {
		FilmActor fa = new FilmActor();
		FilmActorId faid = new FilmActorId();
	
		faid.setFilmId(pk);
		faid.setActorId(actor.getActorId());
		fa.setId(faid);

		sessionFactory.getCurrentSession().save(fa);
		
		return readActors(pk);
	}

	@Override
	public List<Category> createFilmCategory(Short pk, Category category) {
		FilmCategory fc = new FilmCategory();
		FilmCategoryId fcid = new FilmCategoryId();
	
		fcid.setFilmId(pk);
		fcid.setCategoryId(category.getCategoryId());
		fc.setId(fcid);

		sessionFactory.getCurrentSession().save(fc);
		
		return readCategories(pk);
	}

	@Override
	public List<Actor> deleteFilmActor(Short pk, Short apk) {
		FilmActor fa = new FilmActor();
		FilmActorId faid = new FilmActorId();
	
		faid.setFilmId(pk);
		faid.setActorId(apk);
		fa.setId(faid);

		sessionFactory.getCurrentSession().delete(fa);
		
		return readActors(pk);
	}

	@Override
	public List<Category> deleteFilmCategory(Short pk, Byte cpk) {
		FilmCategory fc = new FilmCategory();
		FilmCategoryId fcid = new FilmCategoryId();
	
		fcid.setFilmId(pk);
		fcid.setCategoryId(cpk);
		fc.setId(fcid);

		sessionFactory.getCurrentSession().delete(fc);
		
		return readCategories(pk);
	}
}
