public class Message {
    private String sender, date, message;

    public Message() {

    }

    public Message(String sender, String date, String message) {
	this.sender = sender;
	this.date = date;
	this.message = message;
    }

    public String getSender() {
	return sender;
    }

    public String getDate() {
	return date;
    }

    public String getMessage() {
	return message;
    }

    public void setSender(String sender) {
	this.sender = sender;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return "Sender: " + sender + ", Date: " + date + ", Message: "
		+ message;
    }

}
