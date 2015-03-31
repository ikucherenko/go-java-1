package ua.goit.alg.xmlparser.tests;


import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import ua.goit.alg.xmlparser.parser.XMLParser;

public class TestParser {

  @Test
  public void testSimple() throws IOException{
    XMLParser parser = new XMLParser();
    String result = parser.parse("<?xml doctype=1><start atr1=3><tag>text</tag><tag2/></start>");
    String expectedResult = "<start><tag></tag><tag2></tag2></start>";
    Assert.assertEquals(expectedResult, result);
   
 
  }
}
  