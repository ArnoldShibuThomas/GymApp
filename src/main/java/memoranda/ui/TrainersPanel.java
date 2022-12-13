package main.java.memoranda.ui;

//import from other local packages

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//import ArrayList
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import main.java.memoranda.gymAssets.Belt;
import main.java.memoranda.gymAssets.Instructor;
import main.java.memoranda.gymAssets.Student;
import main.java.memoranda.util.JSONTool;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//import JSON
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


//Trainers panel class for owner to view and modify trainers in our system.
public class TrainersPanel extends JPanel {

    //a variable that contains the belts in the system
    private final String[] beltList = { "white", "yellow", "orange", "purple", "blue",
       "blue stripe", "green", "green stripe", "brown1", "brown2", "brown3", "black1",
       "black2", "black3" };

    // create an arraylis of trainers
    private ArrayList<Student> allTrainers;

    //create an object of the JSONTool to do JSON I/O
    private JSONTool performActionToJSON;

    //student selected = null;
    private Instructor selectedTrainer;

    //create a JPanel for the right side fo the view here wher all buttons and components go into
    private JPanel rightSide;

    //labels that have to do with the name panel
    private JLabel nameLabel;
    private JLabel trainerNameHere;

    //label that has to do with the belt pane 
    private JLabel beltLabel;
    private JLabel beltHere;
    private JLabel rankLabel;

    //making the list global
    private JList trainers;

    //combo boxes
    JComboBox rankComboBox = new JComboBox(beltList);

    /**
    * This is the paramateriz constructor for TrainerPanel.
    */
    public TrainersPanel(JSONTool jsonHere) {
       try {
            //initilize instance variables
            allTrainers = new ArrayList<Student>();
            selectedTrainer = null;
            rightSide = new JPanel();
            rightSide.setLayout(new GridLayout(4,1));
            nameLabel = new JLabel("Name: ");
            trainerNameHere = new JLabel("--");
            beltLabel = new JLabel("Belt: ");
            beltHere = new JLabel("--");
            rankLabel = new JLabel("Training Rank: ");
            performActionToJSON = jsonHere;

            //this method will start the UI components
            jbInit();
       } catch (Exception ex) {
            new ExceptionDialog(ex);
       }
    }

    /**
    * This method is creates the swing panel that the user sees
    */
    void jbInit() throws Exception {

        //this method will read the JSON and update list
        allTrainers = performActionToJSON.getAllTrainers();

        //set the owner panel's layout to be a grid Pane
        this.setLayout(new GridLayout());
    
        //setup the student list 
        setupTrainerListPanel();

        //setup name panel
        setupNamePanel();

        //setup belt panel
        setupBeltPanel();
        
        //setup rank panel
        setupRankPanel();

        //Create trainer button
        setupTrainerButton();

        //add the right panel to split the view
        this.add(rightSide);
    }

    /**
    * This method sets up the belt panel for trainers
    */
    public void setupBeltPanel() {
        //change font of the labels
        beltLabel.setFont(new Font("Arial",Font.BOLD,50));
        beltHere.setFont(new Font("Arial",Font.PLAIN,50));

        //create a spring layout
        SpringLayout springlayout = new SpringLayout();

        //create the name panel and set the layout to spring
        JPanel beltPanel = new JPanel(springlayout);

        //add components to namePanel
        beltPanel.add(beltLabel);
        beltPanel.add(beltHere);

        //setup constraints with 5 as padding and nameLabel to the west of trainerNameHere is the
        // east of nameLabel
        springlayout.putConstraint(SpringLayout.WEST, beltHere,5, SpringLayout.EAST, beltLabel);

        //add this panel to the rightside panel in trainer Panel
        rightSide.add(beltPanel);
    }

