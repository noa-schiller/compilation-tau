import java.io.FileReader;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;

public class Main {
    static public void main(String[] argv) {
        String inputFilename = argv[0];
        String outputFilename = argv[1];

        try {
            // [1] Initialize a file reader
            FileReader file_reader = new FileReader(inputFilename);

            // [2] Initialize a file writer
            PrintWriter file_writer = new PrintWriter(outputFilename);

            // [3] Initialize a new lexer
            Lexer l = new Lexer(file_reader);

            // [4] Read next token
            Symbol s = l.next_token();

            // [5] Main reading tokens loop
            while (s.sym != TokenNames.EOF.ordinal()) {
                // [6] Print to console
                printToken(new PrintWriter(System.out, true), s, l.getLine(), l.getTokenStartPosition());

                // [7] Print to file
                printToken(file_writer, s, l.getLine(), l.getTokenStartPosition());

                // [8] Read next token
                s = l.next_token();
            }

            // [9] Close lexer input file
            l.yyclose();

            // [10] Close output file
            file_writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printToken(PrintWriter writer, Symbol s, int line, int col) {
        TokenNames tokenName = TokenNames.fromOrdinal(s.sym);
        writer.print(tokenName);
        if (s.value != null) {
            writer.print("(");
            writer.print(s.value);
            writer.print(")");
        }
        writer.print("[");
        writer.print(line);
        writer.print(",");
        writer.print(col);
        writer.print("]");
        writer.println();
    }
}
