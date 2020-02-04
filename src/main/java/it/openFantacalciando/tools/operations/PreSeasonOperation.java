package it.openFantacalciando.tools.operations;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.openFantacalciando.tools.enums.DataSource;
import it.openFantacalciando.tools.enums.PlayerRole;
import it.openFantacalciando.tools.global.Variables;
import it.openFantacalciando.tools.models.Footballer;
import it.openFantacalciando.tools.models.IndexEntry;
import it.openFantacalciando.tools.models.Quotation;
import it.openFantacalciando.tools.models.fantagazzetta.FgQuotation;
import it.openFantacalciando.tools.operations.drafters.Fantagazzetta;

public class PreSeasonOperation extends Operation {
	
	public PreSeasonOperation(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Quotation> parseQuotationsFromExcelFile(DataSource source, String quotationSourcePath){
		List<? extends Quotation> quotations = new ArrayList<Quotation>();
		
		if (source == DataSource.FG) {
			Fantagazzetta fg = new Fantagazzetta();
			List<FgQuotation> fgQuotations = fg.readQuotationsFromXlsFile(quotationSourcePath);
			quotations = fgQuotations;
		}
		
		return (List<Quotation>) quotations;
	}
	
	public List<Quotation> sortQuotationsAndAssignIds(List<Quotation> quotations){
		List<Quotation> goalkeepers = new ArrayList<Quotation>();
		List<Quotation> defenders = new ArrayList<Quotation>();
		List<Quotation> midfielders = new ArrayList<Quotation>();
		List<Quotation> strikers = new ArrayList<Quotation>();
		
		for (Quotation quotation : quotations) {
			
			if (quotation.getRole() == PlayerRole.P) {
				goalkeepers.add(quotation);
			}else if (quotation.getRole() == PlayerRole.D) {
				defenders.add(quotation);
			}else if (quotation.getRole() == PlayerRole.C) {
				midfielders.add(quotation);
			}else if (quotation.getRole() == PlayerRole.A) {
				strikers.add(quotation);
			}
		}
		
		Collections.sort(goalkeepers);
		Collections.sort(defenders);
		Collections.sort(midfielders);
		Collections.sort(strikers);
		
		int temp_id = Variables.GOALKEEPER_START_ID;
		for (Quotation quotation : goalkeepers) {
			quotation.setGeneratedId(temp_id);
			temp_id++;
		}
		
		temp_id = Variables.DEFENDER_START_ID;
		for (Quotation quotation : defenders) {
			quotation.setGeneratedId(temp_id);
			temp_id++;
		}
		
		temp_id = Variables.MIDFIELD_START_ID;

		for (Quotation quotation : midfielders) {
			quotation.setGeneratedId(temp_id);
			temp_id++;
		}
		
		temp_id = Variables.FORWARD_START_ID;
		for (Quotation quotation : strikers) {
			quotation.setGeneratedId(temp_id);
			temp_id++;
		}
		
		List<Quotation> orderedQuotationList = new ArrayList<Quotation>();
		orderedQuotationList.addAll(goalkeepers);
		orderedQuotationList.addAll(defenders);
		orderedQuotationList.addAll(midfielders);
		orderedQuotationList.addAll(strikers);
		
		return orderedQuotationList;
	}
	
	public List<Quotation> checkForDuplicatedNamesInQuotations(List<Quotation> quotations){
		List<Quotation> duplicatedNames = new ArrayList<Quotation>();
		
		for (int i = 0; i < quotations.size(); i++) {
			for (int j = 0; j < quotations.size(); j++) {
				if (quotations.get(i).getName().equals(quotations.get(j).getName()) &&
						quotations.get(i).getId() != quotations.get(j).getId()) {
					duplicatedNames.add(quotations.get(i));
					quotations.remove(i);
				}
			}
		}
		
		return duplicatedNames;
	}
	
	public List<Quotation> checkForDuplicatedIdsInQuotations(List<Quotation> quotations){
		List<Quotation> duplicatedIds = new ArrayList<Quotation>();
		
		for (int i = 0; i < quotations.size(); i++) {
			for (int j = 0; j < quotations.size(); j++) {
				if (quotations.get(i).getId() == (quotations.get(j).getId()) &&
						!quotations.get(i).getName().equals(quotations.get(j).getName())) {
					duplicatedIds.add(quotations.get(i));
					quotations.remove(i);
				}
			}
		}
		
		return duplicatedIds;
	}

	public void generateQuotationFile(List<Quotation> quotations, String destination_path) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer;
		writer = new PrintWriter(destination_path+"\\"+Variables.QUOTATION_FILENAME, "UTF-8");
		
		for(int i = 0; i < quotations.size(); i++) {
			if (i != quotations.size()-1) {
				writer.println(generateMCCStringFromQuotation(quotations.get(i)));
			}else {
				writer.print(generateMCCStringFromQuotation(quotations.get(i)));
			}
			
		}
		writer.flush();
		writer.close();
	}
	
