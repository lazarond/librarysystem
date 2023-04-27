package librarysystem;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;

public class LibrarianFrame { //main class

	private JFrame frame;
	private ActionListener loginFrame;
	JPanel mainPanel = new JPanel();
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	
	// Create the book list
    String[] bookTitles = {"Maze Runner-The Scorch Trials", "Summer I Turned Pretty", "Tantei High", "To Kill a Mockingbird", "Winter Woods"};
    String[] bookAuthors = {"James Dashmer", "Jenny Han", "Purpleyhan", "Harper Lee", "Cosmos & Van.J"};
    int[] bookCopies = {5, 3, 2, 4, 3};
	JList bookList = new JList(bookTitles); //displays the available books
    
   
    private JTextArea createJTextArea(String text, int x, int y, int width, int height) { //text areas for displaying book info
    	
		JTextArea jTextArea = new JTextArea ();
		jTextArea.setText(text);
	    jTextArea.setLineWrap(true);
	    jTextArea.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
	    jTextArea.setEditable(false);
	    jTextArea.setBounds(x, y, width, height);
	    return jTextArea;
    }
    
    private JTextArea createBookTitleTextArea(String text, int x, int y) { //text areas for displaying book info
    	
        JTextArea txtTitle = new JTextArea();
        txtTitle.setText(text);
        txtTitle.setLineWrap(true);
        txtTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
        txtTitle.setEditable(false);
        txtTitle.setBounds(x, y, 170, 23);
        mainPanel.add(txtTitle);
        return txtTitle;
    }
    
    
    public LibrarianFrame(ActionListener actionListener) { //create the application
    	
        this.loginFrame = actionListener;
        initialize();
    }
    
	
	private void initialize() { //initialize the contents of the frame
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1041, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setLocation(10, -381);
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		panel.setBackground(new Color(60, 72, 107));
		panel.setBounds(0, 343, 1025, 10);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		panel_1.setBackground(new Color(60, 72, 107));
		panel_1.setBounds(0, 101, 1025, 54);
		mainPanel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Available Books");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 40));
		panel_1.add(lblNewLabel);
		lblNewLabel.setLabelFor(panel_1);
		
		//book covers	
		JLabel[] bookLbls = new JLabel[5];
		String[] bookPaths = {"/Maze_Runner_book.jpg", "/Summer_i_turned_pretty_book.jpg", "/Tantei_high_book.jpg", "/To_Kill_a_Mockingbird_book.jpg", "/Winter_Woods_book.jpg"};

		for (int i = 0; i < 5; i++) {
		    bookLbls[i] = new JLabel("");
		    Image img = new ImageIcon(this.getClass().getResource(bookPaths[i])).getImage();
		    bookLbls[i].setIcon(new ImageIcon(img));
		    bookLbls[i].setBounds(61 + 199*i, 182, 121, 150);
		    mainPanel.add(bookLbls[i]);
		}
	
		//array of book copies labels and positions
		JLabel[] bookCopiesLabels = new JLabel[5];
		int[][] bookCopiesLabelPositions = {
		    {48, 454},
		    {248, 454},
		    {451, 451},
		    {642, 451},
		    {840, 452}
		};

		//borrow button action listener
		ActionListener borrowBtnActionListener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        //get index of selected book
		        int index = Integer.parseInt(((JButton) e.getSource()).getName());

		        //get number of copies available
		        int copies = bookCopies[index];
		        
		        //get name of the book
		        String bookName = bookTitles[index];
		        
		        if (copies == 0) {
		            //show message if no copies available
		            JOptionPane.showMessageDialog(frame, "No copies available!", "Borrow Book", JOptionPane.WARNING_MESSAGE);
		            
		        } else {
		        	
		        //display dialog box with number of copies available and prompt user to confirm borrowing
		        String message = "Number of copies available: " + copies + "\n\nDo you want to borrow this book?";
		        int option = JOptionPane.showConfirmDialog(frame, message, "Borrow Book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		        if (option == JOptionPane.YES_OPTION) {
		            //decrement number of copies available
		            bookCopies[index]--;
		            copies--;

		            //update display
		            bookList.repaint();
		            bookCopiesLabels[index].setText("Copies Available: " + copies);

		            //display message depending on number of copies available
		            if (copies >= 0) {
		                JOptionPane.showMessageDialog(frame, "Book '" + bookName +"' borrowed successfully!", "Borrow Book", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(frame, "No copies available!", "Borrow Book", JOptionPane.WARNING_MESSAGE);
		            }
		        }
		    }
	    }
	};

		//book copies labels and borrow buttons
		for (int i = 0; i < bookCopiesLabels.length; i++) {
		    bookCopiesLabels[i] = new JLabel("Copies Available: " + bookCopies[i]);
		    bookCopiesLabels[i].setFont(new Font("Tw Cen MT", Font.PLAIN, 12));
		    bookCopiesLabels[i].setBounds(bookCopiesLabelPositions[i][0]+10, bookCopiesLabelPositions[i][1], 105, 14);
		    mainPanel.add(bookCopiesLabels[i]);

		    JButton borrowBtn = new JButton("Borrow");
		    borrowBtn.setName(String.valueOf(i));
		    borrowBtn.addActionListener(borrowBtnActionListener);
		    borrowBtn.setForeground(new Color(255, 255, 255));
		    borrowBtn.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		    borrowBtn.setBackground(new Color(60, 72, 107));
		    borrowBtn.setBounds(bookCopiesLabelPositions[i][0] + 16, bookCopiesLabelPositions[i][1] - 28, 89, 23);
		    mainPanel.add(borrowBtn);
		}

		
		//book titles	
		
        JTextArea book1_txtTitle = createBookTitleTextArea("Maze Runner - The Scorch Trials", 26, 364);
        JTextArea book2_txtTitle = createBookTitleTextArea("The Summer I Turned Pretty", 239, 364);
        JTextArea book3_txtTitle = createBookTitleTextArea("Tantei High", 477, 364);
        JTextArea book4_txtTitle = createBookTitleTextArea("To Kill a Mockingbird", 647, 364);
        JTextArea book5_txtTitle = createBookTitleTextArea("Winter Woods", 856, 364);

		//authors name
		
		JTextArea book1_txtTitle_1 = createJTextArea("by James Dashmer", 60, 383, 105, 23);
		mainPanel.add(book1_txtTitle_1);

		JTextArea book1_txtTitle_2 = createJTextArea("by Harper Lee", 664, 383, 83, 23);
		mainPanel.add(book1_txtTitle_2);

		JTextArea book1_txtTitle_3 = createJTextArea("by Jenny Han", 270, 383, 83, 23);
		mainPanel.add(book1_txtTitle_3);

		JTextArea book1_txtTitle_4 = createJTextArea("by Purpleyhan", 467, 383, 83, 23);
		mainPanel.add(book1_txtTitle_4);

		JTextArea book1_txtTitle_5 = createJTextArea("by Cosmos & Van.J", 848, 383, 118, 23);
		mainPanel.add(book1_txtTitle_5);
		
	    frame.setVisible(true);

		}


	}

