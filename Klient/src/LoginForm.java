import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginForm extends JPanel {
    private static final String LOGIN_STRING = "Your Login: ";
    private static final String HOST_STRING = "Server address: ";
    private static final String OK = "OK";

    private JTextField loginField, hostField;
    private JButton button;

    public LoginForm() {

	JPanel contentPane = new JPanel(new GridBagLayout());
	contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	add(contentPane);

	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.HORIZONTAL;

	JLabel jLabel1 = new JLabel(LOGIN_STRING, JLabel.TRAILING);
	add(jLabel1, c);

	loginField = new JTextField(10);
	//loginField.setText("Luk");
	jLabel1.setLabelFor(loginField);
	c.weightx = 1.0;
	c.gridwidth = GridBagConstraints.REMAINDER;

	add(loginField, c);

	JLabel jLabel2 = new JLabel(HOST_STRING, JLabel.TRAILING);
	add(jLabel2, c);

	hostField = new JTextField(10);
	hostField.setText("150.254.68.134");
	jLabel2.setLabelFor(hostField);
	c.gridwidth = GridBagConstraints.REMAINDER;
	c.weightx = 1.0;
	add(hostField, c);

	button = new JButton(OK);
	c.weighty = 1.0;
	c.ipadx = 10;
	c.ipady = 10;
	c.insets = new Insets(10, 0, 0, 0);
	c.anchor = GridBagConstraints.PAGE_END;
	add(button, c);

    }

    public void addActionListener(ActionListener actionListener,
	    String actionCommand) {
	button.addActionListener(actionListener);
	button.setActionCommand(actionCommand);
    }

    public String getLogin() {
	return loginField.getText();
    }

    public String getHostAddress() {
	return hostField.getText();
    }

}
