package ris58h.antlrkit.gradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import ris58h.antlrkit.generator.Generator;
import ris58h.antlrkit.grammar.Grammar;
import ris58h.antlrkit.grammar.parser.GrammarParser;

import java.io.IOException;

public class AntlrKitTask extends DefaultTask {
    //TODO
    private String parserGrammarFileName = "/Users/ris/projects/WebCalm/src/main/antlr/JavaScriptParser.g4";

    @TaskAction
    public void execute() throws IOException {
        Grammar grammar = GrammarParser.parse(parserGrammarFileName);
        if (grammar.type == Grammar.Type.LEXER) {
            throw new RuntimeException("Input grammar has LEXER type. Expected types are PARSER or COMBINED.");
        }
        if (grammar.hasErrors) {
            throw new RuntimeException("Grammar errors in " + parserGrammarFileName);
        }
        new Generator().generate(grammar);
    }
}
