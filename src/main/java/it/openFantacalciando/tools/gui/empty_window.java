package it.openFantacalciando.tools.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class empty_window extends JFrame{

	private static final long serialVersionUID = -3596060649888940443L;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					empty_window window = new empty_window();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public empty_window() {
		this.setTitle("Precampionato");
		this.getContentPane().setLayout(null);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblFonteQuotazioni = new JLabel("Fonte quotazioni:");
		lblFonteQuotazioni.setBounds(10, 10, 150, 13);
		getContentPane().add(lblFonteQuotazioni);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 6, 150, 21);
		getContentPane().add(comboBox);

	}
	
}
