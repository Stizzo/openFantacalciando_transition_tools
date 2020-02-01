package it.openFantacalciando.tools.global;

import javax.swing.JFileChooser;

public class Variables {

	public static String DOCUMENT_PATH = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	public static String TEAMS_FILENAME = "squadre_" + System.currentTimeMillis() + ".txt";
	public static String QUOTATION_FILENAME = "calciatori_" + System.currentTimeMillis() + ".txt";
	public static String MAPTABLE_FILENAME = "IdMaps_NON_CANCELLARE_" + System.currentTimeMillis() + ".txt";
	public static String MISSINGPHOTO_FILENAME = "Foto_Mancanti_" + System.currentTimeMillis() + ".txt";
	public static int GOALKEEPER_START_ID = 101;
	public static int DEFENDER_START_ID = 201;
	public static int MIDFIELD_START_ID = 501;
	public static int FORWARD_START_ID = 801;
	
}
