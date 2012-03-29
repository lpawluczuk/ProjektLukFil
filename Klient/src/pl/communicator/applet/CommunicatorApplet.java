package pl.communicator.applet;

import java.applet.Applet;
import java.awt.Graphics;

public class CommunicatorApplet extends Applet {
    StringBuffer buffer;

    @Override
    public void init() {
	buffer = new StringBuffer();
	addItem("Initializing...");
    }

    @Override
    public void start() {
	addItem("Statring...");
    }

    @Override
    public void stop() {
	addItem("Stopping...");

    }

    @Override
    public void destroy() {
	addItem("Destroy....");

    }

    private void addItem(String newWord) {
	System.out.println(newWord);
	buffer.append(newWord);
	repaint();
    }

    public void paint(Graphics g) {
	g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	g.drawString(buffer.toString(), 5, 15);
    }

}
