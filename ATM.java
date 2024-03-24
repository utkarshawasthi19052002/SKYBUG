
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ATMProject extends JFrame {
    private JPanel mainPanel;
    private JTextField amountTextField;
    private JButton[] numberButtons;
    private JButton clearButton;
    private JButton enterButton;
    private JButton cancelButton;
    private JTextArea receiptTextArea;
    private JLabel balanceLabel;

    private double currentBalance = 1000.00; // I have declared Initial balance

    public ATMProject() {
        setTitle("ATM Simulator");
        showWelcomeScreen();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showWelcomeScreen() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the ATM Simulator", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            remove(welcomePanel);
            initializeComponents();
        });
        welcomePanel.add(startButton, BorderLayout.SOUTH);

        add(welcomePanel);
        getContentPane().setBackground(Color.BLUE);
        pack();
    }

    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 4, 10, 10));
        mainPanel.setBackground(Color.BLUE);

        amountTextField = new JTextField();
        amountTextField.setEditable(false);
        amountTextField.setBackground(Color.WHITE);
        amountTextField.setHorizontalAlignment(JTextField.RIGHT);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBackground(Color.BLACK);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].addActionListener(new NumberButtonClickListener());
        }

        clearButton = new JButton("C");
        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(new ClearButtonClickListener());

        enterButton = new JButton("Enter");
        enterButton.setBackground(Color.BLACK);
        enterButton.setForeground(Color.WHITE);
        enterButton.addActionListener(new EnterButtonClickListener());

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new CancelButtonClickListener());

        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", currentBalance));
        balanceLabel.setForeground(Color.WHITE);

        receiptTextArea = new JTextArea();
        receiptTextArea.setEditable(false);
        receiptTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        receiptTextArea.setBackground(Color.WHITE);

        mainPanel.add(numberButtons[1]);
        mainPanel.add(numberButtons[2]);
        mainPanel.add(numberButtons[3]);
        mainPanel.add(clearButton);
        mainPanel.add(numberButtons[4]);
        mainPanel.add(numberButtons[5]);
        mainPanel.add(numberButtons[6]);
        mainPanel.add(cancelButton);
        mainPanel.add(numberButtons[7]);
        mainPanel.add(numberButtons[8]);
        mainPanel.add(numberButtons[9]);
        mainPanel.add(enterButton);
        mainPanel.add(new JLabel());
        mainPanel.add(numberButtons[0]);
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());

        setLayout(new BorderLayout(10, 10));
        add(amountTextField, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(balanceLabel, BorderLayout.SOUTH);
        pack();
    }

    private class NumberButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String currentText = amountTextField.getText();
            amountTextField.setText(currentText + button.getText());
        }
    }

    private class ClearButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearAmountTextField();
        }
    }

    private class EnterButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            processTransaction();
        }
    }

    private class CancelButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearAmountTextField();
            showMessage("Transaction canceled.");
        }
    }

    private void clearAmountTextField() {
        amountTextField.setText("");
    }

    private void processTransaction() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            if (amount > 0 && amount <= currentBalance) {
                currentBalance -= amount;
                updateBalanceLabel();
                updateTransactionHistory("Withdraw", amount);
                showReceipt();
            } else {
                showMessage("Invalid amount or insufficient funds.");
            }
        } catch (NumberFormatException ex) {
            showMessage("Invalid amount. Please enter a valid number.");
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", currentBalance));
    }

    private void updateTransactionHistory(String type, double amount) {
        
    }

    private void showReceipt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dateTime = dateFormat.format(new Date());

        String receiptText = "   ATM Receipt\n\n"
                + "Date/Time: " + dateTime + "\n"
                + "Transaction Type: Withdrawal\n"
                + "Amount: $" + amountTextField.getText() + "\n"
                + "Balance: " + balanceLabel.getText() + "\n\n"
                + "Thank You!";
        receiptTextArea.setText(receiptText);

        JOptionPane.showMessageDialog(this, new JScrollPane(receiptTextArea),
                "Receipt", JOptionPane.PLAIN_MESSAGE);
        clearAmountTextField();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATMProject atm = new ATMProject();
            atm.setSize(600, 400);
            atm.setVisible(true);
        });
    }
}
