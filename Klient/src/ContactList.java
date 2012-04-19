import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ContactList extends JFrame implements ActionListener,
	ListSelectionListener {
    private static final String CONTACT_LSIT_TITLE = "Contact List";
    private static final String LOGGED_AS_STRING = "Logged as: ";
    private static final String LOGOUT = "Logout";
    private static final String TALK = "Talk";

    private ConnectionManager connectionManager;
    private DefaultListModel<String> listModel;
    private JList list;

    private ReceivingThread thread;

    public ContactList() {
	super(CONTACT_LSIT_TITLE);

	setResizable(false);
	connectionManager = ConnectionManager.getInstance().setServer(
		MainPanel.hostAddress);
	connectionManager.openConnection();
	connectionManager.send(XML.createHelloMessage());

	thread = new ReceivingThread();
	// thread.run();

	JPanel contentPane = new JPanel(new GridBagLayout());
	setContentPane(contentPane);
	contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.HORIZONTAL;

	JLabel jLabel1 = new JLabel(LOGGED_AS_STRING, JLabel.TRAILING);
	add(jLabel1, c);

	JLabel loginLabel = new JLabel(MainPanel.login);
	jLabel1.setLabelFor(loginLabel);
	c.gridwidth = GridBagConstraints.REMAINDER;
	c.weightx = 1.0;
	add(loginLabel, c);

	c.weighty = 1.0;
	c.ipadx = 100;
	c.ipady = 10;
	c.insets = new Insets(10, 0, 10, 0);
	add(createButton(LOGOUT), c);

	add(createContactList(), c);

	c.weighty = 1.0;
	c.ipadx = 100;
	c.ipady = 10;
	c.insets = new Insets(10, 0, 10, 0);
	add(createButton(TALK), c);
    }

    private JButton createButton(String buttonText) {
	JButton button = new JButton(buttonText);
	button.setActionCommand(buttonText);
	button.addActionListener(this);

	return button;
    }

    private JScrollPane createContactList() {
	listModel = new DefaultListModel<String>();
	listModel.addElement("Jan");
	listModel.addElement("Banan");

	list = new JList(listModel);
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list.addListSelectionListener(this);
	list.setVisibleRowCount(5);

	return new JScrollPane(list);
    }

    private void addContact(String name) {
	int index = 0;

	listModel.insertElementAt(name, index);
	list.setSelectedIndex(index);
	list.ensureIndexIsVisible(index);
    }

    private void removeContact(String name) {
	listModel.removeElement(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	String actionCommand = e.getActionCommand();

	// tutj powinno jeszcze powrocic do stanu poczatkowego czyli okna
	// logowania i puscic komunikat do servera, ze sie wyglogowujesz
	if (actionCommand.equals(LOGOUT)) {
	    connectionManager.closeConnection();
	    thread.stopReceiving();
	    this.dispose();
	} else if (actionCommand.equals(TALK)) {
	    ChatWindow chatWindow = new ChatWindow(listModel.getElementAt(list
		    .getSelectedIndex()));
	    chatWindow.pack();
	    chatWindow.setVisible(true);
	}
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
	// TODO Auto-generated method stub

    }

    private class ReceivingThread implements Runnable {
	Thread runner;

	public ReceivingThread() {
	    runner = new Thread(this, "");
	    runner.start();
	}

	public void stopReceiving() {
	    runner.stop();
	}

	@Override
	public void run() {
	    while (true) {
		connectionManager.receive();
	    }
	}

    }

}
