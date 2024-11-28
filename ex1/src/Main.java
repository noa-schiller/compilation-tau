import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import java_cup.runtime.Symbol;

public class Main {
    static public void main(String[] argv) {
        String inputFilename = argv[0];
        String outputFilename = argv[1];

        try (FileReader file_reader = new FileReader(inputFilename);
                PrintWriter file_writer = new PrintWriter(outputFilename);) {

            StringBuilder output = new StringBuilder();
            Lexer l = new Lexer(file_reader);
            try {
                Symbol s = l.next_token();
                while (s.sym != TokenNames.EOF.ordinal()) {
                    appendToken(System.out, s, l.getLine(), l.getTokenStartPosition());
                    appendToken(output, s, l.getLine(), l.getTokenStartPosition());
                    s = l.next_token();
                }
            } catch (Error e) {
                file_writer.println("ERROR");
                return;
            } finally {
                l.yyclose();
            }
            file_writer.print(output.toString());
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
    }

    private static void appendToken(Appendable a, Symbol s, int line, int col) throws IOException {
        a.append(TokenNames.fromOrdinal(s.sym).toString());
        if (s.value != null) {
            a.append(MessageFormat.format("({0})", s.value));
        }
        a.append(MessageFormat.format("[{0},{1}]\n", line, col));
    }
}
