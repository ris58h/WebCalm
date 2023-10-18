package ris58h.antlrkit.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

public class JavaFileGenerator {
    private final String outputDir;

    public JavaFileGenerator(String outputDir) {
        this.outputDir = outputDir;
    }

    public void generateInterface(
            String fqn,
            List<String> importList,
            List<String> extendsList,
            IOCallback<Writer> bodyWriterCallback
    ) throws IOException {
        generateInterface(packageName(fqn), simpleName(fqn), importList, extendsList, bodyWriterCallback);
    }

    public void generateInterface(
            String packageName,
            String simpleName,
            List<String> importList,
            List<String> extendsList,
            IOCallback<Writer> bodyWriterCallback
    ) throws IOException {
        generateFile(packageName, simpleName, "interface", importList, List.of(), extendsList, bodyWriterCallback);
    }

    public void generateClass(
            String fqn,
            List<String> importList,
            String superClass,
            List<String> implementsList,
            IOCallback<Writer> bodyWriterCallback
    ) throws IOException {
        generateClass(packageName(fqn), simpleName(fqn), importList, superClass, implementsList, bodyWriterCallback);
    }

    public void generateClass(
            String packageName,
            String simpleName,
            List<String> importList,
            String superClass,
            List<String> implementsList,
            IOCallback<Writer> bodyWriterCallback
    ) throws IOException {
        List<String> extendsList = superClass == null ? List.of() : List.of(superClass);
        generateFile(packageName, simpleName, "class", importList, implementsList, extendsList, bodyWriterCallback);
    }

    private void generateFile(
            String packageName,
            String simpleName,
            String type,
            List<String> importList,
            List<String> implementsList,
            List<String> extendsList,
            IOCallback<Writer> bodyWriterCallback
    ) throws IOException {
        File outputFile = new File(outputFilePath(packageName, simpleName));
        if (!outputFile.exists()) {
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
        }
        try (Writer writer = new FileWriter(outputFile)) {
            writeFile(packageName, simpleName, type, importList, implementsList, extendsList, writer, bodyWriterCallback);
        }
    }

    private static void writeFile(
            String packageName,
            String simpleName,
            String type,
            List<String> importList,
            List<String> implementsList,
            List<String> extendsList,
            Writer writer,
            IOCallback<Writer> bodyWriterCallback
    ) throws IOException {
        if (packageName != null && !packageName.isBlank()) {
            writer.write("package " + packageName + ";\n\n");
        }

        for (String classToImport : importList) {
            String packageToImport = packageName(classToImport);
            if (!Objects.equals(packageName, packageToImport)) {
                writer.write("import " + classToImport + ";\n");
            }
        }
        writer.write("\n");

        writer.write("public " + type + ' ' + simpleName);
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

    public static String simpleName(String fqn) {
        int i = fqn.lastIndexOf('.');
        return i < 0 ? fqn : fqn.substring(i + 1);
    }

    public static String packageName(String fqn) {
        int i = fqn.lastIndexOf('.');
        return i < 0 ? null : fqn.substring(0, i);
    }

    private String outputFilePath(String packageName, String simpleName) {
        String fileName;
        if (packageName == null) {
            fileName = simpleName + ".java";
        } else {
            fileName = packageName.replace('.', '/') + '/' + simpleName + ".java";
        }
        if (outputDir.endsWith("/")) {
            return outputDir + fileName;
        } else {
            return outputDir + '/' + fileName;
        }
    }
}
