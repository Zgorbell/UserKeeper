package objects;

import lombok.Data;

import java.util.*;

@Data
public class ChoicesMap {

    private Map<Integer, String> map;

    public ChoicesMap(){
        map = new HashMap<>();
    }

    public void  addChoice(int code, String message){
        map.put(code, message);
    }

    public Set<Map.Entry<Integer, String>> getEntrySet(){
        return map.entrySet();
    }

    public boolean isCommandValid(int command){
        return map.containsKey(command);
    }

    public String getCommand(int command){
        return map.get(command);
    }

    public int getPosition(String command){
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(command)) return entry.getKey();
        }
        return -1;
    }
}
