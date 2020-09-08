import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 *
 * @author  Aronson
 */
public class TestMain implements Directions
{
    public static void main(String args[]) {
        TestMain test = new TestMain();
        test.setup();
        test.test1_faceEast();
        test.test2_isLeftBlocked();
        test.test3_findNextDirection();
        test.test4_isWayBlocked();
        test.test5_carpet();

    }

    @Before
    public void setup() {
        Map.getInstance().loadMap("maps/ifLabs.jev");
    }

    @Test
    public void test1_faceEast()
    {   
        Jeroo j1 = new Jeroo(1, 4, EAST, 100);
        j1.faceEast();
        assertTrue("started east, should finish east", j1.isFacing(EAST));

        Jeroo j2 = new Jeroo(1, 5, SOUTH, 100);
        j2.faceEast();
        assertTrue("started south, should finish east", j2.isFacing(EAST));

        Jeroo j3 = new Jeroo(1, 6, WEST, 100);
        j3.faceEast();
        assertTrue("started west, should finish east", j3.isFacing(EAST));

        Jeroo j4 = new Jeroo(1, 7, NORTH, 100);
        j4.faceEast();
        assertTrue("started north, should finish east", j4.isFacing(EAST));

    }

    @Test
    public void test2_isLeftBlocked()
    {   

        // Case 1: robot is in situation where it is not blocked on left
        Jeroo kim = new Jeroo(3, 4, NORTH, 100);
        if (kim.isLeftBlocked())  assert(false);
        assertTrue("should be facing North", kim.isFacing(NORTH));
        assertEquals("should be back to (3, 4)", 3, kim.getY());
        assertEquals("should be back to (3, 4)", 4, kim.getX());

        // Case 1: robot is in situation where it is  blocked on left
        Jeroo kim2 = new Jeroo(4, 4, NORTH, 100);
        if (!kim2.isLeftBlocked())  assert(false);
        assertTrue("should be facing North", kim2.isFacing(NORTH));
        assertEquals("should be back to (4, 4)", 4, kim2.getY());
        assertEquals("should be back to (4, 4)", 4, kim2.getX());

        // Case 3: robot is in situation where it is blocked one space away on left
        Jeroo kim3 = new Jeroo(5, 4, NORTH, 100);
        if (!kim3.isLeftBlocked())  assert(false);
        assertTrue("should be facing North", kim3.isFacing(NORTH));
        assertEquals("should be back to (5, 4)", 5, kim3.getY());
        assertEquals("should be back to (5, 4)", 4, kim3.getX());

    }

    @Test
    public void test3_findNextDirection()
    {   
        //  No flower in front
        Jeroo kim1 = new Jeroo(9, 1, NORTH, 0);
        kim1.findNextDirection();
        assertTrue("no plants in front means turn left", kim1.isFacing(WEST));
        assertEquals("should be back to (9, 1)", 9, kim1.getY());
        assertEquals("should be back to (9 1)", 1, kim1.getX());

        // One flower in front
        Jeroo kim2 = new Jeroo(9, 3, NORTH, 0);
        kim2.findNextDirection();
        assertTrue("one plant in front means turn left", kim2.isFacing(WEST));
        assertEquals("should be back to (9, 3)", 9, kim2.getY());
        assertEquals("should be back to (9, 3)", 3, kim2.getX());

        // One flower in front
        Jeroo kim3 = new Jeroo(9, 5, NORTH, 0);
        kim3.findNextDirection();
        assertTrue("two plants in front means turn right", kim3.isFacing(EAST));
        assertEquals("should be back to (9, 5)", 9, kim3.getY());
        assertEquals("should be back to (9, 5)", 5, kim3.getX());
        assertTrue("should be no flower at (8, 5)", !Map.getInstance().isFlower(8,5));
        assertTrue("should be no flower at (7, 5)", !Map.getInstance().isFlower(7,5));

    }

    @Test
    public void test4_isWayBlocked()
    {   
        Jeroo kim = new Jeroo(11, 1, NORTH, 100);
        assertTrue(!kim.isWayBlocked());
        assertTrue(kim.isFacing(NORTH));
        assertEquals(11, kim.getY());
        assertEquals(1, kim.getX());

        kim = new Jeroo(11, 4, NORTH, 100);
        assertTrue(!kim.isWayBlocked());
        assertTrue(kim.isFacing(NORTH));
        assertEquals(11, kim.getY());
        assertEquals(4, kim.getX());

        kim = new Jeroo(11, 7, NORTH, 100);
        assertTrue(!kim.isWayBlocked());
        assertTrue(kim.isFacing(NORTH));
        assertEquals(11, kim.getY());
        assertEquals(7, kim.getX());

        kim = new Jeroo(11, 10, NORTH, 100);
        assertTrue(!kim.isWayBlocked());
        assertTrue(kim.isFacing(NORTH));
        assertEquals(11, kim.getY());
        assertEquals(10, kim.getX());

        kim = new Jeroo(11, 13, NORTH, 100);
        assertTrue(!kim.isWayBlocked());
        assertTrue(kim.isFacing(NORTH));
        assertEquals(11, kim.getY());
        assertEquals(13, kim.getX());

        kim = new Jeroo(11, 16, NORTH, 100);
        assertTrue(!kim.isWayBlocked());
        assertTrue(kim.isFacing(NORTH));
        assertEquals(11, kim.getY());
        assertEquals(16, kim.getX());

        kim = new Jeroo(11, 19, NORTH, 100);
        assertTrue(kim.isWayBlocked());
        assertTrue(kim.isFacing(NORTH));
        assertEquals(11, kim.getY());
        assertEquals(19, kim.getX());

        kim = new Jeroo(11, 22, NORTH, 100);
        assertTrue(kim.isWayBlocked());
        assertTrue(kim.isFacing(SOUTH));
        assertEquals(11, kim.getY());
        assertEquals(22, kim.getX());

    }

    @Test
    public void test5_carpet()
    {   
        Jeroo kim = new Jeroo(16, 0, EAST, 100);
        kim.carpetRoom();
        assertTrue("should be no flower at (16, 2)", !Map.getInstance().isFlower(16,2));
        assertTrue("should be no flower at (16, 14)", !Map.getInstance().isFlower(16,14));
        //    assertEquals("should be at right of world at (16, 23)", 16, kim.getY());
        //    assertEquals("should be back to (16, 23)", 23, kim.getX());

    }

}