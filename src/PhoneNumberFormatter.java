
import javax.swing.*;
import javax.swing.text.*;
import java.util.regex.Pattern;

public class PhoneNumberFormatter extends DocumentFilter {

    private final JTextField textField;

    public PhoneNumberFormatter(JTextField textField) {
        this.textField = textField;
    }

    private String digitsOnly(String text) {
        return text.replaceAll("[^0-9]", "");
    }

    private String formatPhone(String digits) {
        if (digits.length() > 10) {
            digits = digits.substring(0, 10);
        }

        if (digits.length() > 6) {
            return digits.substring(0, 3) + "-"
                    + digits.substring(3, 6) + "-"
                    + digits.substring(6);
        }

        if (digits.length() > 3) {
            return digits.substring(0, 3) + "-"
                    + digits.substring(3);
        }

        return digits;
    }

    private int caretPosition(String formatted, int digitCount) {
        int position = 0;
        int digitsFound = 0;

        while (position < formatted.length()
                && digitsFound < digitCount) {
            if (Character.isDigit(formatted.charAt(position))) {
                digitsFound++;
            }
            position++;
        }

        // Move past a hyphen if it follows the last digit.
        if (position < formatted.length()
                && formatted.charAt(position) == '-') {
            position++;
        }

        return position;
    }

    @Override
    public void insertString(FilterBypass fb, int offset,
                             String string, AttributeSet attr)
            throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        replace(fb, offset, length, "", null);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length,
                        String text, AttributeSet attrs)
            throws BadLocationException {

        Document document = fb.getDocument();
        String current = document.getText(0, document.getLength());

        int digitsBefore = digitsOnly(
                current.substring(0, offset)).length();

        String insertedDigits = digitsOnly(text == null ? "" : text);

        String remaining = current.substring(offset + length);
        String digitsAfter = digitsOnly(remaining);

        String combined = digitsOnly(current.substring(0, offset))
                + insertedDigits + digitsAfter;

        if (combined.length() > 10) {
            combined = combined.substring(0, 10);
        }

        int newCaretDigits = Math.min(
                digitsBefore + insertedDigits.length(),
                combined.length()
        );

        String formatted = formatPhone(combined);

        fb.replace(0, document.getLength(), formatted, attrs);

        int caret = caretPosition(formatted, newCaretDigits);
        textField.setCaretPosition(Math.min(caret, formatted.length()));
    }
}