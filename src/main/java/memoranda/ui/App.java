package main.java.memoranda.ui;

//import java awt Libraries

import main.java.memoranda.EventsScheduler;
import main.java.memoranda.util.Configuration;
import main.java.memoranda.util.LoginCredentials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import static java.lang.Double.parseDouble;

/**
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/*$Id: App.java,v 1.28 2007/03/20 06:21:46 alexeya Exp $*/
public class App {
    // boolean packFrame = false;

    static AppFrame frame = null;

    // create a option variables to check if the user selected a view
    static boolean option = false;

    //create a variable to pass to the app frame, so it knows which buttons to show
    static String chosenView = "";
    String personWhoIsUsing = "";
    int guiToUse = 2;
    
    public static final String GUIDE_URL = "http://memoranda.sourceforge.net/guide.html";
    public static final String BUGS_TRACKER_URL = "http://sourceforge.net/tracker/?group_id=90997&atid=595566";
    public static final String WEBSITE_URL = "http://memoranda.sourceforge.net";

    private JFrame splash = null;
    private JFrame loginScreen = null;

    /*========================================================================*/ 
    /* Note: Please DO NOT edit the version/build info manually!
       The actual values are substituted by the Ant build script using 
       'version' property and datestamp.*/

    public static final String VERSION_INFO = "@VERSION@";
    public static final String BUILD_INFO = "@BUILD@";
    
    /*========================================================================*/

    private static final String BUILD_PATH = "build.number";
    private static final String VERSION_PATH = "";

    public static AppFrame getFrame() {
        return frame;
    }
    
    /**
     * This method will show gui components.
     */
    public void show() {
        if (frame.isVisible()) {
            frame.toFront();
            frame.requestFocus();
        } else {
            init();
        }
    }

