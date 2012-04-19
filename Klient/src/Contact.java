public class Contact {
    private String login;
    private boolean isHelloContact;

    public Contact(String login, boolean isHelloContact) {
	this.login = login;
	this.isHelloContact = isHelloContact;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getLogin() {
	return login;
    }

    public boolean isHelloContact() {
	return isHelloContact;
    }
}
