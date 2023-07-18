package ris58h.antlrkit.generator;

import ris58h.antlrkit.grammar.GrammarInfo;
import ris58h.antlrkit.grammar.RuleInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.function.Consumer;

public class Generator {
    //TODO
    private String outputDir = "/Users/ris/projects/WebCalm/build/generated-src/grammar/";

    //TODO
    private String parserClass = "ris58h.webcalm.javascript.JavaScriptParser";
    private String psiPackage = "ris58h.webcalm.javascript.psi";
    private String psiImplPackage = "ris58h.webcalm.javascript.psi.impl";
    private String psiImplUtilClass = "ris58h.webcalm.javascript.psi.impl.JavaScriptPsiImplUtil";
    private String psiClassPrefix = "JavaScript";
    private String elementTypeHolderClass = "ris58h.webcalm.javascript.psi.JavaScriptTypes";
    private String elementTypeClass = "ris58h.webcalm.javascript.psi.JavaScriptElementType";
    private String tokenTypeClass = "ris58h.webcalm.javascript.psi.JavaScriptTokenType";

    // TODO
    private Set<String> rulesToDrop = Set.of(
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

    public void generate(GrammarInfo grammarInfo) throws IOException {
        generateParser(grammarInfo);
        generateTypes(grammarInfo);
        generatePsiElements(grammarInfo);
    }

    private void generateParser(GrammarInfo grammarInfo) throws IOException {
        generateClass(
                packageName(parserClass),
                simpleName(parserClass),
                false,
                List.of("com.intellij.lang.PsiParser"),
                List.of("PsiParser"),
                List.of(),
                writer -> {
                    //TODO
                }
        );
    }

    private void generateTypes(GrammarInfo grammarInfo) throws IOException {
        generateClass(
                packageName(elementTypeHolderClass),
                simpleName(elementTypeHolderClass),
                true,
                List.of(
                        "com.intellij.psi.tree.IElementType",
                        "com.intellij.psi.PsiElement",
                        "com.intellij.lang.ASTNode",
                        psiImplPackage + ".*"
                ),
                List.of(),
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
        String tokenClassName = simpleName(tokenTypeClass);
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
        String elementClassName = simpleName(elementTypeClass);
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
        String className = simpleName(elementTypeHolderClass);
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
        generateClass(
                psiPackage,
                interfaceName,
                true,
                List.of(
                        "java.util.List",
                        "org.jetbrains.annotations.*",
                        "com.intellij.psi.PsiElement"
                ),
                List.of(),
                extendsList,
                writer -> {
                    // TODO getters
                }
        );

        if (!ruleInfo.isPrototype) {
            generateClass(
                    psiImplPackage,
                    interfaceName + "Impl",
                    false,
                    List.of(
                            "java.util.List",
                            "org.jetbrains.annotations.*",
                            "com.intellij.psi.PsiElement"
                    ),
                    List.of(interfaceName),
                    List.of(),
                    writer -> {
                        //TODO getters
                    }
            );
        }
    }

    private void generateClass(
            String packageName,
            String simpleName,
            boolean isInterface,
            List<String> importList,
            List<String> implementsList,
            List<String> extendsList,
            Consumer<Writer> bodyWriterCallback
    ) throws IOException {
        File outputFile = new File(outputFilePath(packageName, simpleName));
        if (!outputFile.exists()) {
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
        }
        try (Writer writer = new FileWriter(outputFile)) {
            writeClass(packageName, simpleName, isInterface, importList, implementsList, extendsList, writer, bodyWriterCallback);
        }
    }

    private static void writeClass(
            String packageName,
            String simpleName,
            boolean isInterface,
            List<String> importList,
            List<String> implementsList,
            List<String> extendsList,
            Writer writer,
            Consumer<Writer> bodyWriterCallback
    ) throws IOException {
        if (packageName != null && !packageName.isBlank()) {
            writer.write("package " + packageName + ";\n\n");
        }

        for (String anImport : importList) {
            writer.write("import " + anImport + ";\n");
        }
        writer.write("\n");

        writer.write(
                "public " +
                        (isInterface ? "interface " : "class ") +
                        simpleName
        );
        if (!extendsList.isEmpty()) {
            writer.write(" extends " + String.join(", ", extendsList));
        }
        if (!implementsList.isEmpty()) {
            writer.write(" implements " + String.join(", ", implementsList));
        }
        writer.write(" {\n");
        bodyWriterCallback.accept(writer);
        writer.write("}\n");
    }

    private String psiClassName(String ruleName) {
        // TODO: skip _
        char firstChar = ruleName.charAt(0);
        return psiClassPrefix + Character.toUpperCase(firstChar) + ruleName.substring(1);
    }

    private static String simpleName(String fqn) {
        int i = fqn.lastIndexOf('.');
        return i < 0 ? fqn : fqn.substring(i + 1);
    }

    private static String packageName(String fqn) {
        int i = fqn.lastIndexOf('.');
        return i < 0 ? null : fqn.substring(0, i);
    }

    private String outputFilePath(String packageName, String simpleName) {
        String fileName = packageName.replace('.', '/') + '/' + simpleName + ".java";
        // TODO trailing '/'
        return outputDir + fileName;
    }
}
