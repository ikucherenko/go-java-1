package ua.goit.xmlparsertdd;


import java.util.HashMap;
import java.util.Map;

public class Tag {
  private TagType type;
  private String name;
  private Map<String, String> params = new HashMap<String, String>();

  public Tag() {
  }

  private Tag(TagType type, String name, Map<String, String> params) {
    this.type = type;
    this.name = name;
    this.params = params;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public void setType(TagType type) {
    this.type = type;
  }
  
  public TagType getType() {
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

    Tag tag = (Tag) o;

    if (getType() != tag.getType()) return false;
    if (getName() != null ? !getName().equals(tag.getName()) : tag.getName() != null)
      return false;
    return !(getParams() != null ? !getParams().equals(tag.getParams()) : tag.getParams() != null);

  }

  @Override
  public int hashCode() {
    int result = getType() != null ? getType().hashCode() : 0;
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
    return result;
  }

  static class Builder {
    private TagType type;
    private StringBuilder name = new StringBuilder();
    private Map<String, String> params = new HashMap<String, String>();
    private StringBuilder paramName = new StringBuilder();
    private StringBuilder paramValue = new StringBuilder();

    static Builder newBuilder() {
      return new Builder();
    }

    public TagType getType() {
      return type;
    }

    public void setType(TagType type) {
      this.type = type;
    }

    public String getName() {
      return name.toString();
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

    public Tag build() {
      return new Tag(type, name.toString(), params);
    }
  }

}
