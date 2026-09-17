package model;

import java.util.*;

/**
 * Utility class to simplify debts among a list of people.
 */
public class DebtSimplifier {

    /**
     * Computes and prints the simplified settlements to the console.
     *
     * @param members the list of members
     */
    public static void simplifyDebts(ArrayList<Person> members) {
        System.out.println("\nSimplified Settlements:");
        System.out.print(simplify(members));
    }

    /**
     * Computes the simplified settlements and returns them as a string.
     *
     * @param members the list of members
     * @return a formatted string of the settlements
     */
    public static String simplify(ArrayList<Person> members) {

        ArrayList<Person> creditors = new ArrayList<>();
        ArrayList<Person> debtors = new ArrayList<>();

        for (Person p : members) {
            if (p.getBalance() > 0.01)
                creditors.add(p);
            else if (p.getBalance() < -0.01)
                debtors.add(p);
        }

        int i = 0;
        int j = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("Simplified Settlements:\n");

        while (i < debtors.size() && j < creditors.size()) {

            Person debtor = debtors.get(i);
            Person creditor = creditors.get(j);

            double debt = -debtor.getBalance();
            double credit = creditor.getBalance();

            double settle = Math.min(debt, credit);

            sb.append(debtor.getName())
              .append(" pays ₹")
              .append(String.format("%.2f", settle))
              .append(" to ")
              .append(creditor.getName())
              .append("\n");

            debtor.addBalance(settle);
            creditor.addBalance(-settle);

            if (Math.abs(debtor.getBalance()) < 0.01)
                i++;

            if (Math.abs(creditor.getBalance()) < 0.01)
                j++;
        }

        if (sb.toString().equals("Simplified Settlements:\n")) {
            sb.append("All balances are settled!");
        }

        return sb.toString();
    }
}