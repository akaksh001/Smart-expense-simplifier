import java.util.Scanner;
import model.*;

/**
 * Main application class to run the Smart Expense Splitter.
 */
public class Main {

    /**
     * Entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExpenseManager manager = new ExpenseManager();

        System.out.println("===== SMART EXPENSE SPLITTER =====");

        System.out.print("Enter number of members: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Member " + i + " Name: ");
            manager.addMember(sc.nextLine());
        }

        System.out.print("\nEnter number of expenses: ");
        int expCount = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= expCount; i++) {

            System.out.println("\nExpense " + i);

            System.out.print("Paid By: ");
            String payer = sc.nextLine();

            System.out.print("Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            System.out.print("Description: ");
            String desc = sc.nextLine();

            manager.addExpense(payer, amount, desc);
        }

        manager.showBalances();

        DebtSimplifier.simplifyDebts(
                manager.getMembers());

        sc.close();
    }
}