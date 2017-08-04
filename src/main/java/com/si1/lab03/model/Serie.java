package com.si1.lab03.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Serie {

	@Id
	@GeneratedValue()
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="imdbId", nullable=false)
	private String imdbId;
	
	@Column(name="avaliacao", nullable=false)
	private String avaliacao;
	
	@Column(name="ultimoEpisodio", nullable=false)
	private Integer ultimoEpisodio;
	
	@Column(name="idUsuario", nullable=false)
	private Long idUsuario;
	
	@Column(name="inWatchlist", nullable=false)
	private boolean inWatchlist;
	
	
	public boolean naWatchlist() {
		return inWatchlist;
	}
	public void setWatchlist(boolean perfil) {
		this.inWatchlist = perfil;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long serieID) {
		this.id = serieID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	public Integer getUltimoEpisodio() {
		return ultimoEpisodio;
	}
	public void setUltimoEpisodio(Integer ultimoEpisodio) {
		this.ultimoEpisodio = ultimoEpisodio;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
