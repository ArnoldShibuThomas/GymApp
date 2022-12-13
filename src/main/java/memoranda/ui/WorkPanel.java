package main.java.memoranda.ui;

import main.java.memoranda.util.Context;
import main.java.memoranda.util.JSONTool;
import main.java.memoranda.util.Local;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/*$Id: WorkPanel.java,v 1.9 2004/04/05 10:05:44 alexeya Exp $*/
public class WorkPanel extends JPanel {
	BorderLayout borderLayout1 = new BorderLayout();
	JToolBar toolBar = new JToolBar();
	JPanel panel = new JPanel();
	CardLayout cardLayout1 = new CardLayout();

	public JButton notesB = new JButton();
	public DailyItemsPanel dailyItemsPanel;
	public ResourcesPanel filesPanel = new ResourcesPanel();

	//create a JSONTool object to pass to each class that needs to update lists
	private JSONTool modifyJSONFile = null;

	//add the owner specific panels here
	public StudentsPanel studentsPanel;
	public TrainersPanel trainersPanel;

	public JButton agendaB = new JButton();
	public JButton tasksB = new JButton();
	public JButton eventsB = new JButton();
	public JButton filesB = new JButton();
	public JButton studentsB = new JButton();
	public JButton trainersB = new JButton();
	public JButton refreshB = new JButton();
	JButton currentB = null;
	Border border1;

	//chosen view
	int userChosenView;

