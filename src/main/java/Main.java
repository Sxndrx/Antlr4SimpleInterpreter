import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (System.console() != null) {
            runPrompt(in);
        } else {
            runScript(in);
        }
    }

    public static void runPrompt(Scanner in) {
        String line;
        System.out.print(">>>");
        while (in.hasNextLine()) {
            line = in.nextLine();
            Interpreter.interpret(line);
            System.out.print(">>>");
        }
    }

    public static void runScript(Scanner in) {
        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            Interpreter.interpret(line);
        }
    }

}
