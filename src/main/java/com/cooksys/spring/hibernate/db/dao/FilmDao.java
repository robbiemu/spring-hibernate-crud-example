package com.cooksys.spring.hibernate.db.dao;

import java.util.List;

import com.cooksys.spring.hibernate.db.model.Actor;
import com.cooksys.spring.hibernate.db.model.Category;
import com.cooksys.spring.hibernate.db.model.Film;

public interface FilmDao {
	public List<Film> readFilms();
	public Film readFilm(Short pk);
	public Film createFilm(Film film);
	public Film updateFilm(Short pk, Film film);
	public Film deleteFilm(Short pk);
	public List<Actor> readActors(Short pk);
	public List<Category> readCategories(Short pk);
	public List<Actor> createFilmActor(Short pk, Actor actor);
	public List<Category> createFilmCategory(Short pk, Category category);
	public List<Actor> deleteFilmActor(Short pk, Short apk);
	public List<Category> deleteFilmCategory(Short pk, Byte cpk);
}
