package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//import ArrayList
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gymAssets.Avaliability;
import main.java.memoranda.gymAssets.Student;
import main.java.memoranda.util.AvaliabilityTool;
import main.java.memoranda.util.JSONTool;
import main.java.memoranda.util.Local;

/*$Id: EventDialog.java,v 1.28 2005/02/19 10:06:25 rawsushi Exp $*/
public class EventDialog extends JDialog implements WindowListener {    
    public boolean cancelled = false;
    boolean ignoreStartChanged = false;
    boolean ignoreEndChanged = false;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel header = new JLabel();
    JPanel eventPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc;
    JLabel lblTime = new JLabel();
    //create an end time label
    JLabel endTime = new JLabel();
    public JSpinner timeSpin = 
        new JSpinner(new SpinnerDateModel(new Date(), 
        null, null, Calendar.MINUTE));
    //create an end time spin object
    public JSpinner endTimeSpin = 
        new JSpinner(new SpinnerDateModel(new Date(), 
        null, null, Calendar.HOUR_OF_DAY));
    JLabel lblText = new JLabel();
    public JTextField textField = new JTextField();
    String trainerNameSelected;
    //combo boxes
    JComboBox trainers;
    //the reponse
    JLabel hintsToUser = new JLabel("Please fill out form above!");

    JComboBox startTimeCombo;
    JComboBox endTimeCombo;

    TitledBorder repeatBorder;
    JPanel repeatPanel = new JPanel(new GridBagLayout());
    public JRadioButton noRepeatRB = new JRadioButton();

    //create RB for each day of the week
    public JRadioButton mondayRB = new JRadioButton("Monday");
    public JRadioButton tuesdayRB = new JRadioButton("Tuesday");
    public JRadioButton wednesdayRB = new JRadioButton("Wednesday");
    public JRadioButton thursdayRB = new JRadioButton("Thursday");
    public JRadioButton fridayRB = new JRadioButton("Friday");
    public JRadioButton saturdayRB = new JRadioButton("Saturday");
    public JRadioButton sundayRB = new JRadioButton("Sunday");

    public JRadioButton dailyRepeatRB = new JRadioButton();
    public JSpinner daySpin = new JSpinner(new SpinnerNumberModel(1,1,365,1));
    JLabel lblDays = new JLabel();
    JLabel lblSince = new JLabel();
    public JSpinner startDate = new JSpinner(new SpinnerDateModel());
    JButton setStartDateB = new JButton();
    public JRadioButton weeklyRepeatRB = new JRadioButton();
    public JComboBox weekdaysCB = new JComboBox(Local.getWeekdayNames());
    public JCheckBox enableEndDateCB = new JCheckBox();
    public JCheckBox workingDaysOnlyCB = new JCheckBox();
    public JSpinner endDate = new JSpinner(new SpinnerDateModel());
    JButton setEndDateB = new JButton();
    public JRadioButton monthlyRepeatRB = new JRadioButton();
    public JSpinner dayOfMonthSpin = new JSpinner(new SpinnerNumberModel(1,1,31,1));
    JLabel lblDoM = new JLabel();
    public JRadioButton yearlyRepeatRB = new JRadioButton();
    ButtonGroup repeatRBGroup = new ButtonGroup();
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    JPanel hintPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    JButton okB = new JButton();
    JButton cancelB = new JButton();
    CalendarFrame endCalFrame = new CalendarFrame();
    CalendarFrame startCalFrame = new CalendarFrame();
    private Date eventDate;
    private JSONTool reader;
    ArrayList<Student> allTrainers;
    ArrayList<String> daysChosen;
    AvaliabilityTool scheduleTool;
    Avaliability modifiedAvaliability;
    int modify = 0;
    
