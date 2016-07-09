package com.cooksys.spring.hibernate.db.dao;

import java.util.List;

import com.cooksys.spring.hibernate.db.model.Actor;
import com.cooksys.spring.hibernate.db.model.Film;

public interface ActorDao {
	/* collections */
	public List<Actor> readActors();
	/* units */
	public Actor readActor(Short pk);
	public Actor createActor(Actor actor);
	public Actor deleteActor(Short pk);
	public Actor updateActor(Short pk, Actor actor);
	/* relational */
	public List<Film> readFilms(Short pk);
	public List<Film> createFilmActor(Short pk, Film film);
	public List<Film> deleteFilmActor(Short pk, Short fpk);
}
