package it.openFantacalciando.tools.enums;

public enum PlayerRole {

	P("Portiere", "Goalkeeper", 0),
	D("Difensore", "Defender", 1),
	C("Centrocampista", "Midfielder", 2),
	T("Trequartista","Playmaker", 2),
	A("Attaccante", "Forward", 3);
	
	private String italianName;
	private String englishName;
	private int numberRole;
	
	
	private PlayerRole(String italianName, String englishName, int numberRole) {
		this.italianName = italianName;
		this.englishName = englishName;
		this.numberRole = numberRole;
	}


	public String getItalianName() {
		return italianName;
	}


	public void setItalianName(String italianName) {
		this.italianName = italianName;
	}


	public String getEnglishName() {
		return englishName;
	}


	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}


	public int getNumberRole() {
		return numberRole;
	}


	public void setNumberRole(int numberRole) {
		this.numberRole = numberRole;
	}
	
	
	
}
