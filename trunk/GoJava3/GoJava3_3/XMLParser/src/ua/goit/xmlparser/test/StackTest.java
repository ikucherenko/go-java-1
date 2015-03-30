package ua.goit.xmlparser.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.goit.xmlparser.TagStack;

public class StackTest {

    @Test
    public void test() {
        TagStack test = new TagStack();
        
        test.push("111");
        test.push("222");
        test.push("333");
        assertEquals("333",test.pop());
        assertEquals("222",test.get());
        assertEquals("222",test.pop());
        assertEquals("111",test.pop());
        assertEquals(null,test.pop());
        
    }

}
