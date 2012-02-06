package com.gleason.apa.model;

import com.gleason.apa.model.enums.Ranking;

public class Player {
	private int id;
	private String name;
	private Team team;
	private Ranking rank;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Ranking getRank() {
		return rank;
	}
	public void setRank(Ranking rank) {
		this.rank = rank;
	}
}
