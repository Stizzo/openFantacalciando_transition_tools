package it.openFantacalciando.tools.models;

import it.openFantacalciando.tools.enums.PlayerRole;

public class Quotation implements Comparable<Quotation>{

	private int id;
	private String name;
	private PlayerRole role;
	private String team;
	private long currentQuotation;
	private int generatedId;
	
	
	public Quotation() {
		
	}

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

	public PlayerRole getRole() {
		return role;
	}

	public void setRole(PlayerRole role) {
		this.role = role;
	}

	public String getTeam() {
		return team.toUpperCase();
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public long getCurrentQuotation() {
		return currentQuotation;
	}

	public void setCurrentQuotation(long currentQuotation) {
		this.currentQuotation = currentQuotation;
	}

	
	public int getGeneratedId() {
		return generatedId;
	}

	public void setGeneratedId(int generatedId) {
		this.generatedId = generatedId;
	}

	public String toFullString() {
		return "Quotation [id=" + id + ", generatedId=" + generatedId + ", name=" + name + ", role=" + role + ", team=" + team + ", currentQuotation="
				+ currentQuotation + "]";
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public int compareTo(Quotation q) {
	    return this.name.compareTo(q.getName()); // quotation name sort in ascending order 
	        //return q.getName().compareTo(this.name); use this line for quotation name sort in descending order 
	    }
	


	
}
