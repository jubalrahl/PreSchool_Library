import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditBookPanel extends JPanel {
	

	
	public JButton editBookButton;
	
	public JTextField [] addBookTexts;
	public JLabel [] addBookLabels;
	
	
	
	public EditBookPanel()
	{
		
		
		 initPanel();
		
  
      
		
	}



	private void initPanel() {
		
		
    editBookButton = new JButton();
       
        
        addBookTexts = new JTextField[12];
        for(int fillBook = 0; fillBook < addBookTexts.length; fillBook++ )
        {
            addBookTexts[fillBook]= new JTextField("");
        }
        
        addBookLabels = new JLabel[12];
        for(int fillBook = 0; fillBook < addBookTexts.length; fillBook++ )
        {
        	addBookLabels[fillBook]= new JLabel();
        	
        	if (fillBook == 0){
        		addBookLabels[fillBook]= new JLabel("Title:");
        		addBookLabels[fillBook].setHorizontalAlignment(JLabel.RIGHT);
        	}
        	if (fillBook == 1){
        		addBookLabels[fillBook]= new JLabel("Author:");
        		addBookLabels[fillBook].setHorizontalAlignment(JLabel.RIGHT);
        	}
        	if (fillBook > 1){
        		addBookLabels[fillBook]= new JLabel("Genre:");
        		addBookLabels[fillBook].setHorizontalAlignment(JLabel.RIGHT);
        	}
        }
        
       
        
        editBookButton.setText("Finish Edit");
        
        
        Dimension searching = new Dimension(100,30);
        
        for(int fillBook = 0; fillBook < addBookTexts.length; fillBook++ )
        {
        	addBookTexts[fillBook].setPreferredSize(searching);
        }
        
       
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();

        int counter = 0;
		 
		 for (int i = 0; i < 3; i++){
	        	
			 for (int b = 0; b < 8; b++){
				 
				
		        	
				 d.fill = GridBagConstraints.HORIZONTAL;
		            d.weightx = 0.5;
		            d.gridx = b;
		            d.gridy = i;
		           
		            add(addBookLabels[counter], d);
		            
		            b++;
		            
		            d.weightx = 0.5;
		            d.gridx = b;
		            d.gridy = i;
		            add(addBookTexts[counter], d);
		            
		            counter++;
		        	
		        }
	        	
	        	
	        }
        
        
        
        d.weightx = 0.5;
        d.gridx = 1;
        d.gridy = 3;
        add(editBookButton, d);
	}

}
