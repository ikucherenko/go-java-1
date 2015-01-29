package ua.com.goit.gojava1.lslayer.hackit2.gear.hardware;

import java.util.LinkedHashMap;
import java.util.Map;

import ua.com.goit.gojava1.lslayer.hackit2.gear.AbstractUtility;

public abstract class AbstractHardware extends AbstractUtility implements Hardware {
    public AbstractHardware(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }
    private Map<String, Integer> parameters = new LinkedHashMap<String, Integer>();

    
    public Hardware addParameter(String paramName, int paramValue) {
        this.parameters.put(paramName, paramValue);
        return this;
    }
    
    public String listParameters() {
        String result = "";
        String eol = System.getProperty("line.separator");
        for (Map.Entry<String, Integer> element : parameters.entrySet()) {
            result += eol + element.getKey().toLowerCase() + ": " + element.getValue(); 
        }
        return result;
    }
    @Override
    public String toString() {
        return super.getName() + listParameters();
    }
    
}
