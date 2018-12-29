package it.openFantacalciando.tools.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import it.openFantacalciando.tools.logic.ManageImagesLogic;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class WndManagePlayersImages extends JFrame{

	private static final long serialVersionUID = -3596060649888940443L;
	private JTextField txtPercorsoImmagini;
	private JTextField txtPercorsoDestinazione;
	private JTextField txtPercorsoFileIndici;
	private JTextField txtAltezzaImmagine;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WndManagePlayersImages window = new WndManagePlayersImages();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public WndManagePlayersImages() {
		this.setTitle("Gestione Foto Calciatori");
		this.getContentPane().setLayout(null);
		this.setBounds(100, 100, 633, 215);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblFonteQuotazioni = new JLabel("Percorso cartella immagini:");
		lblFonteQuotazioni.setBounds(10, 41, 215, 13);
		getContentPane().add(lblFonteQuotazioni);
		
		txtPercorsoImmagini = new JTextField();
		txtPercorsoImmagini.setEditable(false);
		txtPercorsoImmagini.setBounds(235, 38, 246, 19);
		getContentPane().add(txtPercorsoImmagini);
		txtPercorsoImmagini.setColumns(10);
		
		JButton btnSfogliaSorgenteImmagini = new JButton("Sfoglia...");
		btnSfogliaSorgenteImmagini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Seleziona il percorso delle immagini");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);
				txtPercorsoImmagini.setText(chooser.getSelectedFile().toString());	
			}
		});
		btnSfogliaSorgenteImmagini.setBounds(491, 37, 85, 21);
		getContentPane().add(btnSfogliaSorgenteImmagini);
		
		JLabel lblPercorsoCartellaDestinazione = new JLabel("Percorso cartella destinazione:");
		lblPercorsoCartellaDestinazione.setBounds(10, 68, 215, 13);
		getContentPane().add(lblPercorsoCartellaDestinazione);
		
		txtPercorsoDestinazione = new JTextField();
		txtPercorsoDestinazione.setEditable(false);
		txtPercorsoDestinazione.setColumns(10);
		txtPercorsoDestinazione.setBounds(235, 65, 246, 19);
		getContentPane().add(txtPercorsoDestinazione);
		
		JButton btnSfogliaCartellaDestinazione = new JButton("Sfoglia...");
		btnSfogliaCartellaDestinazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Seleziona il percorso dove salvare le immagini");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);
				txtPercorsoDestinazione.setText(chooser.getSelectedFile().toString());	
			}
		});
		btnSfogliaCartellaDestinazione.setBounds(491, 64, 85, 21);
		getContentPane().add(btnSfogliaCartellaDestinazione);
		
		JLabel lblPercorsoFileIndici = new JLabel("Percorso file indici:");
		lblPercorsoFileIndici.setBounds(10, 14, 215, 13);
		getContentPane().add(lblPercorsoFileIndici);
		
		txtPercorsoFileIndici = new JTextField();
		txtPercorsoFileIndici.setEditable(false);
		txtPercorsoFileIndici.setColumns(10);
		txtPercorsoFileIndici.setBounds(235, 11, 246, 19);
		getContentPane().add(txtPercorsoFileIndici);
		
		JButton bntSfogliaIndici = new JButton("Sfoglia...");
		bntSfogliaIndici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Seleziona il file txt con gli indici");
				//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);
				txtPercorsoFileIndici.setText(chooser.getSelectedFile().toString());
			}
		});
		bntSfogliaIndici.setBounds(491, 10, 85, 21);
		getContentPane().add(bntSfogliaIndici);
		
		
		
		JLabel lblModificaAltezzaImmagini = new JLabel("Modifica altezza immagini:");
		lblModificaAltezzaImmagini.setBounds(10, 115, 150, 13);
		getContentPane().add(lblModificaAltezzaImmagini);
		
		txtAltezzaImmagine = new JTextField();
		txtAltezzaImmagine.setEditable(false);
		txtAltezzaImmagine.setBounds(170, 112, 57, 19);
		getContentPane().add(txtAltezzaImmagine);
		txtAltezzaImmagine.setColumns(10);
		
		final JCheckBox chckbxRidimensionaImmagini = new JCheckBox("Ridimensiona immagini");
		chckbxRidimensionaImmagini.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (chckbxRidimensionaImmagini.isSelected()) {
					txtAltezzaImmagine.setEditable(true);
				}else {
					txtAltezzaImmagine.setEditable(false);
				}
			}
		});
		chckbxRidimensionaImmagini.setBounds(6, 87, 219, 21);
		getContentPane().add(chckbxRidimensionaImmagini);

		JButton btnConverti = new JButton("Converti");
		btnConverti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageImagesLogic mil = new ManageImagesLogic(txtPercorsoFileIndici.getText(),
						txtPercorsoImmagini.getText(),
						txtPercorsoDestinazione.getText(),
						Integer.parseInt(txtAltezzaImmagine.getText()),
						chckbxRidimensionaImmagini.isSelected()
						);
				try {
					mil.processImages();
					JOptionPane.showMessageDialog(null, "Immagini trasformate con successo!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Errore generico durante l'elaborazione delle immagini", "Error!",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		btnConverti.setBounds(491, 111, 85, 21);
		getContentPane().add(btnConverti);
	}
}
