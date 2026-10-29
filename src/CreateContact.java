
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class CreateContact {

    // No argument means create a new contact.
    public static void ContactCreator() {
        ContactCreator(null);
    }

    // Passing a contact means edit that contact.
    public static void ContactCreator(Contact existingContact) {
        boolean editing = existingContact != null;

        JFrame creator = new JFrame(
                editing ? "Edit Contact" : "Create a New Contact");

        creator.setSize(450, 220);
        creator.setLayout(new GridBagLayout());
        creator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        creator.setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameText = new JTextField(20);

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneNum = new JTextField(20);

        // Apply automatic phone formatting.
        AbstractDocument phoneDocument =
                (AbstractDocument) phoneNum.getDocument();

        phoneDocument.setDocumentFilter(
                new PhoneNumberFormatter(phoneNum));

        // Fill fields when editing.
        if (editing) {
            nameText.setText(existingContact.getName());
            phoneNum.setText(existingContact.getPhoneNumber());
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        creator.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        creator.add(nameText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        creator.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        creator.add(phoneNum, gbc);

        JButton completeContact = new JButton(
                editing ? "Save Changes" : "Create New Contact");

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        creator.add(completeContact, gbc);

        completeContact.addActionListener(e -> {
            String name = nameText.getText().trim();
            String phone = phoneNum.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(
                        creator,
                        "Please enter both a name and phone number.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String digits = phone.replaceAll("[^0-9]", "");

            if (digits.length() != 10) {
                JOptionPane.showMessageDialog(
                        creator,
                        "Please enter a 10-digit phone number.",
                        "Invalid Phone Number",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String formattedPhone = digits.substring(0, 3)
                    + "-" + digits.substring(3, 6)
                    + "-" + digits.substring(6);

            if (editing) {
                int index = WindowCreator.contactModel
                        .indexOf(existingContact);

                if (index == -1) {
                    JOptionPane.showMessageDialog(
                            creator,
                            "This contact no longer exists."
                    );
                    creator.dispose();
                    return;
                }

                existingContact.setName(name);
                existingContact.setPhoneNumber(formattedPhone);

                WindowCreator.contactModel.setElementAt(
                        existingContact, index);

            } else {
                Contact contact =
                        new Contact(name, formattedPhone);

                WindowCreator.contactModel.addElement(contact);
            }

            creator.dispose();
        });

        creator.setVisible(true);
    }
}
