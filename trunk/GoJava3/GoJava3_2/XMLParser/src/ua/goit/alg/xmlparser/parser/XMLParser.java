package ua.goit.alg.xmlparser.parser;


import java.io.File;
import java.io.IOException;
import ua.goit.alg.xmlparser.input.InputStream;
import ua.goit.alg.xmlparser.statemashines.StateMashineTag;

public class XMLParser {
  
  private StringBuilder result = new StringBuilder("");
  
  private ParserData parserData = null;
  
  private StateMashineTag tag = new StateMashineTag(this);
  
  public void update(ParserData parserData){
    this.parserData = parserData;
  }
  
  public String parse(String string) throws IOException {
    InputStream stream = new InputStream(string);
    return parseInputStream(stream);
  }
    
  public String parse(File file) throws IOException {
    InputStream stream = new InputStream(file);
      return parseInputStream(stream);

  }

  public String parseInputStream(InputStream stream) throws IOException {  
   
    int symbol;
    do{
      symbol = stream.read();
      tag.next((char)symbol);
    } while (symbol !=-1);
    return result.toString();
}
  
  public void onOpenTag(Handler handler){
    handler.onOpenTag(parserData);
    result.append(parserData.getText());
    result.append("<").append(parserData.getTag()).append(">");
  }
  public void onCloseTag(Handler handler){
    handler.onCloseTag(parserData);
    result.append(parserData.getText());
    result.append("<").append(parserData.getTag()).append("/>");
  }
  public void onTextValue(Handler handler){
    handler.onTextValue(parserData);
    result.append(parserData.getText());
    result.append("<").append(parserData.getTag()).append(">");
  }
  
  public void onStart(Handler handler){
    handler.onStart(parserData);
    result.append(parserData.getText());
    result.append("<").append(parserData.getTag()).append(">");
  }
  public void onEnd(Handler handler){
    handler.onEnd(parserData);
    result.append(parserData.getText());
    result.append("<").append(parserData.getTag()).append(">");
  }
  public void onError(Handler handler){
    handler.onError(parserData);
    result.append("<").append(parserData.getTag()).append(">");
  }
}
