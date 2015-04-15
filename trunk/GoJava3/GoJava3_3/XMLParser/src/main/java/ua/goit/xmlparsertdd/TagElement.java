package ua.goit.xmlparsertdd;


import java.util.HashMap;
import java.util.Map;

public class TagElement implements Element {
  private TagElementType type;
  private String name;
  private Map<String, String> params = new HashMap<>();

  public TagElement() {
  }

  private TagElement(TagElementType type, String name, Map<String, String> params) {
    this.type = type;
    this.name = name;
    this.params = params;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(TagElementType type) {
    this.type = type;
  }

  public TagElementType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TagElement TagElement = (TagElement) o;

    if (getType() != TagElement.getType()) return false;
    if (getName() != null ? !getName().equals(TagElement.getName()) : TagElement.getName() != null)
      return false;
    return !(getParams() != null ? !getParams().equals(TagElement.getParams()) : TagElement.getParams() != null);
  }

  @Override
  public int hashCode() {
    int result = getType() != null ? getType().hashCode() : 0;
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
    return result;
  }

  static class Builder {
    private TagElementType type;
    private StringBuilder name = new StringBuilder();
    private Map<String, String> params = new HashMap<>();
    private StringBuilder paramName = new StringBuilder();
    private StringBuilder paramValue = new StringBuilder();

    static Builder newBuilder() {
      return new Builder();
    }

    public void setType(TagElementType type) {
      this.type = type;
    }

    public void buildName(char c) {
      name.append(c);
    }
    
    public void buildParamName(char c) {
      paramName.append(c);
    }
    
    public void buildParamValue(char c) {
      paramValue.append(c);
    }

    public void addParams() {
      params.put(paramName.toString(), paramValue.toString());
      paramName = new StringBuilder();
      paramValue = new StringBuilder();
    }

    public TagElement build() {
      TagElement result = new TagElement(type, name.toString(), params);
      return result;
    }
  }

  @Override
  public String getValue() {
    StringBuilder res = new StringBuilder();
    res.append(name).append(" ");
    if (params.size() != 0) {
      for (Map.Entry<String, String> entry : params.entrySet()) {
        res.append(entry.getKey())
                .append("=\"")
                .append(entry.getValue())
                .append("\"");
      }
    }
    return res.toString();
  }
}