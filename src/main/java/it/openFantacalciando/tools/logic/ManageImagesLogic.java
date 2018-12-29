package it.openFantacalciando.tools.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import it.openFantacalciando.tools.models.IndexEntry;
import it.openFantacalciando.tools.operations.ManageImagesOperation;

public class ManageImagesLogic {
	
	ManageImagesOperation operation;
	private String pathIndexFile;
	private String sourceImagesPath;
	private String destinationImagesPath;
	private int heightImagesToResize;
	private boolean doResize;
	
	public ManageImagesLogic(String pathIndexFile, String sourceImagesPath, String destinationImagesPath, int heightImagesToResize, boolean doResize) {
		operation = new ManageImagesOperation();
		this.pathIndexFile = pathIndexFile;
		this.sourceImagesPath = sourceImagesPath;
		this.destinationImagesPath = destinationImagesPath;
		this.heightImagesToResize = heightImagesToResize;
		this.doResize = doResize;
		

	}
	
	public void processImages() throws FileNotFoundException {
		
		List<IndexEntry> indices = operation.readIndexEntries(pathIndexFile);
		operation.moveAndRenameImageFiles(indices,sourceImagesPath, destinationImagesPath);
		if (doResize) {
			try {
				operation.resizeImageFiles(destinationImagesPath, heightImagesToResize);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
