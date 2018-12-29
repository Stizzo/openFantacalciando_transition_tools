package it.openFantacalciando.tools.models;

public class IndexEntry {

	private int generatedIndex;
	private int draftIndex;
	private String playerName;
	
	public IndexEntry(int generatedIndex, int draftIndex, String playerName) {
		super();
		this.generatedIndex = generatedIndex;
		this.draftIndex = draftIndex;
		this.playerName = playerName;
	}
	
	public IndexEntry() {
		
	}

	public int getGeneratedIndex() {
		return generatedIndex;
	}

	public void setGeneratedIndex(int generatedIndex) {
		this.generatedIndex = generatedIndex;
	}

	public int getDraftIndex() {
		return draftIndex;
	}

	public void setDraftIndex(int draftIndex) {
		this.draftIndex = draftIndex;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	
}