	public void generateTeamsFile(List<Quotation> quotations, String destination_path) throws FileNotFoundException, UnsupportedEncodingException {
		ArrayList<String> teams = new ArrayList<String>();
		PrintWriter writer;
		writer = new PrintWriter(destination_path+"\\"+Variables.TEAMS_FILENAME, "UTF-8");
		
		for (Quotation q : quotations) {
			if (!q.getTeam().equals("") && !teams.contains(q.getTeam())) {
				teams.add(q.getTeam());
			}
		}
		Collections.sort(teams);
		for (int i = 0; i < teams.size(); i++) {
			if (i != teams.size()-1) {
				writer.println(teams.get(i));
			}else {
				writer.print(teams.get(i));
			}
			
		}
		writer.flush();
		writer.close();
	}
	
	private String generateMCCStringFromQuotation(Quotation q) {
		return q.getGeneratedId() + "|" +
				"0" + "|" +
				"\"" + q.getName() + "\"" + "|" +
				"\"" + q.getTeam() + "\"" + "|" +
				"1" + "|" +
				q.getRole().getNumberRole() + "|" +
				"0" + "|" +
				"0.0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0.0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				"0" + "|" +
				q.getCurrentQuotation();
	}

	public void generateMapTable(List<Quotation> quotations, String destination_path) throws FileNotFoundException, UnsupportedEncodingException {
		
		PrintWriter writer;
		writer = new PrintWriter(destination_path+"\\"+Variables.MAPTABLE_FILENAME, "UTF-8");
			
		for (int i = 0; i < quotations.size(); i++) {
			if (i != quotations.size()-1) {
				writer.println(quotations.get(i).getGeneratedId() + "|" +
								quotations.get(i).getId() + "|" + 
								quotations.get(i).getName());
			}else {
				writer.print(quotations.get(i).getGeneratedId() + "|" +
						quotations.get(i).getId() + "|" + 
						quotations.get(i).getName());
			}
			
		}
		writer.flush();
		writer.close();
	}

	
	public List<Quotation> findNewFootballersAndGenerateIDs(List<Quotation> newQuotations, List<Footballer> existingPlayers) {
		
		ArrayList<Quotation> newGoalkeepers = new ArrayList<Quotation>();
		ArrayList<Quotation> newDefenders = new ArrayList<Quotation>();
		ArrayList<Quotation> newMidfielders = new ArrayList<Quotation>();
		ArrayList<Quotation> newStrikers = new ArrayList<Quotation>();
		int highestGkId = 0;
		int highestDfId = 0;
		int highestMdId = 0;
		int highestStId = 0;
		
		for (int i = 0; i < existingPlayers.size(); i++) {
			
			//Define the highest Goalkeeper id assigned
			if (existingPlayers.get(i).getId() > highestGkId && 
					existingPlayers.get(i).getId() < Variables.DEFENDER_START_ID) {
				highestGkId = existingPlayers.get(i).getId();
			}
			
			//Define the highest Defender id assigned
			if (existingPlayers.get(i).getId() > highestDfId && 
					existingPlayers.get(i).getId() < Variables.MIDFIELD_START_ID) {
				highestDfId = existingPlayers.get(i).getId();
			}
			
			//Define the highest Midfield id assigned
			if (existingPlayers.get(i).getId() > highestMdId && 
					existingPlayers.get(i).getId() < Variables.FORWARD_START_ID) {
				highestMdId = existingPlayers.get(i).getId();
			}
			
			//Define the highest Striker id assigned
			if (existingPlayers.get(i).getId() > highestStId) {
				highestStId = existingPlayers.get(i).getId();
			}
			
		}
		
		Collections.sort(newQuotations);
		
		for (int i = 0; i < newQuotations.size(); i++) {
			if (0 == newQuotations.get(i).getGeneratedId()) {
				switch (newQuotations.get(i).getRole()) {
				
				case P:
					highestGkId = highestGkId+1;
					newQuotations.get(i).setGeneratedId(highestGkId);
					newGoalkeepers.add(newQuotations.get(i));
					break;
				case D:
					highestDfId = highestDfId+1;
					newQuotations.get(i).setGeneratedId(highestDfId);
					newDefenders.add(newQuotations.get(i));
					break;
				case C:
					highestMdId = highestMdId+1;
					newQuotations.get(i).setGeneratedId(highestMdId);
					newMidfielders.add(newQuotations.get(i));
					break;
				case A:
					highestStId = highestStId+1;
					newQuotations.get(i).setGeneratedId(highestStId);
					newStrikers.add(newQuotations.get(i));
					break;
				default:
					System.out.println("Warning: role not found for this footballer/quotation");
					break;
				}	
			}
		}
		
		newGoalkeepers.addAll(newDefenders);
		newGoalkeepers.addAll(newMidfielders);
		newGoalkeepers.addAll(newStrikers);
		
		return newGoalkeepers;
		
	}

