import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatWindow extends JFrame implements ActionListener {
    private static final String TIME_FORMAT = "hh:mm:ss";
    private static final String LINE_END = "\n";

    private static final String TALKING_STRING = "Talking with ";
    private static final String WRITE_MESSAGE = "Write your message: ";
    private static final String EMPTY_MESSAGE = "Can't send an empty message!";

    private static final String CLEAR = "clear";
    private static final String SEND = "send";
    private static final String FILE = "file...";

    private String hisLogin;
    private String message;

    private JTextArea historyArea, messageArea;
    private JFileChooser chooser = null;

    public ChatWindow(String hisLogin) {
	super(TALKING_STRING + hisLogin);

	this.hisLogin = hisLogin;

	setResizable(false);
	chooser = new JFileChooser();

	JPanel contentPane = new JPanel(new GridBagLayout());
	setContentPane(contentPane);
	contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridwidth = GridBagConstraints.REMAINDER;

	historyArea = new JTextArea(15, 40);
	historyArea.setLineWrap(true);
	historyArea.setWrapStyleWord(true);
	historyArea.setEditable(false);

	JScrollPane scrollPane = new JScrollPane(historyArea);
	add(scrollPane, c);

	c.ipadx = 10;
	c.ipady = 10;
	JLabel label1 = new JLabel(WRITE_MESSAGE);
	add(label1, c);

	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridwidth = GridBagConstraints.REMAINDER;
	messageArea = new JTextArea(5, 40);
	messageArea.setLineWrap(true);
	messageArea.setWrapStyleWord(true);

	JScrollPane scrollPane2 = new JScrollPane(messageArea);
	add(scrollPane2, c);

	JPanel panel = new JPanel(new FlowLayout());
	panel.add(createButton(FILE));
	panel.add(createButton(CLEAR));
	panel.add(createButton(SEND));

	c.weighty = 1.0;
	c.ipadx = 100;
	c.ipady = 10;
	c.insets = new Insets(10, 0, 10, 0);
	add(panel, c);

    }

    private JButton createButton(String buttonText) {
	JButton button = new JButton(buttonText);
	button.setActionCommand(buttonText);
	button.addActionListener(this);
	return button;
    }

    private void addHistoryString(String message, boolean received) {

	String historyString = now() + " " + MainPanel.login + ": " + LINE_END
		+ message + LINE_END;

	historyArea.append(historyString);
	historyArea.setCaretPosition(historyArea.getDocument().getLength());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	String actionCommand = e.getActionCommand();

	if (actionCommand.equals(CLEAR)) {
	    messageArea.setText("");
	} else if (actionCommand.equals(SEND)) {
	    message = messageArea.getText();

	    if (message == null || message.equals("")) {
		System.out.println(EMPTY_MESSAGE);
		return;
	    }

	    // Tutaj wysylamy na serwer!

	    addHistoryString(message, false);
	    messageArea.setText("");
	    messageArea.requestFocus();

	    XML.createMessage(hisLogin, message);
	} else if (actionCommand.equals(FILE)) {
	    pickFile();
	}

    }

    public void pickFile() {
	int option = chooser.showOpenDialog(this);

	if (option == JFileChooser.APPROVE_OPTION) {
	    System.out.println("You chose "
		    + ((chooser.getSelectedFile() != null) ? chooser
			    .getSelectedFile().getName() : "nothing"));
	} else {
	    System.out.println("You canceled.");
	}

    }

    public static String now() {
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
	return sdf.format(cal.getTime());
    }
}
