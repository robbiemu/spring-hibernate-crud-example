package com.cooksys.spring.hibernate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.spring.hibernate.db.dao.FilmDao;
import com.cooksys.spring.hibernate.db.model.Actor;
import com.cooksys.spring.hibernate.db.model.Category;
import com.cooksys.spring.hibernate.db.model.Film;

// these annotations in spring are not inheritable
@RestController
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
@RequestMapping("/films")
public class FilmController {
	@Autowired
	private FilmDao filmDao;
	
	/* collection */
	@RequestMapping(method= RequestMethod.GET) //root
	public @ResponseBody List<Film> getFilms() {
		return filmDao.readFilms();
	}

	/* unit */
	@RequestMapping(value = "/{pk}", method = RequestMethod.GET)
	public @ResponseBody Film getFilm(@PathVariable Short pk) {
		return filmDao.readFilm(pk);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Film postFilm(@RequestBody Film film) {
		return filmDao.createFilm(film);
	}
	
	@RequestMapping(value = "/{pk}", method = RequestMethod.PATCH)
	public @ResponseBody Film patchFilm(@PathVariable Short pk, @RequestBody Film film) {
		return filmDao.updateFilm(pk, film);
	}	
	
	@RequestMapping(value = "/{pk}", method = RequestMethod.DELETE)
	public @ResponseBody Film deleteFilm(@PathVariable Short pk) {
		return filmDao.deleteFilm(pk);
	}
	
	/* relational */
	// get all actors for film
	@RequestMapping(value = "/{pk}/actors", method = RequestMethod.GET)
	public @ResponseBody List<Actor> getActors(@PathVariable Short pk) {
		return filmDao.readActors(pk);
	}
	@RequestMapping(value = "/{pk}/categories", method = RequestMethod.GET)
	public @ResponseBody List<Category> getCategories(@PathVariable Short pk) {
		return filmDao.readCategories(pk);
	}

	@RequestMapping(value = "/{pk}/actors", method = RequestMethod.PUT)
	public @ResponseBody List<Actor> putActor(@PathVariable Short pk, @RequestBody Actor actor) {
		return filmDao.createFilmActor(pk, actor);
	}
	@RequestMapping(value = "/{pk}/categories", method = RequestMethod.PUT)
	public @ResponseBody List<Category> putCategory(@PathVariable Short pk, @RequestBody Category category) {
		return filmDao.createFilmCategory(pk, category);
	}
	
	@RequestMapping(value = "/{pk}/actors/{apk}", method = RequestMethod.DELETE)
	public @ResponseBody List<Actor> deleteActor(@PathVariable Short pk, 
			@PathVariable Short apk) {
		return filmDao.deleteFilmActor(pk, apk);
	}
	@RequestMapping(value = "/{pk}/categories/{cpk}", method = RequestMethod.DELETE)
	public @ResponseBody List<Category> deleteCategory(@PathVariable Short pk, 
			@PathVariable Byte cpk) {
		return filmDao.deleteFilmCategory(pk, cpk);
	}
}
