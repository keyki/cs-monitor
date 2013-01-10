package org.game.cs.core.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.game.cs.core.model.enums.UserState;
import org.springframework.stereotype.Component;

@Component
public class UserControl {

    private Map<String, UserState> statusMap;

    public UserControl() {
        statusMap = new ConcurrentHashMap<>();
    }

    public void addStatus(String user, UserState state) {
        statusMap.put(user, state);
    }

    public void removeStatus(String user) {
        statusMap.remove(user);
    }
    
    public UserState getUserState(String user){
        return statusMap.get(user);
    }
}
