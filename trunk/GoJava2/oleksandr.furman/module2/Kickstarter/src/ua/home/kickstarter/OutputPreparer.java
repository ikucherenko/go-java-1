package ua.home.kickstarter;

import java.util.ArrayList;
import java.util.List;

public class OutputPreparer {
	private Projects projects;
	private List<String> stringProjects;
	private List<String> stringFullProject;

	public OutputPreparer(Projects projects) {
		this.projects = projects;
	}

	public List<String> stringProjectOutput(Category category) {
		stringProjects = new ArrayList<String>();
		int from = 1;
		int to = projects.getProjects(category).size();
		stringProjects.add("");
		stringProjects.add("Выберите проект: [" + from + "..." + to + "] или 0 для выхода");
		stringProjects.add("------------------------------------");
		List<Project> foundProjects = projects.getProjects(category);
		int count = 1;
		for (Project project : foundProjects) {
			stringProjects.add("Название проекта:           " + count + " - " + project.getName());
			stringProjects.add("Описание проекта:           " + project.getDescription());
			stringProjects.add("Необходимая сумма:          " + project.getGoal() + "$");
			stringProjects.add("Собранная сумма:            " + project.getPledged() + "$");
			stringProjects.add("До окончания сбора средств: " + project.getDaysLeft() + " дней");
			stringProjects.add("------------------------------------");
			count++;
		}
		return stringProjects;
	}

	public List<String> stringFullProjectOutput(int projectIndex, Category category) {
		stringFullProject = new ArrayList<String>();
		Project project = projects.getFullProject(projectIndex, category);
		stringFullProject.add("Вы выбрали проект " + project.getName());
		stringFullProject.add("");
		stringFullProject.add("Название проекта:           " + project.getName());
		stringFullProject.add("Описание проекта:           " + project.getDescription());
		stringFullProject.add("Необходимая сумма:          " + project.getGoal() + "$");
		stringFullProject.add("Собранная сумма:            " + project.getPledged() + "$");
		stringFullProject.add("До окончания сбора средств: " + project.getDaysLeft() + " дней");
		stringFullProject.add("История проекта:            " + project.getHistory());
		stringFullProject.add("Линки на видео с демо       " + project.getLinksToVideo());
		stringFullProject.add("Вопросы/ответы:             " + project.getQuestions());
		stringFullProject.add("------------------------------------");
		stringFullProject.add("Введите 0 для выхода");
		stringFullProject.add("");
		return stringFullProject;
	}
}
