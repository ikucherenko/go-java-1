package ua.goit.alg.xmlparser.parser;

import ua.goit.alg.xmlparser.statemashines.TagState;

import java.util.HashMap;
import java.util.Map;

public class ParserData {

  private StringBuilder tag = new StringBuilder("");
  private Map<String, String> attributes = new HashMap<String, String>();
  private StringBuilder text = new StringBuilder("");
  private StringBuilder attributeName = new StringBuilder("");
  private StringBuilder attributeValue = new StringBuilder("");

  public void appendAttributeName(char c) {
    attributeName.append(c);
  }

  public void appendAttributeValue(char c) {
    attributeValue.append(c);
  }

  public String getAttributeValue() {
    return attributeValue.toString();
  }

  public String getAttributeName() {
    return attributeName.toString();
  }

  public String getTag() {
    return tag.toString();
  }

  public void setTag(String tag) {
    this.tag = new StringBuilder(tag);
  }

  public void appendTag(char c) {
    tag.append(c);
  }

  public Map<String, String> getAttributes() {
    return attributes;
  }

  public void addAttribute(String key, String value) {
    this.attributes.put(key, value);
  }

  public String getText() {
    return text.toString();
  }

  public void setText(String text) {
    this.text = new StringBuilder(text);
  }

  public void appendText(char c) {
    text.append(c);
  }

  public void clear() {
    tag.setLength(0);
    text.setLength(0);
    attributes.clear();
    attributeName.setLength(0);
    attributeValue.setLength(0);
  }
}