import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class MapReaderTest {

	private static final String TEST_FILE = "test_map.txt";

    void createTestFile(String content) throws IOException {
        FileWriter writer = new FileWriter(new File(TEST_FILE));
        writer.write(content);
        writer.close();
    }

    @Test
    void testValidMapParsing() throws Exception {
        String mapData = "3 3 1\n" +
                         "@.@\n" +
                         ".W.\n" +
                         "@.@\n";
        createTestFile(mapData);

        Tile[][][] result = MapReader.readMap(TEST_FILE);
        assertNotNull(result, "Map should be successfully parsed.");
        assertEquals('W', result[0][1][1].getCharacter(), "Start position should be 'W'.");
    }

    @Test
    void testIncorrectMapFormatException() {
        String mapData = "3, 3, 1\n" +  // Incorrect format (commas instead of spaces)
                         "@.@\n" +
                         ".W.\n" +
                         "@.@\n";
        assertThrows(IncorrectMapFormatException.class, () -> {
            createTestFile(mapData);
            MapReader.readMap(TEST_FILE);
        }, "Should throw IncorrectMapFormatException for invalid format.");
    }

    @Test
    void testIllegalMapCharacterException() {
        String mapData = "2 2 1\n" +
                         "A$\n" +  // 'A' is not a valid map character
                         ".W\n";
        assertThrows(IllegalMapCharacterException.class, () -> {
            createTestFile(mapData);
            MapReader.readMap(TEST_FILE);
        }, "Should throw IllegalMapCharacterException for illegal characters.");
    }

    @Test
    void testIncompleteMapException() {
        String mapData = "2 3 1\n" +
                         "@W@\n" + // One row is missing
                         "@$@\n";
        assertThrows(IncompleteMapException.class, () -> {
            createTestFile(mapData);
            MapReader.readMap(TEST_FILE);
        }, "Should throw IncompleteMapException for missing rows.");
    }

    @Test
    void testStartPosition() throws Exception {
        String mapData = "3 3 1\n" +
                         "@.@\n" +
                         ".W.\n" +
                         "@.@\n";
        createTestFile(mapData);

        Tile[][][] result = MapReader.readMap(TEST_FILE);
        Tile start = MapReader.startPosition(result, 0);
        assertNotNull(start, "Start position should not be null.");
        assertEquals('W', start.getCharacter(), "Start tile should be 'W'.");
    }
}
