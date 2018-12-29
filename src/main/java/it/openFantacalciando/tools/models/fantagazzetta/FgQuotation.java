package it.openFantacalciando.tools.models.fantagazzetta;

import it.openFantacalciando.tools.models.Quotation;

public class FgQuotation extends Quotation {
	
	private long initialQuotation;
	private long difference;
	
	
	public FgQuotation() {
		
	}

	public long getInitialQuotation() {
		return initialQuotation;
	}
	
	public void setInitialQuotation(long initialQuotation) {
		this.initialQuotation = initialQuotation;
	}
	
	public long getDifference() {
		return difference;
	}
	
	public void setDifference(long difference) {
		this.difference = difference;
	}

	@Override
	public String toString() {
		return "FgQuotation [initialQuotation=" + initialQuotation + ", difference=" + difference + ", toString()="
				+ super.toString() + "]";
	}


	
}
