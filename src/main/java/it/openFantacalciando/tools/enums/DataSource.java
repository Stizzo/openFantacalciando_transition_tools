package it.openFantacalciando.tools.enums;

public enum DataSource {
	FG("Fantagazzetta"),
	PF("Pianeta Fantacalcio"),
	GDS("Gazzetta dello Sport");
	
	private String sourceName;
	
	private DataSource(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
   @Override
   public String toString() {
       return this.sourceName;
   }
	
}
