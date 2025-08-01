package org.interview.cleartrip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RequestService {

    ConcurrentHashMap<String, List<ProjectRequest>> projectRequests = new ConcurrentHashMap<>();
    ProjectService projectService;
    IDGenerator idGenerator = new IDGenerator();
    ConcurrentHashMap<String, ProjectRequest> requests = new ConcurrentHashMap<>();

    public String requestProject(String developerId, String projectId){
        String id = "req" + idGenerator.getId();
        ProjectRequest r = new ProjectRequest(id, developerId, projectId);
        requests.put(id, r);
        projectRequests.putIfAbsent(projectId, new ArrayList<>());
        projectRequests.get(projectId).add(r);
        System.out.println("Request with id " + id + " for " + projectId + " is registered for " + developerId);
        return id;
    }

    public void acceptRequest(String requestId, String projectId, String leadId){

        ProjectRequest r = requests.get(requestId);
        if (r != null && projectRequests.get(projectId).contains(r)) {
            projectService.assignDeveloper(projectId, r.developerId);
            projectRequests.remove(projectId); // remove all pending requests
            System.out.println(r.developerId + " request is accepted to work on " + projectId);
        }
    }
}
