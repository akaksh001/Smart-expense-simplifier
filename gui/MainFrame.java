package gui;

import model.*;
import util.AppLogger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * The main graphical user interface for the Smart Expense Splitter application.
 * Provides functionality to add/remove members, record/delete expenses,
 * view balances and settlement plans, and persist data.
 */
public class MainFrame extends JFrame {

    private ExpenseManager manager;
    private static final String DATA_FILE = "data.dat";

    private JTextField memberField;
    private JComboBox<String> payerBox;
    private JTextField amountField;
    private JTextField descriptionField;
    private JComboBox<ExpenseCategory> categoryBox;

    private DefaultListModel<String> memberModel;
    private JList<String> memberList;

    private DefaultTableModel balanceModel;
    private JTable balanceTable;

    private DefaultTableModel expenseTableModel;
    private JTable expenseTable;

    private JTextArea settlementArea;

    /**
     * Constructs the main application frame, initializes the expense manager,
     * and sets up the GUI components.
     */
    public MainFrame() {
        manager = new ExpenseManager();

        setTitle("Smart Expense Splitter");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        AppLogger.info("Application initialized.");

        createGUI();
    }

    /**
     * Initializes and arranges all GUI components within the frame.
     */
    private void createGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createTopPanel(), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(createInputPanel());
        centerPanel.add(createOutputPanel());

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(createExpenseHistoryPanel(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    /**
     * Creates the top panel containing the title and the toolbar.
     *
     * @return the top panel
     */
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel title = new JLabel("SMART EXPENSE SPLITTER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(title, BorderLayout.CENTER);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(e -> saveData());
        
        JButton loadButton = new JButton("Load Data");
        loadButton.addActionListener(e -> loadData());

        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(loadButton);

        panel.add(toolBar, BorderLayout.EAST);
        return panel;
    }

    /**
     * Creates the input panel for adding members and expenses.
     *
     * @return the input panel
     */
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Input Controls"));

        // Add Member Panel
        JPanel memberPanel = new JPanel(new BorderLayout(5, 5));
        memberPanel.setBorder(BorderFactory.createTitledBorder("Add / Delete Member"));
        
        memberField = new JTextField();
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.addActionListener(e -> addMember());

        JButton deleteMemberButton = new JButton("Delete Selected");
        deleteMemberButton.addActionListener(e -> deleteMember());

        JPanel memberActionPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        memberActionPanel.add(addMemberButton);
        memberActionPanel.add(deleteMemberButton);

        memberPanel.add(memberField, BorderLayout.CENTER);
        memberPanel.add(memberActionPanel, BorderLayout.SOUTH);

        panel.add(memberPanel);
        panel.add(Box.createVerticalStrut(15));

        // Add Expense Panel
        JPanel expensePanel = new JPanel(new GridLayout(5, 2, 8, 8));
        expensePanel.setBorder(BorderFactory.createTitledBorder("Add Expense"));

        payerBox = new JComboBox<>();
        amountField = new JTextField();
        descriptionField = new JTextField();
        categoryBox = new JComboBox<>(ExpenseCategory.values());

        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(e -> addExpense());

        expensePanel.add(new JLabel("Paid By:"));
        expensePanel.add(payerBox);
        expensePanel.add(new JLabel("Amount:"));
        expensePanel.add(amountField);
        expensePanel.add(new JLabel("Description:"));
        expensePanel.add(descriptionField);
        expensePanel.add(new JLabel("Category:"));
        expensePanel.add(categoryBox);
        expensePanel.add(new JLabel(""));
        expensePanel.add(addExpenseButton);

        panel.add(expensePanel);
        return panel;
    }

    /**
     * Creates the output panel showing members, balances, and settlements.
     *
     * @return the output panel
     */
    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Members List
        memberModel = new DefaultListModel<>();
        memberList = new JList<>(memberModel);
        memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JPanel membersPanel = new JPanel(new BorderLayout());
        membersPanel.setBorder(BorderFactory.createTitledBorder("Members"));
        membersPanel.add(new JScrollPane(memberList));

        // Balances Table
        balanceModel = new DefaultTableModel(new String[]{"Member", "Balance"}, 0);
        balanceTable = new JTable(balanceModel);
        
        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBorder(BorderFactory.createTitledBorder("Balances"));
        balancePanel.add(new JScrollPane(balanceTable));

        JPanel top = new JPanel(new GridLayout(1, 2, 10, 10));
        top.add(membersPanel);
        top.add(balancePanel);
        panel.add(top, BorderLayout.CENTER);

        // Settlement Area
        settlementArea = new JTextArea(5, 20);
        settlementArea.setEditable(false);
        
        JButton simplifyButton = new JButton("Simplify Debts");
        simplifyButton.addActionListener(e -> simplifyDebts());

        JPanel settlementPanel = new JPanel(new BorderLayout(5, 5));
        settlementPanel.setBorder(BorderFactory.createTitledBorder("Settlement"));
        settlementPanel.add(new JScrollPane(settlementArea), BorderLayout.CENTER);
        settlementPanel.add(simplifyButton, BorderLayout.SOUTH);

        panel.add(settlementPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the panel containing the expense history table and delete action.
     *
     * @return the expense history panel
     */
    private JPanel createExpenseHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Expense History"));
        panel.setPreferredSize(new Dimension(800, 250));

        String[] cols = {"ID", "Paid By", "Amount", "Description", "Category", "Timestamp"};
        expenseTableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        expenseTable = new JTable(expenseTableModel);
        
        panel.add(new JScrollPane(expenseTable), BorderLayout.CENTER);

        JButton deleteExpenseButton = new JButton("Delete Selected Expense");
        deleteExpenseButton.addActionListener(e -> deleteExpense());
        
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(deleteExpenseButton);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Handles the action of adding a new member.
     * Validates input, adds to manager, updates UI, and logs the action.
     */
    private void addMember() {
        String name = memberField.getText().trim();
        try {
            InputValidator.validateName(name);
            
            boolean added = manager.addMember(name);
            if (!added) {
                JOptionPane.showMessageDialog(this, "Member already exists.");
                AppLogger.warning("Attempted to add existing member: " + name);
                return;
            }

            memberModel.addElement(name);
            payerBox.addItem(name);
            memberField.setText("");
            updateBalances();
            AppLogger.info("Added new member: " + name);
            
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            AppLogger.warning("Add member failed validation: " + ex.getMessage());
        }
    }

    /**
     * Handles the action of deleting a selected member.
     * Removes from manager, updates UI, and logs the action.
     */
    private void deleteMember() {
        String selected = memberList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a member to delete.");
            return;
        }
        
        boolean removed = manager.removeMember(selected);
        if (removed) {
            memberModel.removeElement(selected);
            payerBox.removeItem(selected);
            updateBalances();
            AppLogger.info("Deleted member: " + selected);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to remove member.");
            AppLogger.error("Failed to delete member: " + selected);
        }
    }

    /**
     * Handles the action of adding a new expense.
     * Validates input, adds to manager, updates tables, and logs the action.
     */
    private void addExpense() {
        if (manager.getMembers().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add members first.");
            return;
        }

        String payer = (String) payerBox.getSelectedItem();
        if (payer == null) {
            JOptionPane.showMessageDialog(this, "Select a payer.");
            return;
        }

        String amountStr = amountField.getText().trim();
        String description = descriptionField.getText().trim();
        ExpenseCategory category = (ExpenseCategory) categoryBox.getSelectedItem();

        try {
            InputValidator.validateAmount(amountStr);
            InputValidator.validateDescription(description);

            double amount = Double.parseDouble(amountStr);

            manager.addExpense(payer, amount, description);
            
            // Workaround to set the category since manager.addExpense doesn't accept it
            int size = manager.getExpenses().size();
            Expense lastExp = manager.getExpenses().get(size - 1);
            Expense updatedExp = new Expense(payer, amount, description, category);
            updatedExp.setId(lastExp.getId());
            updatedExp.setTimestamp(lastExp.getTimestamp());
            Expense.setNextId(Expense.getNextId() - 1);
            manager.getExpenses().set(size - 1, updatedExp);
            
            amountField.setText("");
            descriptionField.setText("");

            updateBalances();
            updateExpenseTable();
            
            settlementArea.setText("Expense added successfully.\nClick 'Simplify Debts'.");
            AppLogger.info("Added expense: " + description + " by " + payer + " for " + amount);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            AppLogger.warning("Add expense failed validation: " + ex.getMessage());
        }
    }

    /**
     * Handles the action of deleting a selected expense.
     * Removes from manager, updates tables, and logs the action.
     */
    private void deleteExpense() {
        int selectedRow = expenseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an expense to delete.");
            return;
        }

        int expenseId = (int) expenseTableModel.getValueAt(selectedRow, 0);
        boolean removed = manager.removeExpense(expenseId);

        if (removed) {
            updateBalances();
            updateExpenseTable();
            settlementArea.setText("Expense deleted.\nClick 'Simplify Debts' to recalculate.");
            AppLogger.info("Deleted expense with ID: " + expenseId);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete expense.");
            AppLogger.error("Failed to delete expense with ID: " + expenseId);
        }
    }

