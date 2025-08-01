package org.interview.cleartrip;

import java.util.List;

public class ThrivePlatform {

    public static void main(String[] args) {
        ProjectService projectService = new ProjectService();
        UserService userService = new UserService();
        RequestService requestService = new RequestService();

        userService.registerLead("Lead1");
        userService.registerLead("Lead2");

        userService.registerDeveloper("Dev1");
        userService.registerDeveloper("Dev2");

       projectService.registerProject("Lead2", "App Development", ProjectCategory.BACKEND, List.of("web-development", "design"));
       projectService.registerProject("Lead1", "K8s cluster setup", ProjectCategory.BACKEND, List.of("devops"));

       List<String> availableProjects = projectService.getAvailableProjects();
       availableProjects.forEach(System.out::println);

        requestService.requestProject("Dev1", "project100");
        requestService.acceptRequest("req200", "project100", "Lead2");

        Developer dev1 = userService.getDeveloper("Dev1");
        if(dev1 != null) System.out.println(dev1.name);

        Developer dev2 = userService.getDeveloper("Dev2");
        if(dev2 != null) System.out.println(dev2.name);

        Project project1 = projectService.getProjectDetails("project100");
        if(project1 != null) System.out.println(project1.name);

        Project project2 = projectService.getProjectDetails("project101");
        if(project2 != null) System.out.println(project2.name);


        projectService.completeProject("Dev1", "project100");
        projectService.cancelProject("Lead1", "project101");
    }
}
