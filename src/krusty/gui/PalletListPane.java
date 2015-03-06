package krusty.gui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import krusty.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PalletListPane extends BasicPane {

	private JTextField[] fields;

	private static final int COOKIE_NAME = 0;

	public PalletListPane(Database db) { super(db); }

	public JComponent createTopPanel() {

		return new SearchPanel(db);

	}


}
