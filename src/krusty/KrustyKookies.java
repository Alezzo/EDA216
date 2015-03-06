package krusty;

import krusty.gui.KrustyGUI;

import javax.swing.*;

/**
 * KrustyKookies is the main class for the movie ticket booking
 * application. It creates a database object and the GUI to
 * interface to the database.
 */
public class KrustyKookies {

    public static void main(String[] args) {
        Database db = new Database();

	    JFrame jFrame = new JFrame("Loading Krusty Kookie");

	    if (db.openConnection("db129", "kmg335rz")) {
		    //productionPane.displayMessage("Connected to database");
	    } else {
		    //productionPane.displayMessage("Could not connect to database");
	    }

        new KrustyGUI(db);
    }
}
