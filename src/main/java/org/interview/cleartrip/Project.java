package org.interview.cleartrip;

import java.util.List;

public class Project {

    String id;
    String name;
    ProjectCategory category;
    List<String> skills;
    String leadId;
    String  assignedTo;
    ProjectStatus status;

    public Project(String id, String name, ProjectCategory category, List<String> skills, String leadId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.skills = skills;
        this.leadId = leadId;
        this.assignedTo = null;
        this.status = ProjectStatus.OPEN;
    }
}
