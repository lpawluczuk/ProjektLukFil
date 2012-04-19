import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {
    private static final String LOGIN_COMMAND = "login";

    private LoginForm loginForm;
    private ContactList contactList;

    public static String login, hostAddress;

    public MainPanel() {
	super(new BorderLayout());
	createLoginForm();
    }

    private void createLoginForm() {
	loginForm = new LoginForm();
	loginForm.addActionListener(this, LOGIN_COMMAND);
	add(loginForm);
    }

    private void createContactList() {
	contactList = new ContactList();
	contactList.pack();
	contactList.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (!e.getActionCommand().equals(LOGIN_COMMAND))
	    return;

	login = loginForm.getLogin();
	hostAddress = loginForm.getHostAddress();

	if (login != null && !login.equals("") && hostAddress != null
		&& !hostAddress.equals("")) {
	    loginForm.setVisible(false);
	    createContactList();
	} else {
	    System.out.println("Login nie moze byc pusty!!");
	}

    }
}
