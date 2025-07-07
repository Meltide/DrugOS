package bin;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import static org.fusesource.jansi.Ansi.ansi;

import sys.*;
import java.util.Scanner;

public class Calc {
    public Calc() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the formula to be calculated " + ansi().a("\u001B[2m(Type 'exit' to exit)\u001B[0m") + "\n> ");
            String formula = in.nextLine();

            if (!formula.isEmpty()) {
                if ("exit".equalsIgnoreCase(formula)) return;

                try {
                    Expression exp = new ExpressionBuilder(formula).build();
                    double result = exp.evaluate();
                    System.out.println("Result: " + ansi().fgBlue().a(result).reset());
                } catch (Exception e) {
                    System.err.println("Error: " + ansi().fgRed().a(e.getMessage()).reset());
                    DrugOS.errorCode = ErrorManager.getErrorCode(e);
                }
            }
        }
    }
}