	public List<Quotation> mergeGeneratedIDs(List<Quotation> newQuotations, List<IndexEntry> ids) {
		
		for (int i = 0; i < newQuotations.size(); i++) {
			for (int j = 0; j < ids.size(); j++) {
				if (ids.get(j).getDraftIndex() == newQuotations.get(i).getId()){
					newQuotations.get(i).setGeneratedId(ids.get(j).getGeneratedIndex());
				}
			}
		}
		
		return newQuotations;
	}
	
	public List<IndexEntry> updateIDsList(List<IndexEntry> ids, List<Quotation> newPlayers){
		
		for (int i = 0; i < newPlayers.size(); i++) {
			IndexEntry ie = new IndexEntry();
			ie.setDraftIndex(newPlayers.get(i).getId());
			ie.setGeneratedIndex(newPlayers.get(i).getGeneratedId());
			ie.setPlayerName(newPlayers.get(i).getName());
			
			ids.add(ie);
		}
		
		ids.sort(Comparator.comparingInt(IndexEntry::getGeneratedIndex));
		
		return ids;
		
	}
	
	public List<Footballer> updateTransferStatus (List<Footballer> existingPlayers, List<Quotation> newQuotations ){
		
		for (int i = 0; i < existingPlayers.size(); i++) {
			//check if the footballer is moved away:
			if (checkIfMovedAway(existingPlayers.get(i), newQuotations)) {
				existingPlayers.get(i).setTrasferito(0);
			}
		}
		
		return existingPlayers;
	}
	
	private boolean checkIfMovedAway(Footballer footb, List<Quotation> newQuotations) {
		
		for (int i = 0; i < newQuotations.size(); i++) {
			if (newQuotations.get(i).getName().equals(footb.getCognome())) {
				return false;
			}	
		}
		return true;
	}

	public List<Footballer> addNewPlayers(List<Footballer> existingPlayers, List<Quotation> newPlayers) {
		
		for (int i = 0; i < newPlayers.size(); i++) {
			Footballer fb = new Footballer();
			fb.setId(newPlayers.get(i).getGeneratedId());
			fb.setCognome(newPlayers.get(i).getName());
			fb.setRuolo(newPlayers.get(i).getRole().getNumberRole());
			fb.setSquadra(newPlayers.get(i).getTeam());
			fb.setValore((int) (newPlayers.get(i).getCurrentQuotation()));
			fb.setTrasferito(1);
			existingPlayers.add(fb);
		}
		
		existingPlayers.sort(Comparator.comparingDouble(Footballer::getId));
		
		return existingPlayers;
	}

	public void printMapTable(List<IndexEntry> ids, String outputPath) throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter writer;
		writer = new PrintWriter(outputPath+"\\"+Variables.MAPTABLE_FILENAME, "UTF-8");
			
		for (int i = 0; i < ids.size(); i++) {
			writer.println(ids.get(i).getGeneratedIndex() + "|" +
				ids.get(i).getDraftIndex() + "|" + 
				ids.get(i).getPlayerName());
	
		}
		
		writer.flush();
		writer.close();
		
	}

	public List<Footballer> updateExistingQuotations(List<Quotation> newQuotations, List<Footballer> existingPlayers) {
		
		for (int i = 0; i < existingPlayers.size(); i++) {
			for (int j = 0; j < newQuotations.size(); j++) {
				if (existingPlayers.get(i).getCognome().equals(newQuotations.get(j).getName())) {
					existingPlayers.get(i).setValore((int) newQuotations.get(j).getCurrentQuotation());
					existingPlayers.get(i).setSquadra(newQuotations.get(j).getTeam());
				}
			}
		}
		
		return existingPlayers;
	}


	public List<Footballer> updateFootballersName(List<Footballer> existingPlayers, List<Quotation> newQuotations, List<IndexEntry> ids) {
		for (int i = 0; i < newQuotations.size(); i++){
			for (int j = 0; j < ids.size(); j++){
				if (newQuotations.get(i).getId() == ids.get(j).getDraftIndex() &&
				!newQuotations.get(i).getName().equals(ids.get(j).getPlayerName())){
					System.out.println("Nuova correzione nome trovata: " + newQuotations.get(i).getName() + " - modificare il file dei calciatori manualmente (0 trasferito, 1 presente)");
					for (int k = 0 ; k < existingPlayers.size(); k++){
						if (existingPlayers.get(k).getId() == ids.get(j).getGeneratedIndex()){
							existingPlayers.get(k).setCognome(newQuotations.get(i).getName());
							ids.get(j).setPlayerName(newQuotations.get(i).getName());
						}
					}

				}
			}
		}
		return existingPlayers;

	}
}
