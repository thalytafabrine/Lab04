package com.si1.lab03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.si1.lab03.model.Serie;
import com.si1.lab03.service.SerieService;

@RestController
@RequestMapping(value = "/")
public class SerieController {
	
	@Autowired
	private SerieService serieService;

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public void addSeriePerfil(Serie serie) {
		serieService.addSerie(serie);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/remove/{idUsuario}")
	public void removeSeriePerfil(@RequestBody String imdbId, @PathVariable Long idUsuario) {
		serieService.removeSerie(imdbId, idUsuario);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveWatchlist")
	public void addSerieWatchlist(Serie serie) {
		serieService.addSerie(serie);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeWatchlist/{idUsuario}")
	public void removeSerieWatchlist(@RequestBody String imdbId, @PathVariable Long idUsuario) {
		serieService.removeSerie(imdbId, idUsuario);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getSeries/{idUsuario}")
	public List<Serie> getSeriesPerfil(@PathVariable Long idUsuario) {
		return serieService.getSeries(idUsuario);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getSeriesWatchlist/{idUsuario}")
	public List<Serie> getSeriesWatchlist(@PathVariable Long idUsuario) {
		return serieService.getSeriesWatchlist(idUsuario);
	}
	
}
