import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMSwings extends JFrame implements ActionListener {

    // Shared data
    int balance = 10000;
    int correctPin = 1234;

    // Components
    CardLayout card;
    JPanel mainPanel;

    JTextField pinField, amountField;
    JLabel balanceLabel;

    JButton loginBtn, balanceBtn, depositBtn, withdrawBtn, backBtn, logoutBtn, submitBtn;

    ATMSwings() {
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        card = new CardLayout();
        mainPanel = new JPanel(card);

        createLoginScreen();
        createMenuScreen();
        createAmountScreen();

        add(mainPanel);
        setVisible(true);
    }

    // ---------------- LOGIN SCREEN ----------------
    void createLoginScreen() {
        JPanel panel = new JPanel(null);

        JLabel label = new JLabel("Enter PIN");
        label.setBounds(150, 50, 100, 30);
        panel.add(label);

        pinField = new JTextField();
        pinField.setBounds(120, 90, 150, 30);
        panel.add(pinField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 140, 100, 30);
        loginBtn.addActionListener(this);
        panel.add(loginBtn);

        mainPanel.add(panel, "login");
    }

    // ---------------- MENU SCREEN ----------------
    void createMenuScreen() {
        JPanel panel = new JPanel(null);

        balanceBtn = new JButton("Check Balance");
        balanceBtn.setBounds(120, 30, 150, 30);
        balanceBtn.addActionListener(this);
        panel.add(balanceBtn);

        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(120, 70, 150, 30);
        depositBtn.addActionListener(this);
        panel.add(depositBtn);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(120, 110, 150, 30);
        withdrawBtn.addActionListener(this);
        panel.add(withdrawBtn);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(120, 150, 150, 30);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        balanceLabel = new JLabel("", SwingConstants.CENTER);
        balanceLabel.setBounds(50, 190, 300, 30);
        panel.add(balanceLabel);

        mainPanel.add(panel, "menu");
    }

    // ---------------- AMOUNT SCREEN ----------------
    void createAmountScreen() {
        JPanel panel = new JPanel(null);

        JLabel label = new JLabel("Enter Amount");
        label.setBounds(130, 50, 150, 30);
        panel.add(label);

        amountField = new JTextField();
        amountField.setBounds(120, 90, 150, 30);
        panel.add(amountField);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(70, 140, 100, 30);
        submitBtn.addActionListener(this);
        panel.add(submitBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(200, 140, 100, 30);
        backBtn.addActionListener(this);
        panel.add(backBtn);

        mainPanel.add(panel, "amount");
    }

    // ---------------- BUTTON ACTIONS ----------------
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {
            int pin = Integer.parseInt(pinField.getText());
            if (pin == correctPin) {
                card.show(mainPanel, "menu");
            } else {
                JOptionPane.showMessageDialog(this, "Wrong PIN");
            }
        }

        if (e.getSource() == balanceBtn) {
            balanceLabel.setText("Balance: ₹" + balance);
        }

        if (e.getSource() == depositBtn) {
            submitBtn.setText("Deposit");
            card.show(mainPanel, "amount");
        }

        if (e.getSource() == withdrawBtn) {
            submitBtn.setText("Withdraw");
            card.show(mainPanel, "amount");
        }

        if (e.getSource() == submitBtn) {
            int amt = Integer.parseInt(amountField.getText());

            if (submitBtn.getText().equals("Deposit")) {
                balance += amt;
                JOptionPane.showMessageDialog(this, "Deposited Successfully");
            } else {
                if (amt <= balance) {
                    balance -= amt;
                    JOptionPane.showMessageDialog(this, "Withdraw Successful");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                }
            }
            amountField.setText("");
            card.show(mainPanel, "menu");
        }

        if (e.getSource() == backBtn) {
            card.show(mainPanel, "menu");
        }

        if (e.getSource() == logoutBtn) {
            pinField.setText("");
            balanceLabel.setText("");
            card.show(mainPanel, "login");
        }
    }

    public static void main(String[] args) {
        new ATMSwings();
    }
}
