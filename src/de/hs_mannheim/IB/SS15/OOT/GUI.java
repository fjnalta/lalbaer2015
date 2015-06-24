package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import de.hs_mannheim.IB.SS15.OOT.Exceptions.FullCalendarException;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;

public class GUI extends JFrame implements ActionListener {

	private final static String TITLE = "IM-Planer";

	private Backend backend;

	// Men�leiste
	private JMenuBar jMenuBar;

	private JMenu file;
	private JMenuItem newFile;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem print;
	private JMenuItem exit;

	private JMenu info;
	private JMenuItem about;

	private JTabbedPane tabTable;
	private JTable tableMaster;
	private JTable tableProfessor;
	private JTable tableStudent;

	private JPanel east;
	private JPanel south;

	private JButton btnAddRoom;
	private JButton btnRemoveRoom;
	private JButton btnStudents;
	private JButton btnExaminer;
	private JButton btnSubjects;
	private JButton btnAddBreak;
	private JButton btnRemoveBreak;
	private JButton btnGeneratePlan;

	GUI() {
		setTitle(TITLE); // set title

		createNewMainController();

		createJMenuBar();
		createLayout();

		// -------------------------------
		// Options
		// -------------------------------

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();

		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {

		lookAndFeel();
		new GUI().setVisible(true); // create GUI

	}

	private static void lookAndFeel() {
		// set Look and Feel (Windows)
		UIManager.LookAndFeelInfo[] lfi = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < lfi.length; i++) {

			// Set Windows or OSX Look & Feel
			if (lfi[i].getName() == "Windows") {
				try {
					UIManager.setLookAndFeel(lfi[i].getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// JMenuBar
		if (e.getSource() == newFile) {

			backend = new Backend();

		} else if (e.getSource() == open) {
			// Opening FileChooser for open Dialog
			JFileChooser fc = new JFileChooser((File) null);

			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				loadObject(fc.getSelectedFile().toString());
			}
		} else if (e.getSource() == save) {
			// Opening FileChooser for save Dialog
			JFileChooser fc = new JFileChooser((File) null);

			if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				// plan object needs to be serializable
				saveObject(fc.getSelectedFile().toString());
			}
		} else if (e.getSource() == print) {
			try {
				tableMaster.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == exit) {
			System.out.println("Programm beenden");
			System.exit(0);
		} else if (e.getSource() == about) {
			JOptionPane.showMessageDialog(this, "LALBaer2015");
		}

		// EastButtons
		else if (e.getSource() == btnAddRoom) {

			Backend.rooms++;

			backend.updateSchedules();
			getContentPane().remove(tabTable);
			createTabTable();
			getContentPane().add(tabTable, BorderLayout.CENTER);

			revalidate();
			repaint();

		} else if (e.getSource() == btnRemoveRoom) {
			if (Backend.rooms > 1) {
				Backend.rooms--;

				backend.updateSchedules();
				getContentPane().remove(tabTable);
				createTabTable();
				getContentPane().add(tabTable, BorderLayout.CENTER);

				revalidate();
				repaint();
			} else {
				JOptionPane.showMessageDialog(this, "Es muss mindestens ein Raum existieren.", "Raum entfernen", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnStudents) {

			if (backend.getSubjects().size() <= 0) {
				JOptionPane.showMessageDialog(this, "Es sind noch keine F�cher vorhanden.", "Studenten", JOptionPane.ERROR_MESSAGE);
			} else {
				new StudentsGUI(this);
			}

		} else if (e.getSource() == btnSubjects) {
			new SubjectGUI(this);
		} else if (e.getSource() == btnExaminer) {
			new ExaminerGUI(this);
		} else if (e.getSource() == btnAddBreak) {
			new BreakGUI(this);
		} else if (e.getSource() == btnRemoveBreak) {
			removeBreakDialog();
		} else if (e.getSource() == btnGeneratePlan) {

			backend.createExaminer("Name", backend.subjects, new ArrayList<Desire>());

			try {
				backend.generateExams();
				backend.generateMasterTable();
			} catch (FullCalendarException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void saveObject(String filename) {
		ObjectOutputStream saver;
		try {
			saver = new ObjectOutputStream(new FileOutputStream(filename));
			saver.writeObject(backend);
			saver.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadObject(String filename) {
		ObjectInputStream loader;
		Backend loadedBackend = null;
		try {
			loader = new ObjectInputStream(new FileInputStream(filename));
			loadedBackend = (Backend) loader.readObject();
			loader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		backend = loadedBackend;
	}

	private void removeBreakDialog() {
		// dropdown Men� mit den m�glichen Pausen

		// TODO breaks??
	}

	public void createNewMainController() {
		backend = new Backend();

	}

	private void createJMenuBar() {
		jMenuBar = new JMenuBar();

		file = new JMenu("Datei");

		newFile = new JMenuItem("Neu");
		newFile.addActionListener(this);
		open = new JMenuItem("�ffnen");
		open.addActionListener(this);
		save = new JMenuItem("Speichern");
		save.addActionListener(this);
		print = new JMenuItem("Drucken");
		print.addActionListener(this);
		exit = new JMenuItem("Beenden");
		exit.addActionListener(this);

		info = new JMenu("Info");

		about = new JMenuItem("Über");
		about.addActionListener(this);

		file.add(newFile);
		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(print);
		file.addSeparator();
		file.add(exit);

		info.add(about);

		// add submenus to Bar
		jMenuBar.add(file);
		jMenuBar.add(info);
	}

	private void createLayout() {
		// set Layout
		getContentPane().setLayout(new BorderLayout());

		// NORTH Panel
		getContentPane().add(jMenuBar, BorderLayout.NORTH);

		// CENTER Panel
		createTabTable();
		getContentPane().add(tabTable, BorderLayout.CENTER);

		// SOUTH Panel
		createSouthButtons();
		getContentPane().add(south, BorderLayout.SOUTH);

		// EAST Panel
		createEastButtons();
		getContentPane().add(east, BorderLayout.EAST);
	}

	private void createTabTable() {
		tabTable = new JTabbedPane();

		createTable();

		JPanel master = new JPanel(new GridLayout());
		master.add(new JScrollPane(tableMaster));
		tabTable.addTab("Master", master);

		JPanel professor = new JPanel(new GridLayout());
		professor.add(new JScrollPane(tableProfessor));
		tabTable.addTab("Professor", professor);

		JPanel student = new JPanel(new GridLayout());
		student.add(new JScrollPane(tableStudent));
		tabTable.addTab("Student", student);

	}

	private void createTable() {

		System.out.println();

		// create JTables from DataModels
		tableMaster = new JTable(backend.getSchedule()[0].getTable());
		tableProfessor = new JTable(backend.getSchedule()[1].getTable());
		tableStudent = new JTable(backend.getSchedule()[2].getTable());

	}

	private void createSouthButtons() {

		south = new JPanel();
		south.setLayout(new GridLayout(1, 4));

		btnAddRoom = new JButton("Raum hinzuf�gen");
		btnAddRoom.addActionListener(this);
		south.add(btnAddRoom);

		btnRemoveRoom = new JButton("Raum entfernen");
		btnRemoveRoom.addActionListener(this);
		south.add(btnRemoveRoom);

		btnAddBreak = new JButton("Pause hinzuf�gen");
		btnAddBreak.addActionListener(this);
		south.add(btnAddBreak);

		btnRemoveBreak = new JButton("Pause entfernen");
		btnRemoveBreak.addActionListener(this);
		south.add(btnRemoveBreak);

	}

	private void createEastButtons() {

		east = new JPanel();
		east.setLayout(new GridLayout(3, 1));

		btnSubjects = new JButton("F�cher");
		btnSubjects.addActionListener(this);
		east.add(btnSubjects);

//		btnExaminer = new JButton("Pr�fer");
//		btnExaminer.addActionListener(this);
//		east.add(btnExaminer);

		btnStudents = new JButton("Studenten");
		btnStudents.addActionListener(this);
		east.add(btnStudents);

		btnGeneratePlan = new JButton("Plan erstellen");
		btnGeneratePlan.addActionListener(this);
		east.add(btnGeneratePlan);

	}

	public Backend getBackend() {
		return backend;
	}

}