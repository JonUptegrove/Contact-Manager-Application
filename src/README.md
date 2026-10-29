# Java Contact Manager

A desktop contact management application built with Java and Swing. Create, view, edit, and delete contacts, automatically format phone numbers, and save contact information locally between sessions.

## Features

- **Create Contacts** — Add contacts with a name and phone number.
- **View Contacts** — Display contacts in a scrollable list.
- **Edit Contacts** — Correct misspelled names or update phone numbers.
- **Delete Contacts** — Remove contacts with a confirmation prompt.
- **Automatic Phone Formatting** — Format US phone numbers as `123-456-7890` while typing.
- **Input Validation** — Require a name and a valid 10-digit phone number.
- **Save Contacts** — Store contacts in a local `contacts.txt` file.
- **Automatic Loading** — Load previously saved contacts when the application starts.

## Technologies Used

- Java
- Java Swing
- Java AWT
- Java I/O and NIO
- Base64 Encoding

## Project Structure

```text
ContactManager/
├── Contact.java
├── WindowCreator.java
├── Buttons.java
├── CreateContact.java
├── PhoneNumberFormatter.java
├── ContactFileManager.java
└── contacts.txt
```

The `contacts.txt` file is created when contacts are saved for the first time.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- IntelliJ IDEA or another Java IDE

### Installation

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Ensure all Java files belong to the same package.
4. Configure the project to use an installed JDK.
5. Open `WindowCreator.java`.
6. Run the `main()` method.

No external libraries are required.

## Usage

1. Launch the application.
2. Click **Create Contact** to add a new contact.
3. Enter a name and a 10-digit US phone number.
4. Select a contact and click **Edit Contact** to modify it.
5. Select a contact and click **Delete Contact** to remove it.
6. Click **Save** to save the current contact list.
7. Restart the application to load previously saved contacts.

> **Note:** Changes are only permanently saved when the Save button is clicked. Unsaved changes may be lost when the application closes.

## Data Storage

Contact information is stored locally in `contacts.txt` in the application's working directory.

Each contact's name and phone number are encoded separately using Base64 before being written to the file.

Base64 is an encoding method, not encryption. It does not protect sensitive information from being read.

## Current Limitations

- Phone number validation supports 10-digit US numbers only.
- Contacts are stored in a local text file rather than a database.
- Saving changes requires clicking the Save button.
- Contact search functionality is not yet implemented.
- Corrupted contact files produce a loading error.

## Future Improvements

- [ ] Add contact search and filtering.
- [ ] Add email addresses and mailing addresses.
- [ ] Implement automatic saving after changes.
- [ ] Add automated unit tests.
- [ ] Integrate a MySQL database.
- [ ] Improve recovery from corrupted data files.
- [ ] Add screenshots of the application.

## Learning Objectives

This project provides practical experience with:

- Object-oriented programming in Java.
- Graphical user interface development with Swing.
- Event listeners and user interaction.
- Input validation and data formatting.
- File handling and data persistence.
- Separation of responsibilities across multiple classes.

## License

No open-source license has been selected for this project yet.
