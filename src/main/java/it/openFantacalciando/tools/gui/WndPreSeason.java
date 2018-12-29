package it.openFantacalciando.tools.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.DefaultComboBoxModel;
import it.openFantacalciando.tools.enums.DataSource;
import it.openFantacalciando.tools.global.Variables;
import it.openFantacalciando.tools.logic.PreSeasonLogic;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class WndPreSeason extends JFrame {

	private static final long serialVersionUID = -3596060649888940443L;
	private JTextField txtQuotationSourceFile;
	private JTextField txtDestinationPath;
	private JTextField txtMCCFile;
	private JTextField txtIDsFile;
	private JCheckBox chckbxUpdateList;
	private JButton btnChooseMCCFile;
	private JButton btnChooseIDsFile;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WndPreSeason window = new WndPreSeason();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public WndPreSeason() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
			}
		});
		this.setTitle("Precampionato");
		this.getContentPane().setLayout(null);
		this.setBounds(100, 100, 576, 254);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblFonteQuotazioni = new JLabel("Fonte quotazioni:");
		lblFonteQuotazioni.setBounds(10, 10, 150, 13);
		getContentPane().add(lblFonteQuotazioni);

		final JComboBox cmbDataSource = new JComboBox();
		cmbDataSource.setModel(new DefaultComboBoxModel(DataSource.values()));
		cmbDataSource.setBounds(170, 6, 150, 21);
		getContentPane().add(cmbDataSource);

		JLabel lblPercorsoFileQuotazioni = new JLabel("Percorso file quotazioni:");
		lblPercorsoFileQuotazioni.setBounds(10, 33, 150, 13);
		getContentPane().add(lblPercorsoFileQuotazioni);

		txtQuotationSourceFile = new JTextField();
		txtQuotationSourceFile.setEditable(false);
		txtQuotationSourceFile.setBounds(170, 30, 291, 19);
		getContentPane().add(txtQuotationSourceFile);
		txtQuotationSourceFile.setColumns(10);

		JButton bntSfogliaFileQuotazioni = new JButton("Sfoglia...");
		bntSfogliaFileQuotazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Seleziona il file xls con le quotazioni");
				// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);
				txtQuotationSourceFile.setText(chooser.getSelectedFile().toString());
			}
		});
		bntSfogliaFileQuotazioni.setBounds(471, 29, 85, 21);
		getContentPane().add(bntSfogliaFileQuotazioni);

		JLabel lblPercorsoDestinazione = new JLabel("Percorso destinazione:");
		lblPercorsoDestinazione.setBounds(10, 56, 150, 13);
		getContentPane().add(lblPercorsoDestinazione);

		txtDestinationPath = new JTextField();
		txtDestinationPath.setEditable(false);
		txtDestinationPath.setBounds(170, 53, 291, 19);
		getContentPane().add(txtDestinationPath);
		txtDestinationPath.setColumns(10);

		txtDestinationPath.setText(Variables.DOCUMENT_PATH);

		JButton btnSfogliaPercorsoDestinazione = new JButton("Sfoglia...");
		btnSfogliaPercorsoDestinazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Seleziona il percorso per i files di destinazione");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);
				txtDestinationPath.setText(chooser.getSelectedFile().toString());
			}
		});
		btnSfogliaPercorsoDestinazione.setBounds(471, 52, 85, 21);
		getContentPane().add(btnSfogliaPercorsoDestinazione);

		chckbxUpdateList = new JCheckBox("Aggiorna lista calciatori");
		chckbxUpdateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			btnChooseMCCFile.setEnabled(chckbxUpdateList.isSelected());
			btnChooseIDsFile.setEnabled(chckbxUpdateList.isSelected());
			txtMCCFile.setEnabled(chckbxUpdateList.isSelected());
			txtIDsFile.setEnabled(chckbxUpdateList.isSelected());
			}
		});
		chckbxUpdateList.setBounds(6, 75, 150, 21);
		getContentPane().add(chckbxUpdateList);

		JLabel lblPercorsoFileIds = new JLabel("File Calciatori / MCC");
		lblPercorsoFileIds.setBounds(10, 102, 150, 13);
		getContentPane().add(lblPercorsoFileIds);

		txtMCCFile = new JTextField();
		txtMCCFile.setEditable(false);
		txtMCCFile.setColumns(10);
		txtMCCFile.setBounds(170, 99, 291, 19);
		getContentPane().add(txtMCCFile);

		btnChooseMCCFile = new JButton("Sfoglia...");
		btnChooseMCCFile.setEnabled(false);
		btnChooseMCCFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Seleziona il file calciatori /MCC");
				// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);
				txtMCCFile.setText(chooser.getSelectedFile().toString());
			}
		});
		btnChooseMCCFile.setBounds(471, 98, 85, 21);
		getContentPane().add(btnChooseMCCFile);
		
		JLabel lblFileIds = new JLabel("File IDs / Map");
		lblFileIds.setBounds(10, 129, 150, 13);
		getContentPane().add(lblFileIds);
		
		txtIDsFile = new JTextField();
		txtIDsFile.setEditable(false);
		txtIDsFile.setColumns(10);
		txtIDsFile.setBounds(170, 126, 291, 19);
		getContentPane().add(txtIDsFile);
		
		btnChooseIDsFile = new JButton("Sfoglia...");
		btnChooseIDsFile.setEnabled(false);
		btnChooseIDsFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Seleziona il file con gli ID");
				// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(null);
				txtIDsFile.setText(chooser.getSelectedFile().toString());
			}
		});
		btnChooseIDsFile.setBounds(471, 125, 85, 21);
		getContentPane().add(btnChooseIDsFile);

		
		JButton btnGenerateInitialFiles = new JButton("Genera files iniziali");
		btnGenerateInitialFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//If the datasource is Fantagazzetta
				if (cmbDataSource.getSelectedItem().toString().equals(DataSource.FG.getSourceName())) {
					PreSeasonLogic psl = new PreSeasonLogic();
					//need to update an existing list
					if (chckbxUpdateList.isSelected()) {
						try {
							psl.updateQuotations(DataSource.FG, txtQuotationSourceFile.getText(), txtIDsFile.getText(), txtMCCFile.getText(), txtDestinationPath.getText());
							JOptionPane.showMessageDialog(null, "Quotation updated successfully!");
						} catch (FileNotFoundException | UnsupportedEncodingException e) {
							JOptionPane.showMessageDialog(null, "Generic error found while generating quotations", "Error!",
									JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					} else {
						//need to create a new list
						try {
							psl.generateQuotations(DataSource.FG, txtQuotationSourceFile.getText(),
									txtDestinationPath.getText());
							JOptionPane.showMessageDialog(null, "Quotation generated successfully!");
						} catch (FileNotFoundException | UnsupportedEncodingException e) {
							JOptionPane.showMessageDialog(null, "Generic error found while generating quotations", "Error!",
									JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					}
					
				}

			}
		});

		btnGenerateInitialFiles.setBounds(406, 183, 150, 21);
		getContentPane().add(btnGenerateInitialFiles);

		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(10, 183, 85, 21);
		getContentPane().add(btnBack);
	}
}
