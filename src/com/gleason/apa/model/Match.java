package com.gleason.apa.model;

import java.util.Date;

public class Match {
	private int id;
	private Date matchDate;
	private Team homeTeam;
	private Team awayTeam;
	private Bar location;
	private int homeTeamWins;
	private int awayTeamWins;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Bar getLocation() {
		return location;
	}
	public void setLocation(Bar location) {
		this.location = location;
	}
	public int getHomeTeamWins() {
		return homeTeamWins;
	}
	public void setHomeTeamWins(int homeTeamWins) {
		this.homeTeamWins = homeTeamWins;
	}
	public int getAwayTeamWins() {
		return awayTeamWins;
	}
	public void setAwayTeamWins(int awayTeamWins) {
		this.awayTeamWins = awayTeamWins;
	}
}
