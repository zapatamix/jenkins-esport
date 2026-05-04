package com.alfredo;

import java.io.Serializable;

public class Jugador implements Comparable<Jugador>,Serializable {

	private int id;
	private String nick;
	private String rol;
	private int kills;
	private int deaths;
	private int assists;
	
	Jugador(int id, String nick,String rol, int kills, int deaths,int assists){
		this.id=id;
		this.nick=nick;
		this.rol=rol;
		this.kills=kills;
		this.deaths=deaths;
		this.assists=assists;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	// calcular KDA
	public double calcularKDA() {
		if(deaths == 0) {
			return kills + assists;
		}
		return (double)(kills + assists) / deaths;
	}

	// Comparable para ordenar por KDA (de mayor a menor)
	@Override
	public int compareTo(Jugador otro) {
		int cmp= Double.compare(otro.calcularKDA(), this.calcularKDA());
		if(cmp!=0) {
			return cmp;
		}
		cmp=Integer.compare(otro.getKills(), this.getKills());
		if(cmp!=0) {
			return cmp;
		}
		cmp=Integer.compare(this.getDeaths(), otro.getDeaths());
		if(cmp!=0) {
			return cmp;
		}
		cmp=Integer.compare(this.getId(), otro.getId());
		return cmp;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nick=" + nick + ", rol=" + rol + 
			   ", kills=" + kills + ", deaths=" + deaths +
			   ", assists=" + assists + ", KDA=" + calcularKDA() + "]";
	}
}