    /**
     * This is the event dialog overriden constructor.
     */
    public EventDialog(Frame frame, String title, JSONTool j, AvaliabilityTool t) {
        super(frame, title, true);

        modifiedAvaliability = new Avaliability();
        modify = 0;

        allTrainers = new ArrayList<Student>();
        daysChosen = new ArrayList<String>();

        //read from JSON
        reader = j;
        scheduleTool = t;
        //get all the trainer and put them into an array to be put into the jcombo
        if (reader.getAllTrainers() == null) {
            reader.readFromJSON();
            allTrainers = reader.getAllTrainers();
        } else {
            allTrainers = reader.getAllTrainers();
        }

        String[] name = new String[allTrainers.size()];

        for (int i = 0; i < allTrainers.size(); i++) {
            name[i] = allTrainers.get(i).getName();
        }

        //setup the comboboxes
        trainers = new JComboBox(name);
        trainerNameSelected = trainers.getSelectedItem().toString();

        //this is the time combos
        String[] startTimeList = {"00:00","1:00", "2:00", "3:00","4:00", 
            "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", 
            "13:00", "14:00", "15:00", "16:00", "17:00","18:00", "19:00", 
            "20:00","21:00", "22:00", "23:00"};
        String[] endTimeList = {"00:00","1:00", "2:00", "3:00","4:00", 
            "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", 
            "13:00", "14:00", "15:00", "16:00", "17:00","18:00", "19:00", 
            "20:00","21:00", "22:00", "23:00"};

        startTimeCombo = new JComboBox(startTimeList);
        endTimeCombo = new JComboBox(endTimeList);

        if (j.getView() == 1) {

            int index = 1;

            for (int i = 0; i < allTrainers.size(); i++) {

                Student here = allTrainers.get(i);

                if (here.getName().equals(reader.getUser())) {
                    index = i;
                }
            }

            //set the combobox to default to the name they are
            trainers.setSelectedIndex(index);

            //ensure the person cannot edit who's avaliabilit they are editing
            trainers.setEnabled(false);
        }

        //if for any reason the student is 
        //on this page ensure they cannot do anything with the dialog
        if (j.getView() == 2) {
            return;
        }

        try {
            jbInit();
            pack();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
        super.addWindowListener(this);
    }

    void jbInit() throws Exception {
        this.setResizable(false);
        // Build headerPanel
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Availability"));
        header.setIcon(new ImageIcon(main.java.memoranda.ui
            .EventDialog.class.getResource(
            "/ui/icons/event48.png")));
        headerPanel.add(header);
        
        // Build eventPanel
        lblTime.setText(Local.getString("Start Time"));
        lblTime.setMinimumSize(new Dimension(60, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        eventPanel.add(lblTime, gbc);
        startTimeCombo.setPreferredSize(new Dimension(60, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 1; 
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        eventPanel.add(startTimeCombo, gbc);
        

        //do the end time
        endTime.setText(Local.getString("End Time"));
        endTime.setMinimumSize(new Dimension(60, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 2; 
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        eventPanel.add(endTime, gbc);
        endTimeCombo.setPreferredSize(new Dimension(60, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 3; 
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 5, 0);
        gbc.anchor = GridBagConstraints.EAST;
        eventPanel.add(endTimeCombo, gbc);
        

        lblText.setText(Local.getString("Trainer"));
        lblText.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        eventPanel.add(lblText, gbc);

        trainers.setMinimumSize(new Dimension(375, 24));
        trainers.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        eventPanel.add(trainers, gbc);
        
        // Build RepeatPanel
        repeatBorder = new TitledBorder(BorderFactory.createLineBorder(
        Color.gray, 1), Local.getString("Days"));
        repeatPanel.setBorder(repeatBorder);

        //add day radio buttons
        //monday
        mondayRB.setMaximumSize(new Dimension(80, 35));
        mondayRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //this will simulate the behavior of toggling the RB
                String day = "Monday";

                if (mondayRB.isSelected()) {
                    System.out.println("[Debug]: You Selected: " + day);
                } else {
                    System.out.println("[Debug]: You Unselected: " + day);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        repeatPanel.add(mondayRB, gbc);

        //tuesday
        tuesdayRB.setMaximumSize(new Dimension(80, 35));
        tuesdayRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this will simulate the behavior of toggling the RB
                String day = "Tuesday";

                if (tuesdayRB.isSelected()) {
                    System.out.println("[Debug]: You Selected: " + day);
                } else {
                    System.out.println("[Debug]: You Unselected: " + day);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        repeatPanel.add(tuesdayRB, gbc);

        //wednesday
        wednesdayRB.setMaximumSize(new Dimension(80, 35));
        wednesdayRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this will simulate the behavior of toggling the RB
                String day = "Wednesday";

                if (wednesdayRB.isSelected()) {
                    System.out.println("[Debug]: You Selected: " + day);
                } else {
                    System.out.println("[Debug]: You Unselected: " + day);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        repeatPanel.add(wednesdayRB, gbc);

        //thursday
        thursdayRB.setMaximumSize(new Dimension(80, 35));
        thursdayRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this will simulate the behavior of toggling the RB
                String day = "Thursday";

                if (thursdayRB.isSelected()) {
                    System.out.println("[Debug]: You Selected: " + day);
                } else {
                    System.out.println("[Debug]: You Unselected: " + day);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        repeatPanel.add(thursdayRB, gbc);

        //friday
        fridayRB.setMaximumSize(new Dimension(80, 35));
        fridayRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this will simulate the behavior of toggling the RB
                String day = "Friday";

                if (fridayRB.isSelected()) {
                    System.out.println("[Debug]: You Selected: " + day);
                } else {
                    System.out.println("[Debug]: You Unselected: " + day);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        repeatPanel.add(fridayRB, gbc);

        //saturday
        saturdayRB.setMaximumSize(new Dimension(80, 35));
        saturdayRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this will simulate the behavior of toggling the RB
                String day = "Saturday";

                if (saturdayRB.isSelected()) {
                    System.out.println("[Debug]: You Selected: " + day);
                } else {
                    System.out.println("[Debug]: You Unselected: " + day);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        repeatPanel.add(saturdayRB, gbc);

        //sunday
        sundayRB.setMaximumSize(new Dimension(80, 35));
        sundayRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this will simulate the behavior of toggling the RB
                String day = "Sunday";

                //if the button is selected
                if (sundayRB.isSelected()) {
                    System.out.println("[Debug]: You Selected: " + day);
                } else { 
                    //if the button is unselected
                    System.out.println("[Debug]: You Unselected: " + day);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        repeatPanel.add(sundayRB, gbc);
    
        
        // Build ButtonsPanel
        okB.setMaximumSize(new Dimension(100, 26));
        okB.setMinimumSize(new Dimension(100, 26));
        okB.setPreferredSize(new Dimension(100, 26));
        okB.setText(Local.getString("Ok"));
        okB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okB_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(okB);
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
        cancelB.setText(Local.getString("Cancel"));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setMaximumSize(new Dimension(100, 26));
        buttonsPanel.add(okB);
        buttonsPanel.add(cancelB);
        hintPanel.add(hintsToUser);
        
        // Finally build the Dialog
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(eventPanel, BorderLayout.SOUTH);
        bottomPanel.add(repeatPanel, BorderLayout.NORTH);
        bottomPanel.add(hintsToUser, BorderLayout.CENTER);
        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        
    }

    void editDialogSetup(Avaliability a) {
        modify = 1;
        modifiedAvaliability = a;
        String name = a.getTrainer();
        System.out.println("[Debug]:You want to edit " + name);
        int t = 0;
        for (int i = 0; i < allTrainers.size(); i++) {
            Student here = allTrainers.get(i);

            if (here.getName().equals(name)) {
                t = i;
            }
        }
        System.out.println("[Debug]: We are at: " + t + " and size is: " + allTrainers.size());
        trainers.setSelectedIndex(t);

        //times
        ArrayList<String> startTimeList = new ArrayList<>();
        startTimeList.add("00:00");
        startTimeList.add("1:00");
        startTimeList.add("2:00");
        startTimeList.add("3:00");
        startTimeList.add("4:00");
        startTimeList.add("5:00");
        startTimeList.add("6:00");
        startTimeList.add("7:00");
        startTimeList.add("8:00");
        startTimeList.add("9:00");
        startTimeList.add("10:00");
        startTimeList.add("11:00");
        startTimeList.add("12:00");
        startTimeList.add("13:00");
        startTimeList.add("14:00");
        startTimeList.add("15:00");
        startTimeList.add("16:00");
        startTimeList.add("17:00");
        startTimeList.add("18:00");
        startTimeList.add("19:00");
        startTimeList.add("20:00");
        startTimeList.add("21:00");
        startTimeList.add("22:00");
        startTimeList.add("23:00");

        //ensure the times are populated
        t = startTimeList.indexOf(a.getStartTime());
        startTimeCombo.setSelectedIndex(t);

        t = startTimeList.indexOf(a.getEndTime());
        endTimeCombo.setSelectedIndex(t);

        //ensure right buttons are selected
        ArrayList<String> daysWorking = a.getDays();

        if (daysWorking.contains("Monday")) {
            mondayRB.setSelected(true);
        }

        if (daysWorking.contains("Tuesday")) {
            tuesdayRB.setSelected(true);
        }

        if (daysWorking.contains("Wednesday")) {
            wednesdayRB.setSelected(true);
        }

        if (daysWorking.contains("Thursday")) {
            thursdayRB.setSelected(true);
        }

        if (daysWorking.contains("Friday")) {
            fridayRB.setSelected(true);
        }

        if (daysWorking.contains("Saturday")) {
            saturdayRB.setSelected(true);
        }

        if (daysWorking.contains("Sunday")) {
            sundayRB.setSelected(true);
        }

        scheduleTool.removeAvaliability(a);

        //ensure the person cannot edit the person's name
        trainers.setEnabled(false);

    }

    /**
     * This method will see which days are selected.
     */
    public void doubleCheckDays() {
        //create a new arraylist
        daysChosen = new ArrayList<String>();

        //check to see what buttons are selected
        if (mondayRB.isSelected()) {
            daysChosen.add("Monday");
        }

        if (tuesdayRB.isSelected()) {
            daysChosen.add("Tuesday");
        }

        if (wednesdayRB.isSelected()) {
            daysChosen.add("Wednesday");
        }

        if (thursdayRB.isSelected()) {
            daysChosen.add("Thursday");
        }

        if (fridayRB.isSelected()) {
            daysChosen.add("Friday");
        }

        if (saturdayRB.isSelected()) {
            daysChosen.add("Saturday");
        }

        if (sundayRB.isSelected()) {
            daysChosen.add("Sunday");
        }

        System.out.println("[Debug]: Days selected include " + daysChosen.toString());

    }

    void okB_actionPerformed(ActionEvent e) {
        System.out.println("[Debug]: Ok button pressed");
        //check which days the user selected
        doubleCheckDays();

        //lets attempt to add them to the scedule
        boolean attempt = scheduleTool.addNewAvalaibility(
            trainers.getSelectedItem().toString(),
            startTimeCombo.getSelectedItem().toString(),
            endTimeCombo.getSelectedItem().toString(),
            daysChosen,
            hintsToUser);

        //if the system was able to add them to 
        //the schedule then close the windows and 
        // display message
        if (attempt == true) {
            hintsToUser.setForeground(Color.GREEN);
            this.dispose();
        } else {
            //otherwise if the attempt was a failure let the user know why by message
            hintsToUser.setForeground(Color.RED);
        }
    }

    void cancelB_actionPerformed(ActionEvent e) {

        if (modify == 1) {
            System.out.println("[Debug]: It looks like you" 
                + " tried to modify Availability and cancelled!");
            scheduleTool.addBack(modifiedAvaliability);
        }

        cancelled = true;
        this.dispose();
    }

    public void windowOpened(WindowEvent e) {}

    public void windowClosing(WindowEvent e) {
        cancelled = true;
        this.dispose();
    }
    
    public void setEventDate(Date d) {
        eventDate = d;
    }
    
    public Date getEventDate() {
        return eventDate;
    }
    
    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}

}