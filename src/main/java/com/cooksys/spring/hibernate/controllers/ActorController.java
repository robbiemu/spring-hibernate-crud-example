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

import com.cooksys.spring.hibernate.db.dao.ActorDao;
import com.cooksys.spring.hibernate.db.model.Actor;
import com.cooksys.spring.hibernate.db.model.Film;

@RestController
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
@RequestMapping("/actors")
public class ActorController {
	@Autowired
	private ActorDao actorDao;
	
	/* collection */
	@RequestMapping(method= RequestMethod.GET) //root
	public @ResponseBody List<Actor> getActors() {
		return actorDao.readActors();
	}

	/* unit */
	@RequestMapping(value = "/{pk}", method = RequestMethod.GET)
	public @ResponseBody Actor getActor(@PathVariable Short pk) {
		return actorDao.readActor(pk);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Actor postActor(@RequestBody Actor actor) {
		return actorDao.createActor(actor);
	}
	
	@RequestMapping(value = "/{pk}", method = RequestMethod.PATCH)
	public @ResponseBody Actor patchActor(@PathVariable Short pk, @RequestBody Actor actor) {
		return actorDao.updateActor(pk, actor);
	}	
	
	@RequestMapping(value = "/{pk}", method = RequestMethod.DELETE)
	public @ResponseBody Actor deleteActor(@PathVariable Short pk) {
		return actorDao.deleteActor(pk);
	}
	
	/* relational */
	// get all films for actor
	@RequestMapping(value = "/{pk}/films", method = RequestMethod.GET)
	public @ResponseBody List<Film> getFilms(@PathVariable Short pk) {
		return actorDao.readFilms(pk);
	}
	
	@RequestMapping(value = "/{pk}/films", method = RequestMethod.PUT)
	public @ResponseBody List<Film> putFilm(@PathVariable Short pk, @RequestBody Film film) {
		return actorDao.createFilmActor(pk, film);
	}

	@RequestMapping(value = "/{pk}/films/{fpk}", method = RequestMethod.DELETE)
	public @ResponseBody List<Film> deleteFilm(@PathVariable Short pk, 
			@PathVariable Short fpk) {
		return actorDao.deleteFilmActor(pk, fpk);
	}
}
