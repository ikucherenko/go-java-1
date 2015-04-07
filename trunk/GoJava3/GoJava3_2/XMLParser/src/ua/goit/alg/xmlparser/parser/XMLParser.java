package ua.goit.alg.xmlparser.parser;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import ua.goit.alg.xmlparser.input.StreamReader;
import ua.goit.alg.xmlparser.statemashines.StateMashineTag;

public class XMLParser {

  private Handler openTagHandler;
  private Handler closeTagHandler;
  private Handler textValueHandler;
  private Handler startHandler;
  private Handler endHandler;
  private Handler errHandler;
  private XMLParserData xmlParserData;

  public static class Builder {

    private Handler openTagHandler;
    private Handler closeTagHandler;
    private Handler textValueHandler;
    private Handler startHandler;
    private Handler endHandler;
    private Handler errHandler;

    public Builder setOpenTagHandler(Handler openTagHandler) {
      this.openTagHandler = openTagHandler;
      return this;
    }

    public Builder setCloseTagHandler(Handler closeTagHandler) {
      this.closeTagHandler = closeTagHandler;
      return this;
    }

    public Builder setTextValueHandler(Handler textValueHandler) {
      this.textValueHandler = textValueHandler;
      return this;
    }

    public Builder setStartHandler(Handler startHandler) {
      this.startHandler = startHandler;
      return this;
    }

    public Builder setEndHandler(Handler endHandler) {
      this.endHandler = endHandler;
      return this;
    }

    public Builder setErrHandler(Handler errHandler) {
      this.errHandler = errHandler;
      return this;
    }

    public XMLParser build() {
      return new XMLParser(this);
    }
  }

  public XMLParser() {
  }

  private XMLParser(Builder builder) {
    openTagHandler = builder.openTagHandler;
    closeTagHandler = builder.closeTagHandler;
    textValueHandler = builder.textValueHandler;
    startHandler = builder.startHandler;
    endHandler = builder.endHandler;
    errHandler = builder.errHandler;
    xmlParserData = new XMLParserData(this);
  }
  
  public XMLParserData getXmlParserData(){
    return xmlParserData;
  }

  public void onOpenTag(ParserData parserData) {
    if (openTagHandler != null) {
      parserData.putTagInStack();
      openTagHandler.handle(parserData);
    }
    parserData.clear();
  }

  public void onCloseTag(ParserData parserData) {
    if (closeTagHandler != null) {
      closeTagHandler.handle(parserData);
    }
    if (!parserData.getStackElement().equals(parserData.getTag())) {
      onError(parserData);
    }
    parserData.clear();
  }

  public void onTextValue(ParserData parserData) {
    if (textValueHandler != null) {
      textValueHandler.handle(parserData);
    }
    parserData.setText("");
  }

  public void onStart(ParserData parserData) {
    if (startHandler != null) {
      startHandler.handle(parserData);
    }
    parserData.clear();
  }

  public void onEnd(ParserData parserData) {
    if (endHandler != null) {
      endHandler.handle(parserData);
    }
  }

  public void onError(ParserData parserData) {
    if (errHandler != null) {
      errHandler.handle(parserData);
    }
    xmlParserData.close();
    throw new RuntimeException("Invalid format error");
  }

  public void onOpenTag(Handler handler) {
    openTagHandler = handler;
  }

  public void onCloseTag(Handler handler) {
    closeTagHandler = handler;
  }

  public void onTextValue(Handler handler) {
    textValueHandler = handler;
  }

  public void onStart(Handler handler) {
    startHandler = handler;
  }

  public void onEnd(Handler handler) {
    endHandler = handler;
  }

  public void onError(Handler handler) {
    errHandler = handler;
  }
}