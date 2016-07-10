import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {
	
	public JLabel searchLabel;
	public JTextField searchTextField;
	public JButton searchGenreButton;
	public JButton searchAuthorButton;	
	public JButton searchTitleButton;
	public JButton showAllButton;
	public JButton sortByTitleButton;
	public JButton sortByAuthorButton;
	
	public SearchPanel()
	{
		
		searchLabel = new JLabel("Search");
		searchTextField = new JTextField();
		
		searchAuthorButton = new JButton();
        searchTitleButton = new JButton();
        searchGenreButton = new JButton();
        showAllButton = new JButton();
        sortByTitleButton = new JButton();
        sortByAuthorButton = new JButton();
		
		searchAuthorButton.setText("Search Author");
        searchTitleButton.setText("Search Title");   
        searchGenreButton.setText("Search Genre"); 
        showAllButton.setText("Show All Books"); 
        sortByTitleButton.setText("Sort By Title"); 
        sortByAuthorButton.setText("Sort By Author");
		
        Dimension searching = new Dimension(100,30);
        searchTextField.setPreferredSize(searching);
        
        setLayout(new GridBagLayout());    
        GridBagConstraints c = new GridBagConstraints();
     
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(searchLabel, c);
        
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        add(searchTextField, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        add(searchTitleButton, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        add(searchAuthorButton, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        add(searchAuthorButton, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 5;
        add(searchGenreButton, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 6;
        add(showAllButton, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 7;
        add(sortByTitleButton, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 8;
        add(sortByAuthorButton, c);
        
        
        
		
		
	}

}

