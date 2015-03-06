package krusty.gui;

import krusty.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductionPane extends BasicPane {

	private JTextField[] fields;

	private static final int COOKIE_NAME = 0;

	public ProductionPane(Database db) { super(db); }

	public JComponent createTopPanel() {
		InputPanel inputPanel = new InputPanel();

		String[] cookieNames = db.getCookieNames();
		JComboBox cookieList = new JComboBox<>(cookieNames);


		inputPanel.addComboBox("Cookie name", cookieList);

		return inputPanel;

		//fields = new JTextField[NBR_FIELDS];
		//fields[COOKIE_NAME] = new JTextField(20);
		//return new InputPanel(texts, fields);
	}

	public JComponent createBottomPanel() {
		JButton[] buttons = new JButton[1];

		buttons[0] = new JButton("Create pallet");

		ActionHandler actHand = new ActionHandler();
		//fields[COOKIE_NAME].addActionListener(actHand);
		return new ButtonAndMessagePanel(buttons, messageLabel, actHand);
	}

	/**
	 * Perform the entry actions of this pane, i.e. clear the message line.
	 */
	public void entryActions() {
		clearMessage();
	}

	/**
	 * A class which listens for button clicks.
	 */
	class ActionHandler implements ActionListener {
		/**
		 * Called when the user clicks the login button. Checks with the
		 * database if the user exists, and if so notifies the CurrentUser
		 * object.
		 *
		 * @param e
		 *            The event object (not used).
		 */
		public void actionPerformed(ActionEvent e) {
			String userId = fields[COOKIE_NAME].getText();


		}
	}


}
