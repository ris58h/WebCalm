package ris58h.antlrkit.gradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import ris58h.antlrkit.generator.Generator;
import ris58h.antlrkit.grammar.GrammarInfo;
import ris58h.antlrkit.grammar.parser.GrammarParser;

import java.io.IOException;

public class AntlrKitTask extends DefaultTask {
    //TODO
    private String parserGrammarFileName = "/Users/ris/projects/WebCalm/src/main/antlr/JavaScriptParser.g4";

    @TaskAction
    public void execute() throws IOException {
        GrammarInfo grammarInfo = GrammarParser.parse(parserGrammarFileName);
        if (grammarInfo.type == GrammarInfo.GrammarType.LEXER) {
            throw new RuntimeException("Input grammar has LEXER type. Expected types are PARSER or COMBINED.");
        }
        if (grammarInfo.hasErrors) {
            throw new RuntimeException("Grammar errors in " + parserGrammarFileName);
        }
        new Generator().generate(grammarInfo);
    }
}