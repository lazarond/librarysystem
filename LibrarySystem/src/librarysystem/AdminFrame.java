package librarysystem;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class AdminFrame {
	
    private JFrame frame;
    private ActionListener loginFrame;
    JPanel mainPanel = new JPanel();
    JPanel topPanel = new JPanel();

    /**
     * Create the application.
     */
    
    public AdminFrame(ActionListener actionListener) {
        this.loginFrame = actionListener;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    private void initialize() {
    	    	
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 1041, 605);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);
        
        topPanel.setBackground(new Color(60, 72, 107));
        topPanel.setBounds(0, 0, 1025, 105);
        mainPanel.add(topPanel);
        topPanel.setLayout(null);
        
        JLabel adminWelcome_lbl = new JLabel("Welcome to NU Library System");
        adminWelcome_lbl.setBounds(39, 11, 630, 78);
        topPanel.add(adminWelcome_lbl);
        adminWelcome_lbl.setForeground(new Color(255, 255, 255));
        adminWelcome_lbl.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
        
        frame.setVisible(true);
    }
}
