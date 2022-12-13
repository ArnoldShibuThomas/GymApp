/**
 * EventsTable.java
 * Created on 09.03.2003, 9:52:02 Alex
 * Package: net.sf.memoranda.ui
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

package main.java.memoranda.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import main.java.memoranda.Event;
import main.java.memoranda.EventsManager;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.gymAssets.Avaliability;
import main.java.memoranda.util.AvaliabilityTool;
import main.java.memoranda.util.Local;


/**
 * This is the Event table class.
 */
/*$Id: EventsTable.java,v 1.6 2004/10/11 08:48:20 alexeya Exp $*/
public class EventsTable extends JTable {

    public static final int EVENT = 100;
    public static final int EVENT_ID = 101;

    Vector events = new Vector();
    AvaliabilityTool tool;
    //all the people for the day
    ArrayList<Avaliability> people;

    /**
     * Constructor for EventsTable.
     */
    public EventsTable(AvaliabilityTool t) {
        super();
        tool = t;
        setModel(new EventsTableModel());
        initTable(CurrentDate.get());
        this.setShowGrid(false);
        Calendar c = Calendar.getInstance();
        String dayOfTheWeek = tool.getTheDayOfTheWeek(c.get(c.DAY_OF_WEEK));
        System.out.println("[Debug]: It is: " + dayOfTheWeek);

        //all the people for the day
        people = tool.getPeopleForDay(dayOfTheWeek);

        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) {

                //get the date and day!
                System.out.println("[Debug]: Date: " 
                    + d.getMonth() + "/" + d.getDay() + "/" 
                    + d.getYear());
                Calendar c = Calendar.getInstance();
                c.set(d.getYear(),d.getMonth(),d.getDay());
                String dayOfTheWeek = t.getTheDayOfTheWeek(c.get(c.DAY_OF_WEEK));
                System.out.println("[Debug]: It is: " + dayOfTheWeek);

                //updateUI();
                initTable(d);
            }
        });
    }

    public AvaliabilityTool getTool() {
        return tool;
    }

    /**
     * This method constructs a table.
     */
    public void initTable(CalendarDate d) {
        events = (Vector)EventsManager.getEventsForDate(d);

        //get the date and day!
        System.out.println("[Debug]: Date: " + d.getMonth() 
            + "/" + d.getDay() + "/" 
            + d.getYear());
        Calendar c = Calendar.getInstance();
        c.set(d.getYear(),d.getMonth(),d.getDay());
        String dayOfTheWeek = tool.getTheDayOfTheWeek(c.get(c.DAY_OF_WEEK));
        System.out.println("[Debug]: It is: " + dayOfTheWeek);

        //all the people for the day
        people = tool.getPeopleForDay(dayOfTheWeek);

        //events = new Vector<Avaliability>(people);

        getColumnModel().getColumn(0).setPreferredWidth(60);
        getColumnModel().getColumn(0).setMaxWidth(60);
        clearSelection();
        updateUI();
    }

    public void refresh() {
        initTable(CurrentDate.get());
    }

    /**
     * This method creates the table with all the entries.
     */
    public TableCellRenderer getCellRenderer(int row, int column) {
        return new javax.swing.table.DefaultTableCellRenderer() {

            public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
                Component comp;
                comp = super
                .getTableCellRendererComponent(table, value, 
                    isSelected, hasFocus, row, column);
                Avaliability av = (Avaliability)getModel().getValueAt(row, EVENT);
                comp.setForeground(java.awt.Color.gray);
                return comp;
            }
        };

    }

    class EventsTableModel extends AbstractTableModel {

        String[] columnNames = {
            //Local.getString("Task name"),
            Local.getString("Trainer"),
            Local.getString("Start"),
            Local.getString("End")
        };

        EventsTableModel() {
            super();
        }

        public int getColumnCount() {
            return 3;
        }

        public int getRowCount() {
            int i;

            try {
                i = people.size();
            } catch (NullPointerException e) {
                i = 1;
            }

            return i;
        }

        public Object getValueAt(int row, int col) {

            try {
                Avaliability av = (Avaliability)people.get(row);

                if (col == 0) {
                    return av.getTrainer();
                } else if (col == 1) {
                    return av.getStartTime();
                } else if (col == 2) {
                    return av.getEndTime();
                } else { 
                    return av;
                }
            } catch (Exception e) {
                return null;
            }
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }


    }
}
