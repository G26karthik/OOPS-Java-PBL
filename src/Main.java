import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String userName = JOptionPane.showInputDialog("Enter your name:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MentalHealthTest test = new MentalHealthTest();
        test.startTest();  // Start the mental health test

        DatabaseHandler dbHandler = new DatabaseHandler();
        test.calculateResults(dbHandler, userName);  // Calculate and store results
    }
}
