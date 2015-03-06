package krusty.gui;

import krusty.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductionPane extends BasicPane {

	private JTextField[] fields;

	private static final int USER_ID = 0;
	private static final int NBR_FIELDS = 1;

	public ProductionPane(Database db) { super(db); }

	public JComponent createTopPanel() {
		String[] texts = new String[NBR_FIELDS];
		texts[USER_ID] = "User id";
		fields = new JTextField[NBR_FIELDS];
		fields[USER_ID] = new JTextField(20);
		return new InputPanel(texts, fields);
	}

	public JComponent createBottomPanel() {
		JButton[] buttons = new JButton[1];
		buttons[0] = new JButton("Login");
		ActionHandler actHand = new ActionHandler();
		fields[USER_ID].addActionListener(actHand);
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
			String userId = fields[USER_ID].getText();


		}
	}


}
