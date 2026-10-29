//Creates the Buttons then adds them to the ribbons
//Also handles all of their events

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Buttons {

    public static void NewContact(JPanel ribbon) {
        JButton newContact = new JButton("Create Contact");

        newContact.addActionListener(e ->
                CreateContact.ContactCreator()
        );

        ribbon.add(newContact);
    }

    public static void EditContact(JPanel ribbon) {
        JButton editContact = new JButton("Edit Contact");

        editContact.addActionListener(e -> {
            Contact selected = WindowCreator.contactList
                    .getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please select a contact to edit."
                );
                return;
            }

            CreateContact.ContactCreator(selected);
        });

        ribbon.add(editContact);
    }

    public static void DeleteContact(JPanel ribbon) {
        JButton deleteContact = new JButton("Delete Contact");

        deleteContact.addActionListener(e -> {
            Contact selected = WindowCreator.contactList
                    .getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please select a contact to delete."
                );
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Delete " + selected.getName() + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                WindowCreator.contactModel.removeElement(selected);
            }
        });

        ribbon.add(deleteContact);
    }

    public static void SaveContacts(JPanel ribbon) {
        JButton saveContact = new JButton("Save");

        saveContact.addActionListener(e -> {
            List<Contact> contacts = new ArrayList<>();

            for (int i = 0;
                 i < WindowCreator.contactModel.getSize();
                 i++) {

                contacts.add(
                        WindowCreator.contactModel.getElementAt(i));
            }

            try {
                ContactFileManager.save(contacts);

                JOptionPane.showMessageDialog(
                        null,
                        "Contacts saved successfully."
                );

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Could not save contacts: " + ex.getMessage(),
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        ribbon.add(saveContact);
    }
}
