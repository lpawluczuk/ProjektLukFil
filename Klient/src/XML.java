import java.util.ArrayList;

public class XML {
    public static final char MESSAGE_TAG = 'm';
    public static final char HELLO_TAG = 'h';
    public static final char GOODBAY_TAG = 'g';
    public static final char CONTACT_TAG = 'c';
    public static final char TAG_END = ';';

    private String message;

    public XML(String message) {
	this.message = message;
    }

    public static String addTag(String tag, String text) {
	return tag + text + TAG_END;
    }

    public static String createMessage(String doKogo, String message) {
	String messageToSend = MESSAGE_TAG + doKogo + TAG_END
		+ ChatWindow.now() + TAG_END + message;

	System.out.println(messageToSend);
	return messageToSend;
    }

    public static String createHelloMessage() {
	String helloMessage = HELLO_TAG + MainPanel.login;
	System.out.println(helloMessage);
	return helloMessage;
    }

    public char getMessageType(String message) {
	return message.charAt(0);
    }

    public static Message parseMessage(String serverMessage) {
	int endTagPosition = serverMessage.indexOf(TAG_END);
	Message message = new Message();

	message.setSender(serverMessage.subSequence(1, endTagPosition)
		.toString());
	serverMessage = serverMessage.subSequence(endTagPosition + 1,
		serverMessage.length()).toString();

	endTagPosition = serverMessage.indexOf(TAG_END);
	message.setDate(serverMessage.subSequence(0, endTagPosition).toString());
	serverMessage = serverMessage.subSequence(endTagPosition + 1,
		serverMessage.length()).toString();

	message.setMessage(serverMessage.subSequence(0, serverMessage.length())
		.toString());

	System.out.println(message.toString());
	return message;
    }

    public static Contact parseContact(String serverMessage) {
	boolean isHelloMessage;

	if (serverMessage.charAt(0) == HELLO_TAG)
	    isHelloMessage = true;
	else
	    isHelloMessage = false;

	return new Contact(serverMessage.subSequence(1, serverMessage.length())
		.toString(), isHelloMessage);
    }

    public static ArrayList<Contact> parseContactList(String serverMessage) {
	ArrayList<Contact> contactList = new ArrayList<Contact>();
	int endTagPosition = 0;
	serverMessage = serverMessage.subSequence(0, 1).toString();

	while (endTagPosition != -1) {
	    contactList.add(new Contact(serverMessage.subSequence(0,
		    endTagPosition).toString(), true));
	}
	return new ArrayList<Contact>();
    }
}
