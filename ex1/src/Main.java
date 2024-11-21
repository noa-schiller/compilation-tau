import java_cup.runtime.Symbol;

import java.io.*;
import java.io.PrintWriter;

public class Main {
    static public void main(String[] argv) {
        String inputFilename = argv[0];
        String outputFilename = argv[1];

        try {
            // [1] Initialize a file reader
            var file_reader = new FileReader(inputFilename);

            // [2] Initialize a file writer
            var file_writer = new PrintWriter(outputFilename);

            // [3] Initialize a new lexer
            var l = new Lexer(file_reader);

            // [4] Read next token
            var s = l.next_token();

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

    private static void printToken(PrintWriter stream, Symbol s, int line, int position) {
        TokenNames tokenName = TokenNames.fromOrdinal(s.sym);
        stream.print(tokenName);
        if (s.value != null) {
            stream.print("(");
            stream.print(s.value);
            stream.print(")");
        }
        stream.print("[");
        stream.print(line);
        stream.print(",");
        stream.print(position);
        stream.print("]");
        stream.println();
    }
}
