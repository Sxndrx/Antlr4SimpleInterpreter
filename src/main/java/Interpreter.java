import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

public class Interpreter {
    private static CPU cpu = new CPU();
    private static MyInterpreterVisitor visitor = new MyInterpreterVisitor(cpu);
    private static MyErrorListener myErrorListener = new MyErrorListener();

    public static void interpret(String line) {
        CharStream input = CharStreams.fromString(line);

        try {
            InterpreterLexer lexer = new InterpreterLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(myErrorListener);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            InterpreterParser parser = new InterpreterParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(myErrorListener);
            ParseTree tree = parser.instruction();//
            visitor.visit(tree);
        } catch (ParseCancellationException e) {
            System.out.println(e.getMessage());
        }
    }
}
