package com.matroskeen.beans;

public class Tournament {
	
	public final static byte STATUS_ACTIVE = 0;
	public final static byte STATUS_PLANNED = 1;
	public final static byte STATUS_PAST = 2;
	public final static byte[] STATUSES = {STATUS_ACTIVE, STATUS_PLANNED, STATUS_PAST};
	
	private int id;
	private String title;
	private int teamPlayers;
	private int extraPlayers;
	private long date;
	private String info;
	private String terms;
	private byte status;
	
	public Tournament() { }
	
	public Tournament(int id, String title, int teamPlayers, int extraPlayers,
            long date, String info, String terms, byte status) {
	    super();
	    this.id = id;
	    this.title = title;
	    this.teamPlayers = teamPlayers;
	    this.extraPlayers = extraPlayers;
	    this.date = date;
	    this.info = info;
	    this.terms = terms;
	    this.status = status;
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getTeamPlayers() {
		return teamPlayers;
	}
	public void setTeamPlayers(int teamPlayers) {
		this.teamPlayers = teamPlayers;
	}

	public int getExtraPlayers() {
		return extraPlayers;
	}

	public void setExtraPlayers(int extraPlayers) {
		this.extraPlayers = extraPlayers;
	}
	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}

	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	public static String getStatusName(byte status) {
		switch(status) {
			case 0:
				return "Активні";
			case 1:
				return "Заплановані";
			case 2:
				return "Зіграні";
			default:
				return "undefined";
		}
	}
	
}
