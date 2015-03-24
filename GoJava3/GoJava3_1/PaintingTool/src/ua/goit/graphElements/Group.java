package ua.goit.graphElements;

import java.util.ArrayList;

public interface Group {
    public ArrayList<Element> getElements();
    public ArrayList<Group> getGroups();
    public void setElement(Element element);
    public void setGroup(Group group);
    public String getName();
    public String getType();

}