    /**
    * This method is used to setup the rank panel
    */
    public void setupRankPanel(){
        //set the size on the beltLabel
        rankLabel.setFont(new Font("Arial",Font.BOLD,50));

        //set size of combobox
        rankComboBox.setPreferredSize(new Dimension(250,50));
        rankComboBox.setPrototypeDisplayValue("100000000000");

        //set comboBox to deafult value
        rankComboBox.setSelectedIndex(0);

        //create a spring layout
        SpringLayout springlayout = new SpringLayout();

        //create the belt panel
        JPanel rankPanel = new JPanel(springlayout);

        //add components to beltPanel
        rankPanel.add(rankLabel);
        rankPanel.add(rankComboBox);

        //setup constraints with 5 as padding and beltLabel to the west of beltComboBox is the
        // east of beltLabel
        springlayout.putConstraint(SpringLayout.WEST, rankComboBox,5, SpringLayout.EAST,
                rankLabel);

        //add the name and belt panel to rightSidePanel
        rightSide.add(rankPanel);

        //a listener on the belt color
        rankComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                //get the selected item from the combo box
                String selectedRank = rankComboBox.getSelectedItem().toString();
                //set the studentHere's belt color
                if(selectedTrainer != null){
                    selectedTrainer.setTrainingRank(selectedRank);
                    performActionToJSON.updateTrainersInJSON(allTrainers);
                }
             }
        });
    }

    /**
    * This method is used to create the name panel
    */
    public void setupNamePanel() {

        //change font of the labels
        nameLabel.setFont(new Font("Arial",Font.BOLD,50));
        trainerNameHere.setFont(new Font("Arial",Font.PLAIN,50));

        //create a spring layout
        SpringLayout springlayout = new SpringLayout();

        //create the name panel and set the layout to spring
        JPanel namePanel = new JPanel(springlayout);

        //add components to namePanel
        namePanel.add(nameLabel);
        namePanel.add(trainerNameHere);

        //setup constraints with 5 as padding and nameLabel to the west of trainerNameHere
        // is the east of nameLabel
        springlayout.putConstraint(SpringLayout.WEST, trainerNameHere,5, SpringLayout.EAST,
                nameLabel);

        //add this panel to the rightside panel in trainer Panel
        rightSide.add(namePanel);
    }

    /**
     * This method initializes the create new trainer button.
     *
     */
    public void setupTrainerButton() {

        System.out.println("[DEBUG] Adding add trainer button");
        //set the size for the button

        JButton addButton = new JButton("Add new trainer");

        //set size of button
        addButton.setPreferredSize(new Dimension(40, 40));

        //add button to rightside
        rightSide.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButton.setText("See pop up to create new trainer");

                String newTrName = JOptionPane.showInputDialog("New trainer's name");
                System.out.println("[DEBUG]: New trainer name: " + newTrName);

                String newTrPassword = JOptionPane.showInputDialog("New trainer's password");
                System.out.println("[DEBUG]: New trainer password: " + newTrPassword);

                String newTrRank = JOptionPane.showInputDialog("New trainer's rank");
                System.out.println("[DEBUG]: New trainer rank: " + newTrRank);

                String newTrBelt = JOptionPane.showInputDialog("New trainer's belt level");
                System.out.println("[DEBUG]: New trainer belt level: " + newTrPassword);

                if (newTrBelt != null && newTrRank != null && newTrName != null
                        && newTrPassword != null) {
                    Instructor newTr = new Instructor(newTrName, newTrPassword, newTrBelt,
                            newTrRank, false);
                    allTrainers.add(newTr);
                    System.out.println("[DEBUG]: New trainer added: "
                            + allTrainers.contains(newTr));
                    rightSide.remove(addButton);
                    rightSide.repaint();
                    forceRefresh();
                } else {
                    System.out.println("[DEBUG]: Input cannot be null");
                }

            }
        });
    }

    /**
     * Forces a reinit of the panel.
     */
    public void forceRefresh() {
        try {
            this.removeAll();
            jbInit();
        } catch (Exception e) {
            System.out.println("[DEBUG]: Error refreshing this panel");
        }
    }


    /**
    * This method sets up the Student JList as a scrollable list that has the ability to handle
     * being clicked on.
    */
    public void setupTrainerListPanel() {

        //create a default list model for the the students Jlist
        DefaultListModel list = new DefaultListModel();

        //create a JList that will contain all the students
        trainers = new JList(list);
    
        //set the model
        trainers.setModel(list);

        //set the font of the list
        trainers.setFont(new Font("Arial",Font.PLAIN,25));

        //add people to the list - read from the students.json that are stored in the array
        for (int i = 0; i < allTrainers.size(); i++) {
            Instructor here = (Instructor) allTrainers.get(i);
            list.addElement(here.getName());
        }

        //create a scrollPane and put our students list here to allow scrolling
        JScrollPane trainerScrollPanel = new JScrollPane(trainers);

        //add the students scroll ppanel to our parent gridpane here
        this.add(trainerScrollPanel);

        //Titled borders
        TitledBorder trainerTitle = BorderFactory.createTitledBorder("Trainers");
        //align it
        trainerTitle.setTitleJustification(TitledBorder.CENTER);
        //setup font
        trainerTitle.setTitleFont(new Font("Arial",Font.BOLD,30));

        //set title of list
        trainers.setBorder(trainerTitle);

        //this is the listener on the Jlist that will run when something in the list is selected
        trainers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                //get index selected by user
                int trainerNumber = trainers.getSelectedIndex();

                //ensure that the there are students
                if(allTrainers.size() > 0){
                    //get the student at this part of the jlist and store it in student object
                    selectedTrainer = (Instructor) allTrainers.get(trainerNumber);

                    //trigger a read to ensure we see the right info
                    allTrainers = performActionToJSON.getAllTrainers();

                    //set the text of the label to the student objects name
                    trainerNameHere.setText(selectedTrainer.getName());
                    //set the label to be the trainer's belt level 
                    beltHere.setText(selectedTrainer.getBeltLevel());
                    //set the rank of the trainer to be the slected rank
                    String rankOfTrainer = selectedTrainer.getTrainingRank();
                    //update comboBox
                    updateComboBox(rankOfTrainer);
                }   
            }
        });
    }

    /**
    * This method updated the comboBox on in the trainerRankPanel.
    */
    public void updateComboBox(String rankProvided) {
        //set the default index to 0
        int indexOfSelection = 0;

        //fo through list of belts and check which belt the player is 
        for (int i = 0; i < beltList.length; i++) {
            //once belt is found update the indexOfSelection
            if (rankProvided.equals(beltList[i])) {
                indexOfSelection = i;
            }
        }

        //go ahead and set the default value of the selection to the current student's belt color
        rankComboBox.setSelectedIndex(indexOfSelection);
    }

}