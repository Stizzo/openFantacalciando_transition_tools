package it.openFantacalciando.tools.operations.drafters;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import it.openFantacalciando.tools.enums.PlayerRole;
import it.openFantacalciando.tools.models.fantagazzetta.FgQuotation;
import it.openFantacalciando.tools.operations.Drafter;

public class Fantagazzetta implements Drafter {
	
	public Fantagazzetta() {
		
	}

	@SuppressWarnings("resource")
	public List<FgQuotation> readQuotationsFromXlsFile(String source_path) {
		List<FgQuotation> quotations = new ArrayList<FgQuotation>();
		
		try {

			XSSFWorkbook wb =new XSSFWorkbook(new File(source_path));
			XSSFSheet sheet = wb.getSheetAt(0);
		
			Iterator<Row> rowIterator = sheet.iterator(); 
		
			while (rowIterator.hasNext()){
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				if (row.getRowNum() >=2){
					FgQuotation quotation = new FgQuotation();
					while (cellIterator.hasNext()){
						Cell cell = cellIterator.next();
						
						switch (cell.getColumnIndex()) {
						case 0:	
							quotation.setId((int) Float.parseFloat(cell.toString()));
							break;
						case 1:
							quotation.setRole(PlayerRole.valueOf(cell.toString()));
							break;
						case 2:
							quotation.setName(cell.toString());
							break;
						case 3:
							quotation.setTeam(cell.toString());
							break;
						case 4: 
							quotation.setCurrentQuotation((long) Float.parseFloat(cell.toString()));
							break;
						case 5:
							quotation.setInitialQuotation((long) Float.parseFloat(cell.toString()));
							break;
						case 6:
							quotation.setDifference((long) Float.parseFloat(cell.toString()));
							break;
						}

					}

					quotations.add(quotation);
				}
				
			}
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return quotations;
	}
	
	
	
}
