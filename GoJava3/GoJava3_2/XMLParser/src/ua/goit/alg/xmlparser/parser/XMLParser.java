package ua.goit.alg.xmlparser.parser;

public class XMLParser {
  
  private StringBuilder result = new StringBuilder("");
  
  private ParserData parserData = null;
  
  public void update(ParserData parserData){
    this.parserData = parserData;
    
  }
  
  public String parse(String string) {
    return "tag";
  }

  public void onOpenTag(Handler handler){
    handler.onOpenTag(parserData);
    
  }
  public void onCloseTag(Handler handler){
    handler.onCloseTag(parserData);
  }
  public void onTextValue(Handler handler){
    handler.onTextValue(parserData);
  }
  
  public void onStart(Handler handler){
    handler.onStart(parserData);
  }
  public void onEnd(Handler handler){
    handler.onEnd(parserData);
  }
  public void onError(Handler handler){
    handler.onError(parserData);
  }
}