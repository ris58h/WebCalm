package ris58h.antlrkit.generator;

import ris58h.antlrkit.grammar.Grammar;
import ris58h.antlrkit.generator.GrammarInfo;
import ris58h.antlrkit.generator.RuleInfo;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Generator {
    //TODO
    private final String outputDir = "/Users/ris/projects/WebCalm/build/generated-src/grammar/";

    //TODO
    private final String parserClass = "ris58h.webcalm.javascript.JavaScriptParser";
    private final String psiPackage = "ris58h.webcalm.javascript.psi";
    private final String psiImplPackage = "ris58h.webcalm.javascript.psi.impl";
    private final String psiImplUtilClass = "ris58h.webcalm.javascript.psi.impl.JavaScriptPsiImplUtil";
    private final String psiClassPrefix = "JavaScript";
    private final String elementTypeHolderClass = "ris58h.webcalm.javascript.psi.JavaScriptTypes";
    private final String elementTypeClass = "ris58h.webcalm.javascript.psi.JavaScriptElementType";
    private final String tokenTypeClass = "ris58h.webcalm.javascript.psi.JavaScriptTokenType";

    // TODO
    private final Set<String> rulesToDrop = Set.of(
            "program",
            "sourceElements",
            "sourceElement",
            "statementList",
            "statement",
            "eos",
            "declaration",
            "assignable",
            "elementList",
            "arrayElement",
            "templateStringAtom",
            "arrowFunctionParameters",
            "arrowFunctionBody",
            "assignmentOperator",
            "classTail",
            "getter",
            "setter",
            "caseClauses"
    );

    private final JavaFileGenerator javaFileGenerator = new JavaFileGenerator(outputDir);

    public void generate(Grammar grammar) throws IOException {
        GrammarInfo grammarInfo = GrammarInfo.from(grammar);
        generateParser(grammarInfo);
        generateTypes(grammarInfo);
        generatePsiElements(grammarInfo);
    }

    private void generateParser(GrammarInfo grammarInfo) throws IOException {
        javaFileGenerator.generateClass(
                parserClass,
                List.of("com.intellij.lang.PsiParser"),
                null,
                List.of("PsiParser"),
                writer -> {
                    //TODO
                }
        );
    }

    private void generateTypes(GrammarInfo grammarInfo) throws IOException {
        javaFileGenerator.generateInterface(
                elementTypeHolderClass,
                List.of(
                        "com.intellij.psi.tree.IElementType",
                        "com.intellij.psi.PsiElement",
                        "com.intellij.lang.ASTNode",
                        psiImplPackage + ".*",
                        elementTypeClass
                ),
                List.of(),
                writer -> {
                    try {
                        writeTypesBody(grammarInfo, writer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    private void writeTypesBody(GrammarInfo grammarInfo, Writer writer) throws IOException {
        // Tokens
        String tokenClassName = JavaFileGenerator.simpleName(tokenTypeClass);
        for (String tokenName : grammarInfo.tokenNames) {
            writer.write("    IElementType " + tokenName + " = new " + tokenClassName + "(\"" + tokenName + "\");\n");
        }
        if (!grammarInfo.implicitTokens.isEmpty()) {
            // TODO: log warning
            // TODO: could we use *.tokens file after 'generateGrammarSource' task to retrieve implicit token names?
            writer.write(
                    "    // Can't generate elements for the following implicit tokens.\n" +
                            "    // Consider to create them manually or use corresponding token refs in the grammar.\n"
            );
            for (String implicitToken : grammarInfo.implicitTokens) {
                writer.write("    // " + implicitToken + "\n");
            }
        }
        writer.write("\n");

        // Rules
        List<RuleInfo> ruleInfos = grammarInfo.ruleInfos;
        String elementClassName = JavaFileGenerator.simpleName(elementTypeClass);
        for (RuleInfo ruleInfo : ruleInfos) {
            String ruleName = ruleInfo.name;
            if (!rulesToDrop.contains(ruleName)) {
                writer.write("    IElementType " + ruleName + " = new " + elementClassName + "(\"" + ruleName + "\");\n");
            }
        }
        writer.write("\n");

        // Factory
        writer.write("    class Factory {\n");
        writer.write("        public static PsiElement createElement(ASTNode node) {\n");
        writer.write("            IElementType type = node.getElementType();\n");
        String className = JavaFileGenerator.simpleName(elementTypeHolderClass);
        for (RuleInfo ruleInfo : ruleInfos) {
            String ruleName = ruleInfo.name;
            if (!rulesToDrop.contains(ruleName)) {
                String implName = psiClassName(ruleName) + "Impl";
                writer.write("            if (type == " + className + "." + ruleName + ") {\n");
                writer.write("                return new " + implName + "(node);\n");
                writer.write("            }\n");
            }
        }
        writer.write("            throw new AssertionError(\"Unknown element type: \" + type);\n");
        writer.write("        }\n");
        writer.write("    }\n");
    }

    private void generatePsiElements(GrammarInfo grammarInfo) throws IOException {
        List<RuleInfo> ruleInfos = grammarInfo.ruleInfos;
        for (RuleInfo ruleInfo : ruleInfos) {
            if (!rulesToDrop.contains(ruleInfo.name)) {
                generatePsiElements(ruleInfo);
            }
        }
    }

    private void generatePsiElements(RuleInfo ruleInfo) throws IOException {
        String interfaceName = psiClassName(ruleInfo.name);
        List<String> extendsList = new ArrayList<>();
        extendsList.add("PsiElement");
        if (ruleInfo.prototypeName != null) {
            extendsList.add(psiClassName(ruleInfo.prototypeName));
        }
        javaFileGenerator.generateInterface(
                psiPackage,
                interfaceName,
                List.of(
                        "java.util.List",
                        "org.jetbrains.annotations.*",
                        "com.intellij.psi.PsiElement"
                ),
                extendsList,
                writer -> {
                    // TODO getters
                }
        );

        if (!ruleInfo.isPrototype) {
            String simpleName = interfaceName + "Impl";
            javaFileGenerator.generateClass(
                    psiImplPackage,
                    simpleName,
                    List.of(
                            "java.util.List",
                            "org.jetbrains.annotations.*",
                            "com.intellij.psi.PsiElement",
                            "com.intellij.extapi.psi.ASTWrapperPsiElement"
                    ),
                    "ASTWrapperPsiElement",
                    List.of(interfaceName),
                    writer -> {
                        try {
                            writer.write("    public " + simpleName + "(@NotNull ASTNode node) {\n");
                            writer.write("        super(node);\n");
                            writer.write("    }\n");
                            //TODO getters
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    private String psiClassName(String ruleName) {
        // TODO: skip _
        char firstChar = ruleName.charAt(0);
        return psiClassPrefix + Character.toUpperCase(firstChar) + ruleName.substring(1);
    }
}
