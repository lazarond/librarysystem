package librarysystem;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import librarysystem.AdminFrame;
import librarysystem.UserCredentials;
import librarysystem.LibrarianFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrame {

	private JFrame frame;
	private JTextField usernameTextfield;
	private ArrayList<UserCredentials> userCredentialsList;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 695, 398);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(176, 224, 230));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		//initialize the user credentials list
        userCredentialsList = new ArrayList<>();

        //add admin credentials
        userCredentialsList.add(new UserCredentials("Admin_01", "AdminOne", "Admin"));
        userCredentialsList.add(new UserCredentials("Admin_02", "AdminTwo", "Admin"));
        userCredentialsList.add(new UserCredentials("Admin_03", "AdminThree", "Admin"));

        //add librarian credentials
        userCredentialsList.add(new UserCredentials("Librarian_01", "LibOne", "Librarian"));
        userCredentialsList.add(new UserCredentials("Librarian_02", "LibTwo", "Librarian"));
        userCredentialsList.add(new UserCredentials("Librarian_03", "LibThree", "Librarian"));	
		
		JPanel LoginDesign = new JPanel();
		LoginDesign.setBackground(new Color(60, 72, 107));
		LoginDesign.setForeground(Color.BLACK);
		LoginDesign.setBounds(427, 0, 252, 359);
		mainPanel.add(LoginDesign);
		LoginDesign.setLayout(null);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setForeground(new Color(176, 224, 230));
		loginLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 45));
		loginLabel.setBounds(21, 73, 133, 51);
		LoginDesign.add(loginLabel);
		
		JComboBox<String> comboBox = new JComboBox<>(); //for user types (admin and librarian)
		comboBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		comboBox.setForeground(new Color(60, 72, 107));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Admin", "Librarian"}));
		comboBox.setBounds(98, 135, 122, 24);
		LoginDesign.add(comboBox);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(new Color(176, 224, 230));
		usernameLabel.setBackground(new Color(176, 224, 230));
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameLabel.setBounds(21, 171, 67, 14);
		LoginDesign.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(176, 224, 230));
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordLabel.setBounds(21, 201, 67, 14);
		LoginDesign.add(passwordLabel);
		
		usernameTextfield = new JTextField();
		usernameTextfield.setForeground(new Color(60, 72, 107));
		usernameTextfield.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		usernameTextfield.setBounds(98, 170, 122, 20);
		LoginDesign.add(usernameTextfield);
		usernameTextfield.setColumns(10);
		
		JCheckBox checkPasswordbox = new JCheckBox("Show password");
		checkPasswordbox.setFont(new Font("Tw Cen MT", Font.PLAIN, 11));
		checkPasswordbox.setForeground(new Color(176, 224, 230));
		checkPasswordbox.setBackground(new Color(60, 72, 107));
		checkPasswordbox.setBounds(125, 221, 95, 14);
		LoginDesign.add(checkPasswordbox);
		
		checkPasswordbox.addActionListener(new ActionListener() { //show password feature
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JCheckBox cb = (JCheckBox) e.getSource();
		        passwordField.setEchoChar(cb.isSelected() ? '\u0000' : '*');
		    }
		});
		
		
		JButton login_btn = new JButton("Login");
		login_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { //executes when the login button is clicked
				
				//getting the input values of username, password, and userType
			    String username = usernameTextfield.getText().trim();
			    String password = new String(passwordField.getPassword()).trim();
			    String userType = (String) comboBox.getSelectedItem();
			    String successfulLogin = "SUCCESSFULLY LOGIN";
			    
			    //initialize the variables
			    boolean usernameCorrect = false;
			    boolean passwordCorrect = false;
			    boolean userTypeCorrect = false;

			  //iterating through the userCredentialsList to check if the input values are correct
			    for (UserCredentials userCredentials : userCredentialsList) {
			    	
					//checking if both the username and password are correct
			        if (userCredentials.getUsername().equals(username) && userCredentials.getPassword().equals(password)) {
			        	
			            usernameCorrect = true;
			            passwordCorrect = true;
			            
			    		//checking if the userType is also correct
			            if (userCredentials.getUserType().equals(userType)) {
			                userTypeCorrect = true;
			            }
			            break;
			            
			        	//checking if only the username is correct	
			        } else if (userCredentials.getUsername().equals(username)) {
			            usernameCorrect = true;
			            
			        	//Checking if only the password is correct	
			        } else if (userCredentials.getPassword().equals(password)) {
			            passwordCorrect = true;
			        }
			    }
			        
			    	//displays appropriate messages depending on the input values
			        if (usernameCorrect && passwordCorrect && userTypeCorrect) {
			        	
			        	//displaying a success message and disposing of the login frame
			            JOptionPane.showMessageDialog(null, successfulLogin, "Successfully Logged In!", JOptionPane.INFORMATION_MESSAGE);
			            frame.dispose();

			        	//opening a new frame depending on the userType
			            if (userType.equals("Admin")) {
			                new AdminFrame(this);

			            } else if (userType.equals("Librarian")) {
			                new LibrarianFrame(this);

			            }
			            

			   
			    } else if (username.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Please enter your username", "Error", JOptionPane.WARNING_MESSAGE);
			    } else if (password.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Please enter your password", "Error", JOptionPane.WARNING_MESSAGE);
			    } else if (usernameCorrect && !passwordCorrect) {
			        JOptionPane.showMessageDialog(null, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
			    } else if (!usernameCorrect && passwordCorrect) {
			        JOptionPane.showMessageDialog(null, "Incorrect username", "Error", JOptionPane.ERROR_MESSAGE);
			    } else {
			        JOptionPane.showMessageDialog(null, "Incorrect username and password", "Error", JOptionPane.ERROR_MESSAGE);
			    }
			}

					
		});
		
		login_btn.setForeground(new Color(60, 72, 107));
		login_btn.setBackground(new Color(176, 224, 230));
		login_btn.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		login_btn.setBounds(74, 259, 102, 23);
		LoginDesign.add(login_btn);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setForeground(new Color(176, 224, 230));
		lblUserType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserType.setBackground(new Color(176, 224, 230));
		lblUserType.setBounds(21, 142, 67, 14);
		LoginDesign.add(lblUserType);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 199, 122, 20);
		LoginDesign.add(passwordField);
		
		JLabel titleLabel = new JLabel("WELCOME");
		titleLabel.setBackground(new Color(60, 72, 107));
		titleLabel.setForeground(new Color(60, 72, 107));
		titleLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 55));
		titleLabel.setBounds(98, 167, 236, 87);
		mainPanel.add(titleLabel);
		
		JLabel logoLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/National_University_logo.png")).getImage();
		logoLabel.setIcon(new ImageIcon(img));
		logoLabel.setBounds(171, 44, 130, 128);
		mainPanel.add(logoLabel);
		
		JLabel LMS_label = new JLabel("Library Management System");
		LMS_label.setForeground(new Color(60, 72, 107));
		LMS_label.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		LMS_label.setBounds(72, 240, 296, 28);
		mainPanel.add(LMS_label);
		
	}

	protected void dispose() {

		
	}
}






