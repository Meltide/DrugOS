package bin;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class Calc {
    public Calc() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the formula to be calculated (Type 'exit' to exit)\n> ");
            String formula = in.nextLine();

            if (!formula.isEmpty()) {
                if ("exit".equalsIgnoreCase(formula)) return;

                try {
                    Expression exp = new ExpressionBuilder(formula).build();
                    double result = exp.evaluate();
                    System.out.println("Result: " + result);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
