package Tests;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import src.Main.Main;
import src.Parser.Parser;
import src.lexer.Lexer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class TestExamples {

    private static final Path EXAMPLES = Path.of("examples");

    @TestFactory
    Collection<DynamicTest> examplesCompile() throws IOException {
        final List<Path> examples;
        try (Stream<Path> files = Files.list(EXAMPLES)) {
            examples = files
                    .filter(Files::isRegularFile)
                    .filter(file -> file.getFileName().toString().endsWith(".z"))
                    .sorted()
                    .toList();
        }

        assertFalse(examples.isEmpty(), "no .z files found in " + EXAMPLES);

        return examples.stream()
                .map(example -> dynamicTest(example.getFileName().toString(), () -> {
                    try (InputStream input = Files.newInputStream(example)) {
                        final Parser parser = new Parser(new Lexer(input));
                        assertNotNull(Main.generateModule(parser, example),
                                () -> "failed to compile " + example);
                    }
                }))
                .toList();
    }
}
