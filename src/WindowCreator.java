
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class WindowCreator {

    public static DefaultListModel<Contact> contactModel =
            new DefaultListModel<>();

    public static JList<Contact> contactList =
            new JList<>(contactModel);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WindowCreator::mainWindow);
    }

    public static void mainWindow() {
        // Load saved contacts before displaying the window.
        try {
            List<Contact> contacts = ContactFileManager.load();

            for (Contact contact : contacts) {
                contactModel.addElement(contact);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not load contacts: " + ex.getMessage(),
                    "Load Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        JFrame window = new JFrame("Contact Manager");

        window.setLayout(new BorderLayout());
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        JPanel ribbon = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 5, 5)
        );
        ribbon.setBackground(Color.LIGHT_GRAY);

        Buttons.NewContact(ribbon);
        Buttons.EditContact(ribbon);
        Buttons.DeleteContact(ribbon);
        Buttons.SaveContacts(ribbon);

        window.add(ribbon, BorderLayout.NORTH);

        contactList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );
        contactList.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(contactList);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Your Contacts")
        );

        window.add(scrollPane, BorderLayout.CENTER);

        window.setVisible(true);
    }
}
