import javax.swing.*;
import java.awt.*;

public class WindowCreator {
    public static void mainWindow(){
        //Creates the JFrame and names it window
        JFrame window = new JFrame("Contact Manager");
        //Sets the layout and the bounds
        window.setLayout(new BorderLayout());
        window.setBounds(0,0,800,800);
        //Sets the close operation
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creates the top Panel
        JPanel ribbon = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        ribbon.setPreferredSize(new Dimension(500,30));
        ribbon.setBackground(Color.LIGHT_GRAY);

        //Adds the components to the window
        window.add(ribbon, BorderLayout.NORTH);

        //Adds the Buttons to the ribbon
        Buttons.NewContact(ribbon);

        //Sets the visibility of the window
        window.setVisible(true);
    }

}
