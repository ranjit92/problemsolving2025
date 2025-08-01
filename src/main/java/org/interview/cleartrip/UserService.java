package org.interview.cleartrip;

import java.util.concurrent.ConcurrentHashMap;

public class UserService {

    ConcurrentHashMap<String, Lead> leads = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Developer> developers = new ConcurrentHashMap<>();
    IDGenerator id = new IDGenerator();

    public void registerLead(String name){
        leads.put(name, new Lead(name));
        System.out.println("Lead generated: "+ name);
    }

    public void registerDeveloper(String name){
        developers.put(name, new Developer(name));
        System.out.println("Developer generated: "+ name);
    }

    public Developer getDeveloper(String id){
        if(developers.containsKey(id)){
            return developers.get(id);
        }
        throw new RuntimeException("Developer not found");
    }

    public boolean isLead(String id){
        return leads.containsKey(id);
    }
}
