import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class ChatApplet extends JApplet {

    public void init() {
	// Execute a job on the event-dispatching thread:
	// creating this applet's GUI.
	try {
	    SwingUtilities.invokeAndWait(new Runnable() {
		public void run() {
		    createGUI();
		}
	    });
	} catch (Exception e) {
	    System.err.println("createGUI didn't successfully complete");
	}
    }

    private void createGUI() {
	MainPanel mainPanel = new MainPanel();
	mainPanel.setOpaque(true);
	setContentPane(mainPanel);
	setSize(new Dimension(700, 60));
    }

    public void destroy() {
	// Execute a job on the event-dispatching thread:
	// creating this applet's GUI.
	try {
	    SwingUtilities.invokeAndWait(new Runnable() {
		public void run() {
		    // destroyGUI();
		}
	    });
	} catch (Exception e) {
	}
    }

    private void destroyGUI() {

    }
}
