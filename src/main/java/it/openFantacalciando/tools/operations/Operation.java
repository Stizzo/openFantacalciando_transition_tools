package it.openFantacalciando.tools.operations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import it.openFantacalciando.tools.models.IndexEntry;
import it.openFantacalciando.tools.global.Variables;
import it.openFantacalciando.tools.models.Footballer;

public class Operation {

	public List<IndexEntry> readIndexEntries(String filePath) throws FileNotFoundException {
		List<IndexEntry> entries = new ArrayList<IndexEntry>();

		String line;
		FileReader reader = new FileReader(filePath);
		Scanner scanner = new Scanner(reader);

		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			String[] arraySplittate = line.split("\\|");

			IndexEntry ie = new IndexEntry(Integer.parseInt(arraySplittate[0].trim()),
					Integer.parseInt(arraySplittate[1].trim()), arraySplittate[2].trim());

			entries.add(ie);

		}
		scanner.close();

		return entries;
	}

	public List<Footballer> readFootballersList(String existingFootballersListPath) throws FileNotFoundException {
		ArrayList<Footballer> playerList = new ArrayList<Footballer>();

		Scanner scanner = null;
		String line;
		FileReader reader;

		reader = new FileReader(existingFootballersListPath);
		scanner = new Scanner(reader);

		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			String[] arraySplittate = line.split("\\|");

			// Creazione di un calciatore scartando i parametri "di giornata"
			Footballer a = new Footballer(Integer.parseInt(arraySplittate[0]), // id
					Integer.parseInt(arraySplittate[1]), // giornata
					arraySplittate[2].replaceAll("\"", "").trim(), // cognome
					arraySplittate[3], // squadra
					Integer.parseInt(arraySplittate[4]), // trasferito
					Integer.parseInt(arraySplittate[5]), // ruolo
					0, // presenza
					0.0, // fantavoto
					0, // minutiInf
					0, // minutiSup
					0.0, // voto
					0, // golSeg
					0, // golSub
					0, // golVit
					0, // golPar
					0, // assist
					0, // amm
					0, // esp
					0, // rigTir
					0, // rigSub
					0, // rigPar
					0, // rigSba
					0, // autogol
					0, // entrato
					0, // titolare
					0, // senzavt
					0, // incasa
					Integer.parseInt(arraySplittate[27])); // valore Il valore rimane aggiornato nel caso venga
															// trasferito
			playerList.add(a);

		}
		scanner.close();

		return playerList;
	}

	public void printMCC(List<Footballer> existingPlayers, String outputPath)
			throws FileNotFoundException, UnsupportedEncodingException { // Stampa il file MCC finito

		PrintWriter writer = new PrintWriter(outputPath +"\\"+Variables.QUOTATION_FILENAME, "UTF-8");
		for (int i = 0; i < existingPlayers.size(); i++) {

			writer.println(existingPlayers.get(i).getId() + "|" + "0|\"" + existingPlayers.get(i).getCognome() + "\"|"
					+ existingPlayers.get(i).getSquadra() + "|" + existingPlayers.get(i).getTrasferito() + "|" + existingPlayers.get(i).getRuolo()
					+ "|" + existingPlayers.get(i).getPresenza() + "|"
					// +a.get(i).getFantavoto()+"|"
					+ "0.0|" + existingPlayers.get(i).getMinutiInf() + "|" + existingPlayers.get(i).getMinutiSup() + "|"
					// +a.get(i).getVoto()+"|"
					+ "0.0|" + existingPlayers.get(i).getGolSeg() + "|" + existingPlayers.get(i).getGolSub() + "|"
					+ existingPlayers.get(i).getGolVit() + "|" + existingPlayers.get(i).getGolPar() + "|" + existingPlayers.get(i).getAssist()
					+ "|" + existingPlayers.get(i).getAmm() + "|" + existingPlayers.get(i).getEsp() + "|" + existingPlayers.get(i).getRigTir()
					+ "|" + existingPlayers.get(i).getRigSub() + "|" + existingPlayers.get(i).getRigPar() + "|"
					+ existingPlayers.get(i).getRigSba() + "|" + existingPlayers.get(i).getAutogol() + "|" + existingPlayers.get(i).getEntrato()
					+ "|" + existingPlayers.get(i).getTitolare() + "|" + existingPlayers.get(i).getSenzavt() + "|"
					+ existingPlayers.get(i).getIncasa() + "|" + existingPlayers.get(i).getValore());
		}
		writer.close();

	}
}