    /**
     * Updates the balances table with current data from the manager.
     */
    private void updateBalances() {
        balanceModel.setRowCount(0);
        for (Person person : manager.getMembers()) {
            balanceModel.addRow(new Object[]{
                    person.getName(),
                    String.format("₹%.2f", person.getBalance())
            });
        }
    }

    /**
     * Updates the expense history table with current data from the manager.
     */
    private void updateExpenseTable() {
        expenseTableModel.setRowCount(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Expense e : manager.getExpenses()) {
            expenseTableModel.addRow(new Object[]{
                    e.getId(),
                    e.getPaidBy(),
                    String.format("₹%.2f", e.getAmount()),
                    e.getDescription(),
                    e.getCategory().getDisplayName(),
                    e.getTimestamp().format(dtf)
            });
        }
    }

    /**
     * Re-populates the member list and payer dropdown from the manager's data.
     */
    private void updateMemberUI() {
        memberModel.clear();
        payerBox.removeAllItems();
        for (Person p : manager.getMembers()) {
            memberModel.addElement(p.getName());
            payerBox.addItem(p.getName());
        }
    }

    /**
     * Computes the simplified debts and displays them in the settlement area.
     */
    private void simplifyDebts() {
        String result = DebtSimplifier.simplify(manager.getMembers());
        settlementArea.setText(result);
        updateBalances();
        AppLogger.info("Simplified debts.");
    }

    /**
     * Saves the application data to the file system.
     */
    private void saveData() {
        try {
            manager.saveData(DATA_FILE);
            JOptionPane.showMessageDialog(this, "Data saved successfully.");
            AppLogger.info("Data saved to " + DATA_FILE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to save data: " + ex.getMessage());
            AppLogger.error("Failed to save data: " + ex.getMessage());
        }
    }

    /**
     * Loads the application data from the file system.
     */
    private void loadData() {
        try {
            manager.loadData(DATA_FILE);
            updateMemberUI();
            updateBalances();
            updateExpenseTable();
            
            // Adjust the nextId for Expense to avoid conflicts
            int maxId = 0;
            for (Expense e : manager.getExpenses()) {
                if (e.getId() > maxId) {
                    maxId = e.getId();
                }
            }
            Expense.setNextId(maxId + 1);

            JOptionPane.showMessageDialog(this, "Data loaded successfully.");
            AppLogger.info("Data loaded from " + DATA_FILE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load data: " + ex.getMessage());
            AppLogger.error("Failed to load data: " + ex.getMessage());
        }
    }

    /**
     * Main entry point for the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
