import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import org.junit.jupiter.api.Test;

class QueueTest {

	private Tile[][][] tileMap;
    private Queue<Tile> queue;
    private Set<Tile> visited;

    @BeforeEach
    void setUp() {
        // Mock Tile 3D array (3x3 grid with 1 room)
        tileMap = new Tile[3][3][1];

        // Fill the map with empty paths (.)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tileMap[i][j][0] = new Tile('.', i, j, 0);
            }
        }

        // Place a wall and a goal
        tileMap[1][1][0] = new Tile('@', 1, 1, 0); // Wall
        tileMap[2][2][0] = new Tile('$', 2, 2, 0); // Goal

        queue = new LinkedList<>();
        visited = new HashSet<>();
    }

    @Test
    void testQuadSearchEnqueuesValidTiles() {
        Tile start = tileMap[0][0][0]; // Starting position
        p2.quadSearch(tileMap, start, queue, visited);

        assertEquals(2, queue.size(), "Queue should contain 2 valid moves from (0,0)");
        assertTrue(queue.contains(tileMap[1][0][0]), "Should contain (1,0)");
        assertTrue(queue.contains(tileMap[0][1][0]), "Should contain (0,1)");
    }

    @Test
    void testQuadSearchAvoidsWalls() {
        Tile start = tileMap[0][1][0]; // Adjacent to the wall
        p2.quadSearch(tileMap, start, queue, visited);

        assertFalse(queue.contains(tileMap[1][1][0]), "Wall at (1,1) should not be enqueued");
    }

    @Test
    void testQueueSearchFindsGoal() {
        queue.add(tileMap[0][0][0]);
        visited.add(tileMap[0][0][0]);

        p2.queueSearch(tileMap, 0);

        assertEquals('$', tileMap[2][2][0].getCharacter(), "Goal should be found at (2,2)");
    }

    @Test
    void testQueueSearchHandlesNoPath() {
        tileMap[1][0][0] = new Tile('@', 1, 0, 0); // Block paths
        tileMap[0][1][0] = new Tile('@', 0, 1, 0);

        queue.add(tileMap[0][0][0]);
        visited.add(tileMap[0][0][0]);

        p2.queueSearch(tileMap, 0);

        assertEquals(0, queue.size(), "Queue should be empty when no path exists");
    }

    @Test
    void testWPositionInitializesQueueCorrectly() {
        p2.wPosition(tileMap, queue, visited, 0);

        assertEquals(1, queue.size(), "Queue should contain the starting position");
        assertEquals(tileMap[0][0][0], queue.peek(), "First element should be the start position");
    }

}