	public WorkPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}
	
	public WorkPanel(JSONTool jsontool) {
		try {
			//get the user selected mode
			userChosenView = jsontool.getView();
			//set modify list to list we got
			modifyJSONFile = jsontool;
			// have the file be read
			modifyJSONFile.readFromJSON();

			dailyItemsPanel = new DailyItemsPanel(this,modifyJSONFile);

			//instantiate student and trainer panel
			trainersPanel = new TrainersPanel(modifyJSONFile);
			studentsPanel = new StudentsPanel(modifyJSONFile);

			//then do a jbinit
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

	void jbInit() throws Exception {
		border1 =
			BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(
					BevelBorder.LOWERED,
					Color.white,
					Color.white,
					new Color(124, 124, 124),
					new Color(178, 178, 178)),
				BorderFactory.createEmptyBorder(0, 2, 0, 0));

		this.setLayout(borderLayout1);
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.setBackground(Color.white);

		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false);
		panel.setLayout(cardLayout1);

		agendaB.setBackground(Color.white);
		agendaB.setMaximumSize(new Dimension(60, 80));
		agendaB.setMinimumSize(new Dimension(30, 30));

		agendaB.setFont(new java.awt.Font("Dialog", 1, 10));
		agendaB.setPreferredSize(new Dimension(50, 50));
		agendaB.setBorderPainted(false);
		agendaB.setContentAreaFilled(false);
		agendaB.setFocusPainted(false);
		agendaB.setHorizontalTextPosition(SwingConstants.CENTER);
		agendaB.setText(Local.getString("Agenda"));
		agendaB.setVerticalAlignment(SwingConstants.TOP);
		agendaB.setVerticalTextPosition(SwingConstants.BOTTOM);
		agendaB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendaB_actionPerformed(e);
			}
		});
		agendaB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/agenda.png")));
		agendaB.setOpaque(false);
		agendaB.setMargin(new Insets(0, 0, 0, 0));
		agendaB.setSelected(true);

		eventsB.setBackground(Color.white);
		eventsB.setMaximumSize(new Dimension(60, 80));
		eventsB.setMinimumSize(new Dimension(30, 30));

		eventsB.setFont(new java.awt.Font("Dialog", 1, 10));
		eventsB.setPreferredSize(new Dimension(50, 50));
		eventsB.setBorderPainted(false);
		eventsB.setContentAreaFilled(false);
		eventsB.setFocusPainted(false);
		eventsB.setHorizontalTextPosition(SwingConstants.CENTER);
		eventsB.setText(Local.getString("Availability"));
		eventsB.setVerticalAlignment(SwingConstants.TOP);
		eventsB.setVerticalTextPosition(SwingConstants.BOTTOM);
		eventsB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsB_actionPerformed(e);
			}
		});
		eventsB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/events.png")));
		eventsB.setOpaque(false);
		eventsB.setMargin(new Insets(0, 0, 0, 0));
		//eventsB.setSelected(true);

		tasksB.setSelected(true);
		tasksB.setFont(new java.awt.Font("Dialog", 1, 10));
		tasksB.setMargin(new Insets(0, 0, 0, 0));
		tasksB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/tasks.png")));
		tasksB.setVerticalTextPosition(SwingConstants.BOTTOM);
		tasksB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tasksB_actionPerformed(e);
			}
		});
		tasksB.setVerticalAlignment(SwingConstants.TOP);
		tasksB.setText(Local.getString("Tasks"));
		tasksB.setHorizontalTextPosition(SwingConstants.CENTER);
		tasksB.setFocusPainted(false);
		tasksB.setBorderPainted(false);
		tasksB.setContentAreaFilled(false);
		tasksB.setPreferredSize(new Dimension(50, 50));
		tasksB.setMinimumSize(new Dimension(30, 30));
		tasksB.setOpaque(false);
		tasksB.setMaximumSize(new Dimension(60, 80));
		tasksB.setBackground(Color.white);

		notesB.setFont(new java.awt.Font("Dialog", 1, 10));
		notesB.setBackground(Color.white);
		notesB.setBorder(null);
		notesB.setMaximumSize(new Dimension(60, 80));
		notesB.setMinimumSize(new Dimension(30, 30));
		notesB.setOpaque(false);
		notesB.setPreferredSize(new Dimension(60, 50));
		notesB.setBorderPainted(false);
		notesB.setContentAreaFilled(false);
		notesB.setFocusPainted(false);
		notesB.setHorizontalTextPosition(SwingConstants.CENTER);
		notesB.setText(Local.getString("Notes"));
		notesB.setVerticalAlignment(SwingConstants.TOP);
		notesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		notesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notesB_actionPerformed(e);
			}
		});
		notesB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/notes.png")));
		notesB.setMargin(new Insets(0, 0, 0, 0));
		notesB.setSelected(true);
		this.setPreferredSize(new Dimension(1073, 300));

		filesB.setSelected(true);
		filesB.setMargin(new Insets(0, 0, 0, 0));
		filesB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/files.png")));
		filesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		filesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filesB_actionPerformed(e);
			}
		});
		filesB.setFont(new java.awt.Font("Dialog", 1, 10));
		filesB.setVerticalAlignment(SwingConstants.TOP);
		filesB.setText(Local.getString("Resources"));
		filesB.setHorizontalTextPosition(SwingConstants.CENTER);
		filesB.setFocusPainted(false);
		filesB.setBorderPainted(false);
		filesB.setContentAreaFilled(false);
		filesB.setPreferredSize(new Dimension(50, 50));
		filesB.setMinimumSize(new Dimension(30, 30));
		filesB.setOpaque(false);
		filesB.setMaximumSize(new Dimension(60, 80));
		filesB.setBackground(Color.white);

		//refresh code
		System.out.println("[DEBUG]: Adding refresh button");
		refreshB.setSelected(true);
		refreshB.setMargin(new Insets(0, 0, 0, 0));
		refreshB.setIcon(
				new ImageIcon(
						main.java.memoranda.ui.AppFrame.class.getResource(
								"/ui/icons/refresh.png")));
		refreshB.setVerticalTextPosition(SwingConstants.BOTTOM);
		refreshB.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				refreshB_actionPerformed(e);
			}
		});
		refreshB.setFont(new java.awt.Font("Dialog", 1, 10));
		refreshB.setVerticalAlignment(SwingConstants.TOP);
		refreshB.setText(Local.getString("Refresh"));
		refreshB.setHorizontalTextPosition(SwingConstants.CENTER);
		refreshB.setFocusPainted(false);
		refreshB.setBorderPainted(false);
		refreshB.setContentAreaFilled(false);
		refreshB.setPreferredSize(new Dimension(50, 50));
		refreshB.setMinimumSize(new Dimension(30, 30));
		refreshB.setOpaque(false);
		refreshB.setMaximumSize(new Dimension(60, 80));
		refreshB.setBackground(Color.white);


		//owner code
		studentsB.setSelected(true);
		studentsB.setFont(new java.awt.Font("Dialog", 1, 10));
		studentsB.setMargin(new Insets(0, 0, 0, 0));
		studentsB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/student.png")));
		studentsB.setVerticalTextPosition(SwingConstants.BOTTOM);
		studentsB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentsB_actionPerformed(e);
			}
		});
		studentsB.setVerticalAlignment(SwingConstants.TOP);
		studentsB.setText(Local.getString("Students"));
		studentsB.setHorizontalTextPosition(SwingConstants.CENTER);
		studentsB.setFocusPainted(false);
		studentsB.setBorderPainted(false);
		studentsB.setContentAreaFilled(false);
		studentsB.setPreferredSize(new Dimension(50, 50));
		studentsB.setMinimumSize(new Dimension(30, 30));
		studentsB.setOpaque(false);
		studentsB.setMaximumSize(new Dimension(60, 80));
		studentsB.setBackground(Color.white);

		trainersB.setSelected(true);
		trainersB.setFont(new java.awt.Font("Dialog", 1, 10));
		trainersB.setMargin(new Insets(0, 0, 0, 0));
		trainersB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/trainer.png")));
		trainersB.setVerticalTextPosition(SwingConstants.BOTTOM);
		trainersB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trainersB_actionPerformed(e);
			}
		});
		trainersB.setVerticalAlignment(SwingConstants.TOP);
		trainersB.setText(Local.getString("Trainers"));
		trainersB.setHorizontalTextPosition(SwingConstants.CENTER);
		trainersB.setFocusPainted(false);
		trainersB.setBorderPainted(false);
		trainersB.setContentAreaFilled(false);
		trainersB.setPreferredSize(new Dimension(50, 50));
		trainersB.setMinimumSize(new Dimension(30, 30));
		trainersB.setOpaque(false);
		trainersB.setMaximumSize(new Dimension(60, 80));
		trainersB.setBackground(Color.white);
		//end of owner


		this.add(toolBar, BorderLayout.WEST);
		this.add(panel, BorderLayout.CENTER);
		panel.add(dailyItemsPanel, "DAILYITEMS");
		panel.add(filesPanel, "FILES");
		//adds the owner panel to owner
		if(userChosenView == 0){
			panel.add(studentsPanel, "STUDENTS");
			panel.add(trainersPanel, "TRAINERS");
		}

		toolBar.add(agendaB, null);
		
		if(userChosenView == 0 || userChosenView == 1){
			toolBar.add(eventsB, null);
		}
	
		toolBar.add(tasksB, null);
		toolBar.add(notesB, null);
		toolBar.add(filesB, null);
		//add owner to toolbar
		if(userChosenView == 0){
			toolBar.add(studentsB, null);
			toolBar.add(trainersB,null);
		}
		toolBar.add(refreshB, null);
		currentB = agendaB;
		// Default blue color
		currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);

		toolBar.setBorder(null);
		panel.setBorder(null);
		dailyItemsPanel.setBorder(null);
		filesPanel.setBorder(null);

	}

	public void selectPanel(String pan) {
		if (pan != null) {
			if (pan.equals("NOTES"))
				notesB_actionPerformed(null);
			else if (pan.equals("TASKS"))
				tasksB_actionPerformed(null);
			else if (pan.equals("EVENTS"))
				eventsB_actionPerformed(null);
			else if (pan.equals("FILES"))
				filesB_actionPerformed(null);
			else if (pan.equals("STUDENTS"))
				studentsB_actionPerformed(null);
			else if(pan.equals("TRAINERS"))
				trainersB_actionPerformed(null);
		}
	}

	public void agendaB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("AGENDA");
		setCurrentButton(agendaB);
		Context.put("CURRENT_PANEL", "AGENDA");
	}

	public void notesB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("NOTES");
		setCurrentButton(notesB);
		Context.put("CURRENT_PANEL", "NOTES");
	}

	public void tasksB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("TASKS");
		setCurrentButton(tasksB);
		Context.put("CURRENT_PANEL", "TASKS");
	}

	public void eventsB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("EVENTS");
		setCurrentButton(eventsB);
		Context.put("CURRENT_PANEL", "EVENTS");
	}

	//action performed once the Owner was clicked
	public void studentsB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "STUDENTS");
		setCurrentButton(studentsB);
		Context.put("CURRENT_PANEL", "STUDENTS");
	}

	public void trainersB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "TRAINERS");
		setCurrentButton(trainersB);
		Context.put("CURRENT_PANEL", "TRAINERS");
	}

	public void filesB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "FILES");
		setCurrentButton(filesB);
		Context.put("CURRENT_PANEL", "FILES");
	}

	void setCurrentButton(JButton cb) {
		currentB.setBackground(Color.white);
		currentB.setOpaque(false);
		currentB = cb;
		// Default color blue
		currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);
	}

	public void refreshB_actionPerformed(ActionEvent e) {

		invalidate();
		validate();
		repaint();
	}
}