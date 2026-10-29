
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ContactFileManager {

    private static final Path FILE_PATH =
            Paths.get("contacts.txt");

    public static void save(List<Contact> contacts)
            throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(
                FILE_PATH, StandardCharsets.UTF_8)) {

            Base64.Encoder encoder = Base64.getEncoder();

            for (Contact contact : contacts) {
                String name = encoder.encodeToString(
                        contact.getName().getBytes(StandardCharsets.UTF_8));

                String phone = encoder.encodeToString(
                        contact.getPhoneNumber().getBytes(StandardCharsets.UTF_8));

                writer.write(name + "\t" + phone);
                writer.newLine();
            }
        }
    }

    public static List<Contact> load() throws IOException {
        List<Contact> contacts = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return contacts;
        }

        Base64.Decoder decoder = Base64.getDecoder();

        try (BufferedReader reader = Files.newBufferedReader(
                FILE_PATH, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t", -1);

                if (parts.length != 2) {
                    throw new IOException(
                            "Invalid contact record in contacts.txt");
                }

                try {
                    String name = new String(
                            decoder.decode(parts[0]),
                            StandardCharsets.UTF_8);

                    String phone = new String(
                            decoder.decode(parts[1]),
                            StandardCharsets.UTF_8);

                    contacts.add(new Contact(name, phone));

                } catch (IllegalArgumentException ex) {
                    throw new IOException(
                            "Could not read a contact from contacts.txt",
                            ex);
                }
            }
        }

        return contacts;
    }
}
