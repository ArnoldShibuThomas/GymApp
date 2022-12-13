package main.java.memoranda.ui;

//import from other local packages

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

//Students panel class for owner to view and modify students in our system.
public class StudentsPanel extends JPanel {

    //a variable that contains the belts in the system
    private final String[] beltList = { "white", "yellow", "orange", "purple", "blue",
        "blue stripe", "green", "green stripe", "brown1", "brown2", "brown3", "black1",
        "black2", "black3" };

    // create an arraylis of students
    private ArrayList<Student> allStudents;

    //create an object of the JSONTool to do JSON I/O
    private JSONTool performActionToJSON;

    //student selected = null;
    private Student selectedStudet;

    //create a JPanel for the right side fo the view here wher all buttons and components go into
    private JPanel rightSide;

    //labels that have to do with the name panel
    private JLabel nameLabel;
    private JLabel studentNameHere;

    //label that has to do with the belt pane 
    private JLabel beltLabel;
    //combo box
    JComboBox beltComboBox = new JComboBox(beltList);

    //constructor
    public StudentsPanel(JSONTool jsonHere) {
        try {
            //initilize instance variables
            allStudents = new ArrayList<Student>();
            selectedStudet = null;
            rightSide = new JPanel();
            rightSide.setLayout(new GridLayout(3,1));
            nameLabel = new JLabel("Name: ");
            studentNameHere = new JLabel("--");
            beltLabel = new JLabel("Belt: ");
            performActionToJSON = jsonHere;

            //this method will start the UI components
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {

        //this method will read the JSON and update list
        allStudents = performActionToJSON.getAllStudents();

        //set the owner panel's layout to be a grid Pane
        this.setLayout(new GridLayout());
    
        //setup the student list 
        setupStudentListPanel();

        //setup name panel
        setupNamePanel();
        
        //setup belt panel
        setupBeltPanel();

        setupStudentButton();

        //add the right panel to split the view
        this.add(rightSide);
    }

    /**
     * Creates the button to handle students being added from the gui.
     */
    public void setupStudentButton() {

        System.out.println("[DEBUG] Adding add student button");
        //set the size for the button

        JButton addButton = new JButton("Add new student");

        //set size of button
        addButton.setPreferredSize(new Dimension(40, 40));

        //add button to rightside
        rightSide.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButton.setText("See pop up to create new student");

                String newStuName = JOptionPane.showInputDialog("New student's name");
                System.out.println("[DEBUG]: New student name: " + newStuName);

                String newStuPassword = JOptionPane.showInputDialog("New student's password");
                System.out.println("[DEBUG]: New student password: " + newStuPassword);

                if (newStuName != null && newStuPassword != null) {
                    Student newStu = new Student(newStuName, newStuPassword);
                    allStudents.add(newStu);
                    System.out.println("[DEBUG]: New student added: "
                            + allStudents.contains(newStu));
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


    public void setupBeltPanel() {
        //set the size on the beltLabel
        beltLabel.setFont(new Font("Arial",Font.BOLD,50));

        //set size of combobox
        beltComboBox.setPreferredSize(new Dimension(250,50));
        beltComboBox.setPrototypeDisplayValue("100000000000");

        //set comboBox to deafult value
        beltComboBox.setSelectedIndex(0);

        //create a spring layout
        SpringLayout springlayout = new SpringLayout();

        //create the belt panel
        JPanel beltPanel = new JPanel(springlayout);

        //add components to beltPanel
        beltPanel.add(beltLabel);
        beltPanel.add(beltComboBox);

        //setup constraints with 5 as padding and beltLabel to the west of beltComboBox is the
        // east of beltLabel
        springlayout.putConstraint(SpringLayout.WEST, beltComboBox,5, SpringLayout.EAST,
                beltLabel);

        //add the name and belt panel to rightSidePanel
        rightSide.add(beltPanel);

        //a listener on the belt color
        beltComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //get the selected item from the combo box
                String selectedBelt = beltComboBox.getSelectedItem().toString();
                //set the studentHere's belt color
                if (selectedStudet != null) {
                    selectedStudet.setBeltLevel(selectedBelt);
                    performActionToJSON.updateStudentsToJSON(allStudents);
                }
            }
        });
    }

    public void setupNamePanel() {

        //change font of the labels
        nameLabel.setFont(new Font("Arial",Font.BOLD,50));
        studentNameHere.setFont(new Font("Arial",Font.PLAIN,50));

        //create a spring layout
        SpringLayout springlayout = new SpringLayout();

        //create the name panel and set the layout to spring
        JPanel namePanel = new JPanel(springlayout);

        //add components to namePanel
        namePanel.add(nameLabel);
        namePanel.add(studentNameHere);

        //setup constraints with 5 as padding and nameLabel to the west of studentNameHere is
        // the east of nameLabel
        springlayout.putConstraint(SpringLayout.WEST, studentNameHere,5, SpringLayout.EAST,
                nameLabel);

        //add this panel to the rightside panel in Student Panel
        rightSide.add(namePanel);
    }


    //this sets up the Student JList as a scrollable list that has the ability to handle
    // being clicked on
    public void setupStudentListPanel() {

        //create a default list model for the the students Jlist
        DefaultListModel list = new DefaultListModel();

        //create a JList that will contain all the students
        JList students = new JList(list);
    
        //set the model
        students.setModel(list);

        //set the font of the list
        students.setFont(new Font("Arial",Font.PLAIN,25));

        //add people to the list - read from the students.json that are stored in the array
        for (int i = 0; i < allStudents.size(); i++) {
            Student here = allStudents.get(i);
            list.addElement(here.getName());
        }

        //create a scrollPane and put our students list here to allow scrolling
        JScrollPane studentScrollPanel = new JScrollPane(students);

        //add the students scroll ppanel to our parent gridpane here
        this.add(studentScrollPanel);

        //Titled borders
        TitledBorder studentTitle = BorderFactory.createTitledBorder("Students");
        //align it
        studentTitle.setTitleJustification(TitledBorder.CENTER);
        //setup font
        studentTitle.setTitleFont(new Font("Arial",Font.BOLD,30));

        //set title of list
        students.setBorder(studentTitle);

        //this is the listener on the Jlist that will run when something in the list is selected
        students.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                //get index selected by user
                int studentNumber = students.getSelectedIndex();

                //ensure that the there are students
                if(allStudents.size() > 0){

                    //get the student at this part of the jlist and store it in student object
                    selectedStudet = allStudents.get(studentNumber);

                    //set the text of the label to the student objects name
                    studentNameHere.setText(selectedStudet.getName()); 
                    String beltOfStudent = selectedStudet.getBeltLevel();
                    updateComboBox(beltOfStudent);
                }   
            }
        });
    }

    public void updateComboBox(String beltProvided) {
        //set the default index to 0
        int indexOfSelection = 0;

        //fo through list of belts and check which belt the player is 
        for (int i = 0; i < beltList.length; i++) {
            //once belt is found update the indexOfSelection
            if (beltProvided.equals(beltList[i])) {
                indexOfSelection = i;
            }
        }

        //go ahead and set the default value of the selection to the current student's belt color
        beltComboBox.setSelectedIndex(indexOfSelection);
    }

}