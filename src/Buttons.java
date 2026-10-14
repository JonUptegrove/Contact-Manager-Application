//Creates the Buttons then adds them to the ribbons
//Also handles all of their events

import javax.swing.*;

public class Buttons {
    public static void NewContact(JPanel ribbon){
        //Creates the "Create new Contact" button
        JButton newContact = new JButton("Create Contact");
        //adds the button to a ribbon
        ribbon.add(newContact);

        newContact.addActionListener(e-> {
                CreateContact.ContactCreator();
        });
    }
}
