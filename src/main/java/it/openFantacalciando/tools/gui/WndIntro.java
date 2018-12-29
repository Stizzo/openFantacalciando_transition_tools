package it.openFantacalciando.tools.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WndIntro extends JFrame{


	private static final long serialVersionUID = -5860566603622674291L;
	private JFrame frmTest;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WndIntro window = new WndIntro();
					window.frmTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public WndIntro() {
		initialize();
	}


	private void initialize() {
		frmTest = new JFrame();
		frmTest.setResizable(false);
		frmTest.setTitle("OpenFantacalciando Tools");
		frmTest.setBounds(100, 100, 509, 624);
		frmTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTest.getContentPane().setLayout(null);
		
		JButton btnPrecampionato = new JButton("Precampionato e lista calciatori");
		btnPrecampionato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmTest.setVisible(false);
				WndPreSeason precampionato = new WndPreSeason();
				precampionato.setVisible(true);
			
			}
		});
		btnPrecampionato.setBounds(10, 10, 475, 142);
		frmTest.getContentPane().add(btnPrecampionato);
		
		JButton btnGestioneFotoCalciatori = new JButton("Gestione foto calciatori");
		btnGestioneFotoCalciatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmTest.setVisible(false);
				WndManagePlayersImages mpi = new WndManagePlayersImages();
				mpi.setVisible(true);
			}
		});
		btnGestioneFotoCalciatori.setBounds(10, 162, 475, 142);
		frmTest.getContentPane().add(btnGestioneFotoCalciatori);
	}
}
