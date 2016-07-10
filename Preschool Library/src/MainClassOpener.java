import javax.swing.JFrame;


public class MainClassOpener {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        LibraryDisplay libraryDisplay = new LibraryDisplay();
       libraryDisplay.setSize(1500,600);
       libraryDisplay.setLocationRelativeTo(null);
		libraryDisplay.setVisible(true);
		libraryDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }
    
}
