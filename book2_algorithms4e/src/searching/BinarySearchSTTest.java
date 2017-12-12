package searching;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchSTTest {

BinarySearchST<String, String> testST;
    
    @Before
    public void createST(){
        testST=new BinarySearchST<String, String>(5);
        testST.put("key1", "val1");
        testST.put("key2", "val2");
        testST.put("key3", "val3");
    }
    
    @Test
    public void testIsEmpty(){
        assertFalse(testST.isEmpty());
    }
    
    @Test
    public void testContains(){
        assertTrue(testST.contains("key2"));
    }
    
    @Test
    public void testSize(){
        assertEquals(3,testST.size());
    }
    
    @Test
    public void testRank(){
        assertEquals(2,testST.rank("key3"));
    }
    
    @Test
    public void testGetValue(){
        assertEquals("val2",testST.getValue("key2"));
    }

}
