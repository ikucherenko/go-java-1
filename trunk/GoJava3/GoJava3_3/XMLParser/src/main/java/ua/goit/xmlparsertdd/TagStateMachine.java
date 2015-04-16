package ua.goit.xmlparsertdd;

import java.util.HashSet;
import java.util.Set;

public class TagStateMachine {
  
  final static Set<TagState> commentStates= new HashSet<>();
  static{
    commentStates.add(TagState.COMMENT);
    commentStates.add(TagState.OPEN);
    commentStates.add(TagState.EXCLAM_MARK);
    commentStates.add(TagState.HYPHEN_BEFORE);
    commentStates.add(TagState.FIRST_HYPHEN_AFTER);
    commentStates.add(TagState.SECOND_HYPHEN_AFTER);
    commentStates.add(TagState.CLOSE);
    commentStates.add(TagState.VALID_COMMENT_END);
  }
  
  TagState currentState = TagState.INIT;
  private TagElement.Builder tagBuilder = TagElement.Builder.newBuilder();
  private TextElement.Builder textBuilder = TextElement.Builder.newBuilder();
  private TagStack tagStack = new TagStack();
  private boolean hasNotSentTextValue = false;
  
  public TagState next(char c, XMLParser parser) throws XMLNestingException{
    TagState previousState = currentState;
    currentState = currentState.next(c, tagBuilder);

   if (currentState == TagState.TEXT_VALUE) {
      textBuilder.buildTextValue(c);
    } 

    if (previousState != currentState) {
      if (currentState == TagState.VALID_TAG_END || 
          currentState == TagState.VALID_HEADER_END) {
        createTagElement(parser);
      }else if (currentState == TagState.INVALID_TAG_END) {
        textBuilder.buildTextValue("XML Syntax Error");
        TextElement result = textBuilder.build();
        textBuilder = TextElement.Builder.newBuilder();
        parser.sendEventToHandler(Event.INVALID_END, result);
      } 
      
      if (previousState == TagState.TEXT_VALUE) {
        hasNotSentTextValue = true;
      }
    }
    
    if(hasNotSentTextValue && !commentStates.contains(currentState) && currentState != TagState.TEXT_VALUE){
      parser.sendEventToHandler(Event.TEXT_VALUE, textBuilder.build());
      textBuilder.resetTextValue();
      hasNotSentTextValue = false;
    }
    return currentState;
  }

  private void createTagElement(XMLParser parser) throws XMLNestingException {
    TagElement result = tagBuilder.build();
    tagBuilder = TagElement.Builder.newBuilder();
    setEvent(parser, result);
   }

  public void setEvent(XMLParser parser, TagElement result) throws XMLNestingException {
    if (result.getType() == TagElementType.OPEN) {
      tagStack.push(result);
      parser.sendEventToHandler(Event.OPEN_TAG, result);
    } else if (result.getType() == TagElementType.CLOSE) {
      parser.sendEventToHandler(Event.CLOSE_TAG, result);
      TagElement prevTag = tagStack.pop();
      if (!prevTag.getName().equals(result.getName())){
        throw new XMLNestingException(); 
      }
    } else if (result.getType() == TagElementType.HEADER) {
      tagStack.push(result);
      parser.sendEventToHandler(Event.START, result);
    } else if (result.getType() == TagElementType.SINGLE) {
      tagStack.push(result);
      tagStack.pop();
      parser.sendEventToHandler(Event.SINGLE_TAG, result);
    }
  }

  boolean isStackEmpty() {
    return tagStack.isStackEmpty();
  }
}
