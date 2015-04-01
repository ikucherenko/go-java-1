package ua.com.goit.gojava.POM.persistence.fileDB;

import java.util.List;

public interface DAOFactory {
	
	public void saveObject(Object obj, String key);
	public void deleteObject(Object obj, String key);
	public List<Object> getObjectList(String key);
	public void saveData();
	
}