package com.si1.lab03.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si1.lab03.model.Serie;
import com.si1.lab03.repository.SerieRepository;

@Service
public class SerieService {
	
	private SerieRepository serieRepository;

	@Autowired
	public SerieService(SerieRepository serieRepository) {
		this.serieRepository = serieRepository;
	}

	public void addSerie(Serie serie) {
		if (userHaveSerie(serie)) {
			throw new RuntimeException();	
		} else {
			serieRepository.save(serie);
		}
	}
	
	public void removeSerie(String imdbID, Long idUsuario) {
		List<Serie> series = serieRepository.findAll();
		for (Serie serie2 : series) {
			if (serie2.getImdbId().equals(imdbID) && serie2.getIdUsuario().equals(idUsuario)) {
				serieRepository.delete(serie2);	
			} else {
				throw new RuntimeException();
			}
		}
	
	}
	
	public void addSerieWatchlist(Serie serie) {
		if (userHaveSerie(serie)) {
			throw new RuntimeException();		
		} else {
			serieRepository.save(serie);
		}
	}
	
	public void removeSerieWatchlist(String imdbID, Long idUsuario) {
		List<Serie> series = serieRepository.findAll();
		for (Serie serie2 : series) {
			if (serie2.getImdbId().equals(imdbID) && serie2.getIdUsuario().equals(idUsuario)) {
				serieRepository.delete(serie2);	
			} else {
				throw new RuntimeException();
			}
		}
	}
	
	public boolean userHaveSerie(Serie serie) {
		List<Serie> series = serieRepository.findAll();
		for (Serie serie2 : series) {
			if (serie2.equals(serie)) {
				return true;
			}
		} return false;
	}
	
	public List<Serie> getSeries(Long idUsuario) {
		List<Serie> series =  serieRepository.findAll();
		List<Serie> profile = new ArrayList<>();
		for (Serie serie : series) {
			if (!serie.naWatchlist() && serie.getIdUsuario().equals(idUsuario)) {
				profile.add(serie);
			}
		}
		return profile;
	} 
	
	public List<Serie> getSeriesWatchlist(Long idUsuario) {
		List<Serie> series =  serieRepository.findAll();
		List<Serie> watchlist = new ArrayList<>();
		for (Serie serie : series) {
			if (serie.naWatchlist() && serie.getIdUsuario().equals(idUsuario)) {
				watchlist.add(serie);
			}
		}
		return watchlist;
	} 
	
}
