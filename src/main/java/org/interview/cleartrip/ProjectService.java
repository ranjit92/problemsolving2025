package org.interview.cleartrip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectService {

    ConcurrentHashMap<String, Project> projects = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Developer> developers = new ConcurrentHashMap<>();
    IDGenerator idGenerator = new IDGenerator();

    public String registerProject(String leadId, String name, ProjectCategory projectCategory, List<String> skills){
        String projectId = name + idGenerator.getId();
        Project project = new Project(projectId, name, projectCategory, skills, leadId);
        projects.put(projectId, project);
        System.out.println(name + " project with id " + projectId + " registered by " + leadId);
        return projectId;
    }

    public List<String> getAvailableProjects(){
        List<String> res = new ArrayList<>();
        for (Project p : projects.values()) {
            if (ProjectStatus.OPEN == p.status) {
                res.add(p.id + ", " + p.name + ", " + p.skills + ", " + p.leadId);
            }
        }
        return res;
    }

    public record EntityName(String name, AtomicInteger id){}

    public void assignDeveloper(String projectId, String developerId) {
        Project p = projects.get(projectId);
        p.status = ProjectStatus.ASSIGNED;
        p.assignedTo = developerId;
        developers.get(developerId).assignedProjectId = projectId;
    }

    void cancelProject(String leadId, String projectId){

        Project p = projects.get(projectId);
        if (p != null && p.leadId.equals(leadId) && p.status == ProjectStatus.OPEN) {
            p.status = ProjectStatus.CANCELLED;
            System.out.println(projectId + " is cancelled");
        }
    }

    void completeProject(String developerId, String projectId){
        Project p = projects.get(projectId);
        if (p != null && p.assignedTo.equals(developerId)) {
            p.status = ProjectStatus.COMPLETED;
            developers.get(developerId).assignedProjectId = null;
            System.out.println(projectId + " is marked completed");
        }
    }

    Project getProjectDetails(String projectId){
        Project p = projects.get(projectId);
        if (p == null){
            throw new RuntimeException("Project not found");
        }
        return projects.get(projectId);
    }
}