    /**
     * This is the method that sets up login gui.
     */
    public void login() {
        
        // instantiate the frame
        loginScreen = new JFrame();

        //create a field for the user to type in a user name 
        JTextField userNameEntry = new JTextField();
        userNameEntry.setSize(200,500);

        //create a password field
        JPasswordField passwordEntry = new JPasswordField();
        passwordEntry.setSize(200,500);

        //create a username and password JLabel
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        JLabel userNameLabel = new JLabel("Name: ");
        userNameLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        //this is the sucess or failure message
        JLabel sucessLabel = new JLabel("Please give me a name and password!");

        //create a credentails checker
        LoginCredentials loginCheck = new LoginCredentials();

        //create a login button
        JButton loginButton = new JButton("Login");
        //create an action listener on this button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //store the username and password in a variable
                String password = new String(passwordEntry.getPassword());
                String userName = userNameEntry.getText();

                //go ahead and update the sucess Label as need!
                if (userName.length() == 0) {
                    //say this if no userName is provided
                    sucessLabel.setText("Try Again: Provide me with a name!");  
                    sucessLabel.setForeground(Color.RED);
                } else if (password.length() == 0) {
                    //say this if no password was provided
                    sucessLabel.setText("Try Again: Provide me with a password!");
                    sucessLabel.setForeground(Color.RED);
                } else {
                    boolean isOwner = loginCheck.checkOwner(userName,password);
                    //check if the person is a trainer
                    boolean isTrainer = 
                        loginCheck.credentialsMatch(1,userName,password);

                    //Check if the person is a student
                    boolean isStudent = 
                        loginCheck.credentialsMatch(2,userName,password);

                    //ensure the username matches with the name
                    if (isOwner == true) {
                        sucessLabel.setText("Sucess: Welcome owner " + "!");   
                        sucessLabel.setForeground(Color.GREEN);
                        //then have the program open the GUI
                        chosenView = userName + password;
                        personWhoIsUsing = userName;
                        guiToUse = 0;
                        System.out.println("Owner view displaying");
                        option = true;
                    } else if (isTrainer == true) {
                        //ensure the sucessMessage changes
                        sucessLabel.setText("Sucess: Welcome trainer " + userName + "!");    
                        sucessLabel.setForeground(Color.GREEN);
                        chosenView = userName + password;
                        personWhoIsUsing = userName;
                        guiToUse = 1;
                        System.out.println("Trainer view displaying");
                        option = true;
                    } else if (isStudent == true) {
                        //ensure the sucessMessage changes
                        sucessLabel.setText("Sucess: Welcome student " + userName + "!");    
                        sucessLabel.setForeground(Color.GREEN);
                        chosenView = userName + password;
                        personWhoIsUsing = userName;
                        guiToUse = 2;
                        System.out.println("Student view displaying");
                        option = true;
                    } else {
                        //say this if the credentials they gave us don't match with anyone
                        sucessLabel.setText("Try Again: Invalid name and password!");   
                        sucessLabel.setForeground(Color.RED);
                        userNameEntry.setText("");
                        passwordEntry.setText("");
                    }
                }

            }
        });

        //create the panel to the panel that contains the different componenet and panels
        JPanel mainPanel = new JPanel();
        //set the layout of this to a BorderLayout
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        mainPanel.setLayout(new BorderLayout(100,30));

        //create a panel to exlcusively hold user name and password related componenets
        JPanel fields = new JPanel(new GridLayout(2,2,5,5));

        //populate the fields panel
        fields.add(userNameLabel);
        fields.add(userNameEntry);
        fields.add(passwordLabel);
        fields.add(passwordEntry);

        //add fields componenets to main pane
        mainPanel.add(fields, BorderLayout.CENTER);
        mainPanel.add(loginButton,BorderLayout.SOUTH);
        mainPanel.add(sucessLabel, BorderLayout.NORTH);

        loginScreen.add(mainPanel,BorderLayout.CENTER);
        //set size of panel
        loginScreen.setSize(400, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //set the screen to the center
        loginScreen.setLocation(
            (screenSize.width - 400) / 2,
            (screenSize.height - 300) / 2);
        //this is the default close behavior
        loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set the title of the windows
        loginScreen.setTitle("Login");
        loginScreen.pack();
        //set the visibility to true so the screen can be seen
        loginScreen.setVisible(true);

        //This is a busy wait tht waits until the user has decided upon view
        while (option == false) {
            try {
                //this make the thread wait
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught!");
            }
        }

        OutputStream os = null;
        Writer wr = null;

        //write the user view to a file
        try {
            os = new FileOutputStream("src/main/resources/gymfiles/view.txt");
            wr = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            wr.write(chosenView);
        } catch (IOException e) {
            System.out.println("[Error]: It looks like default view will be used!");
        } finally {

            if (wr != null) {
                
                try {
                    wr.close();
                } catch (IOException e) {
                    System.out.println("Unable to close resource");
                }
                try {
                    os.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //when the user gives an option the go ahead and dispose of this screen
        loginScreen.dispose();

    }

    /**
     * This methods  displays the splash screen and login.
     */
    public App(boolean fullmode) {
        super();
        //ask the user to login or in this case to choose thier view
        login();

        if (fullmode) {
            fullmode = !Configuration.get("START_MINIMIZED").equals("yes");
        }

        /* DEBUG */
        if (!fullmode) {
            System.out.println("Minimized mode");
        }

        if (!Configuration.get("SHOW_SPLASH").equals("no")) {
            showSplash();
        }

        try {
            System.out.println(BUILD_INFO);
            File build = new File(BUILD_PATH);
            String buildInfo = build.toString();
            System.out.println(buildInfo.substring(buildInfo.indexOf("=")));
            System.out.println(VERSION_INFO);
        } catch (Exception e) {
            System.out.println("Unable to read build info.");
        }

        System.out.println(Configuration.get("LOOK_AND_FEEL"));
        try {
            if (Configuration.get("LOOK_AND_FEEL").equals("system")) {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } else if (Configuration.get("LOOK_AND_FEEL").equals("default")) {
                UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());                  
            } else if (
                Configuration.get("LOOK_AND_FEEL").toString().length() > 0) {
                UIManager.setLookAndFeel(
                    Configuration.get("LOOK_AND_FEEL").toString());
            }

        } catch (Exception e) {         
            new ExceptionDialog(e, "Error when initializing a pluggable look-and-feel." 
                + "Default LF will be used.", "Make sure that specified look-and-feel" 
                + "library classes are on the CLASSPATH.");
        }
        if (Configuration.get("FIRST_DAY_OF_WEEK").equals("")) {
            String fdow;
            if (Calendar.getInstance().getFirstDayOfWeek() == 2) {
                fdow = "mon";
            } else {
                fdow = "sun";
            }
            Configuration.put("FIRST_DAY_OF_WEEK", fdow);
            Configuration.saveConfig();
            /* DEBUG */
            System.out.println("[DEBUG] first day of week is set to " + fdow);
        }

        EventsScheduler.init();
        frame = new AppFrame(personWhoIsUsing,guiToUse);
        if (fullmode) {
            init();
        }
        if (!Configuration.get("SHOW_SPLASH").equals("no")) {
            splash.dispose();
        }
    }

    void init() {
        /*
         * if (packFrame) { frame.pack(); } else { frame.validate(); }
         * 
         * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         * 
         * Dimension frameSize = frame.getSize(); if (frameSize.height >
         * screenSize.height) { frameSize.height = screenSize.height; } if
         * (frameSize.width > screenSize.width) { frameSize.width =
         * screenSize.width; }
         * 
         * 
         * Make the window fullscreen - On Request of users This seems not to
         * work on sun's version 1.4.1_01 Works great with 1.4.2 !!! So update
         * your J2RE or J2SDK.
         */
        /* Used to maximize the screen if the JVM Version if 1.4 or higher */
        /* --------------------------------------------------------------- */
        double jvmVersion =
                parseDouble(System.getProperty("java.version").substring(0, 3));

        frame.pack();
        if (jvmVersion >= 1.4) {
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        } else {
            frame.setExtendedState(Frame.NORMAL);
        }
        /* --------------------------------------------------------------- */
        /* Added By Jeremy Whitlock (jcscoobyrs) 07-Nov-2003 at 15:54:24 */

        // Not needed ???
        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();

    }

    /**
    * This methods closes the splash screenwindow.
    */
    public static void closeWindow() {
        if (frame == null) {
            return;
        }
        frame.dispose();
    }

    /**
     * Method showSplash.
     */
    private void showSplash() {
        splash = new JFrame();
        //this is where the splash screen is located in the code and the file location
        ImageIcon spl =
            new ImageIcon(App.class.getResource("/ui/splash.png"));
        JLabel l = new JLabel();
        l.setSize(400, 300);
        l.setIcon(spl);
        splash.getContentPane().add(l);
        splash.setSize(400, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        splash.setLocation(
            (screenSize.width - 400) / 2,
            (screenSize.height - 300) / 2);
        splash.setUndecorated(true);
        splash.setVisible(true);
    }
}