package com.cooksys.spring.hibernate.db.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.spring.hibernate.db.dao.ActorDao;
import com.cooksys.spring.hibernate.db.model.Actor;
import com.cooksys.spring.hibernate.db.model.Film;
import com.cooksys.spring.hibernate.db.model.FilmActor;
import com.cooksys.spring.hibernate.db.model.FilmActorId;

@Transactional // this automates transactions for us
@Repository // this annotation typifies the injectable as a resource of data
public class ActorDaoImpl implements ActorDao {
	private static Logger log = LoggerFactory.getLogger(ActorDaoImpl.class);
		
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Actor> readActors() {
		Session s = sessionFactory.getCurrentSession();
		
		String hql = "from Actor";
		Query q = s.createQuery(hql);	

		return q.list();
	}
	
	@Override
	public Actor readActor(Short pk) {
		return sessionFactory.getCurrentSession().get(Actor.class, pk);
	}

	@Override
	public Actor createActor(Actor actor) {
		Serializable s = sessionFactory.getCurrentSession().save(actor);

		return sessionFactory.getCurrentSession().get(Actor.class, s);
	}

	@Override
	public Actor deleteActor(Short pk) {
		Actor a = sessionFactory.getCurrentSession().get(Actor.class, pk);
		
		sessionFactory.getCurrentSession().delete(a);
		
		return a;
	}

	@Override
	public Actor updateActor(Short pk, Actor actor) {
		actor.setActorId(pk);
		
		sessionFactory.getCurrentSession().update(actor);
		
		return sessionFactory.getCurrentSession().get(Actor.class, pk);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> readFilms(Short pk) {
		Session s = sessionFactory.getCurrentSession();
		
		String hql = "select fa.film from FilmActor fa where fa.actor.actorId = :actorId";
		Query q = s.createQuery(hql);
		q.setShort("actorId", pk);

		return q.list();
	}

	@Override
	public List<Film> createFilmActor(Short pk, Film film) {
		FilmActor fa = new FilmActor();
		FilmActorId faid = new FilmActorId();
	
		faid.setActorId(pk);
		faid.setFilmId(film.getFilmId());
		fa.setId(faid);

		sessionFactory.getCurrentSession().save(fa);
		
		return readFilms(pk);
	}

	@Override
	public List<Film> deleteFilmActor(Short pk, Short fpk) {
		FilmActor fa = new FilmActor();
		FilmActorId faid = new FilmActorId();
	
		faid.setActorId(pk);
		faid.setFilmId(fpk);
		fa.setId(faid);

		sessionFactory.getCurrentSession().delete(fa);
		
		return readFilms(pk);
	}

}
