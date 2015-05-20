package ua.com.goit.gojava.kickstarter.view;

import java.util.ArrayList;

import ua.com.goit.gojava.kickstarter.model.Category;
import ua.com.goit.gojava.kickstarter.model.Project;

public class ProjectsPage {
    
    Printer printer;

    public ProjectsPage(Printer printer) {
	this.printer = printer;
    }

    public void showProjectInfo(Project project) {
	printer.println(project.getName());
	printer.println("  Short Description: " + project.getBrief());
	printer.println("  Pledged: " + project.getPledged());
	printer.println("  Days to go: " + project.getDaysToGo());
	printer.println("  History: " + project.getDescription());
	printer.println("  Video link: " + project.getLink());
	printer.println("  Questions/Answers " + project.getFAQ());
    }

    public void showProjectMenu(Project project, int userInput) {
	while (true) {

	    showProjectInfo(project);

	    printer.println("");
	    printer.println("Press 0 to return back");
	    printer.println("------------------------------------------------------------------");

	    int keyCode = userInput;
	    if (keyCode == 0) {
		break;
	    } else {
		printer.println("Wrong code!");
	    }
	}
    }
    
    public void showProjectsOfCategory(Category category, ArrayList<Project> projectsOfCurrentCategory) {
	printer.println("Category: " + category.getName());
	printer.println(" Projects: ");
	for (int i = 0; i < projectsOfCurrentCategory.size(); i++) {
	    printer.print("  " + (i + 1) + ": ");
	    Project currentProject = projectsOfCurrentCategory.get(i);
	    printer.println(currentProject.getName());
	    printer.println("  Short Description: "
		    + currentProject.getBrief());
	    printer.println("  Pledged: " + currentProject.getPledged());
	    printer.println("  Days to go: " + currentProject.getDaysToGo());
	}
    }

}
