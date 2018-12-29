package it.openFantacalciando.tools.logic;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import it.openFantacalciando.tools.enums.DataSource;
import it.openFantacalciando.tools.models.Footballer;
import it.openFantacalciando.tools.models.IndexEntry;
import it.openFantacalciando.tools.models.Quotation;
import it.openFantacalciando.tools.operations.PreSeasonOperation;

public class PreSeasonLogic {
	private PreSeasonOperation operation;
	public PreSeasonLogic() {
		
	}

	
	public void generateQuotations(DataSource source, String quotationSourcePath, String quotationDestinationPath) throws FileNotFoundException, UnsupportedEncodingException {
		operation = new PreSeasonOperation();
		List<Quotation> quotations = new ArrayList<Quotation>();
		
		quotations = operation.parseQuotationsFromExcelFile(source, quotationSourcePath);
		
		List<Quotation> duplicatedNames = operation.checkForDuplicatedNamesInQuotations((List<Quotation>) quotations);
		List<Quotation> duplicatedIds = operation.checkForDuplicatedIdsInQuotations((List<Quotation>) quotations);
		
		System.out.println("Duplicated names found in quotation: " + duplicatedNames.size());
		System.out.println(duplicatedNames.toString());
		System.out.println("Duplicated ids found in quotation: " + duplicatedIds.size());
		System.out.println(duplicatedIds.toString());
		
		quotations = operation.sortQuotationsAndAssignIds(quotations);
		
		
		operation.generateQuotationFile(quotations, quotationDestinationPath);
		operation.generateTeamsFile(quotations, quotationDestinationPath);
		
		operation.generateMapTable(quotations, quotationDestinationPath);
	}
	
	public void updateQuotations(DataSource source, String quotationSourcePath, String existingMapIDsPath, String existingFootballersListPath, String destinationPath) throws FileNotFoundException, UnsupportedEncodingException {
		
		operation = new PreSeasonOperation();
		List<Quotation> newQuotations = new ArrayList<Quotation>();
		List<IndexEntry> ids = new ArrayList<IndexEntry>();
		List<Footballer> existingPlayers = new ArrayList<Footballer>();
		List<Quotation> newPlayers = new ArrayList<Quotation>();
		
		newQuotations = operation.parseQuotationsFromExcelFile(source, quotationSourcePath);
		
		List<Quotation> duplicatedNames = operation.checkForDuplicatedNamesInQuotations((List<Quotation>) newQuotations);
		List<Quotation> duplicatedIds = operation.checkForDuplicatedIdsInQuotations((List<Quotation>) newQuotations);
		
		System.out.println("Duplicated names found in quotation: " + duplicatedNames.size());
		System.out.println(duplicatedNames.toString());
		System.out.println("Duplicated ids found in quotation: " + duplicatedIds.size());
		System.out.println(duplicatedIds.toString());
		
		ids = operation.readIndexEntries(existingMapIDsPath);
		
		newQuotations = operation.mergeGeneratedIDs(newQuotations, ids);
		
		existingPlayers = operation.readFootballersList(existingFootballersListPath);
		existingPlayers = operation.updateTransferStatus(existingPlayers, newQuotations);
		
		newPlayers = operation.findNewFootballersAndGenerateIDs(newQuotations, existingPlayers);
		existingPlayers = operation.updateExistingQuotations(newQuotations, existingPlayers);
		
		existingPlayers = operation.addNewPlayers(existingPlayers, newPlayers);
		ids = operation.updateIDsList(ids, newPlayers);
		
		operation.printMCC(existingPlayers, destinationPath);
		operation.printMapTable(ids, destinationPath);
		
		
	}
}
