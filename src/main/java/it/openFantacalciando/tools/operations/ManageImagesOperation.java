package it.openFantacalciando.tools.operations;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import it.openFantacalciando.tools.global.Variables;
import it.openFantacalciando.tools.models.IndexEntry;

public class ManageImagesOperation extends Operation{
	
	public void copyImageFiles(String pathDestinationFolder) {
		
	}
	
	public void moveAndRenameImageFiles(List<IndexEntry> indices, String sourceFolderPath, String destinationFolderPath) {
		
		File folder = new File(sourceFolderPath);
		File[] listOfImages = folder.listFiles();
		
		for (File image : listOfImages) {
			if (image.isFile()) {
				IndexEntry indexEntryOfImage = null;
	
				for (IndexEntry index : indices) {
					int fileName = Integer.parseInt(image.getName().replaceAll(".png", ""));
					System.out.println(fileName + " - " + index.getDraftIndex());
					if (index.getDraftIndex() == fileName) {
						indexEntryOfImage = index;
						break;
					}
				}

				if (indexEntryOfImage != null) {
					
					image.renameTo(new File(destinationFolderPath + "\\" + indexEntryOfImage.getGeneratedIndex() + ".png"));
				} else {
					//throw warning, you have image of a player not in list
				}
			}
			
		}
	}

	public void resizeImageFiles(String sourceFolderPath, int height) throws IOException {
		File folder = new File(sourceFolderPath);
		File[] listOfImages = folder.listFiles();
		
		Path path = Paths.get(sourceFolderPath+ "\\resized");
		Files.createDirectory(path);
		
		for (File file :  listOfImages) {
			
			BufferedImage image = ImageIO.read(file);
			BufferedImage resized = resize(image,height,height);

			File output = new File(sourceFolderPath+ "\\resized\\" + file.getName());
			ImageIO.write(resized, "png", output);
			
		}
		
	}
	
    private static BufferedImage resize(BufferedImage img, int height, int width) {
    	//took from https://memorynotfound.com/java-resize-image-fixed-width-height-example/
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public List<IndexEntry> fetchMissingPhotos(List<IndexEntry> indices, String sourceImagesPath) {
		List<IndexEntry> missingPhotos = new ArrayList<IndexEntry>();
		File image;
		for (IndexEntry index : indices) {
			image = new File(sourceImagesPath+"\\"+index.getDraftIndex()+".png");
			System.out.println("Cerco: " + sourceImagesPath+"\\"+index.getDraftIndex()+".png");
			if (!image.exists()){
				missingPhotos.add(index);
			}
		}

		return missingPhotos;
    }

	public void writeMissingPhotoList(String sourceImagesPath, List<IndexEntry> missingPhotos) throws FileNotFoundException {
		PrintWriter writer;
		writer = new PrintWriter(sourceImagesPath + "\\" + Variables.MISSINGPHOTO_FILENAME);

		for (IndexEntry missingPhoto : missingPhotos){
			writer.println(missingPhoto.getDraftIndex() + " - " + missingPhoto.getPlayerName() + " (" + missingPhoto.getGeneratedIndex()+")");
		}
		writer.flush();
		writer.close();
	}
}
