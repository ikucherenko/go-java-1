package ua.com.goit.gojava.kickstarter;

import java.util.Arrays;

public class Kickstarter {

	private Categories categories;
	private Projects projects;
	private IO io; 
	private QuoteGenerator generator; 

	public Kickstarter(Categories categories, Projects projects, IO io, QuoteGenerator generator) {
		this.categories = categories;
		this.projects = projects;
		this.io = io;  
		this.generator = generator;
	}

	public void run() {	
		println(generator.nextQuote());
		
		categoryMenu().run();
		println("Спасибо за использование нашей программы!");
	}

	private Menu categoryMenu() {
		return new Menu(io) {
			@Override
			Menu nextMenu(Object selected) {
				Category category = (Category)selected;
								
				Project[] found = projects.getProjects(category); 
				printProjects(found);	
				
				return projectsMenu(found); 
			}

			@Override
			Object choose(int menu) {
				return chooseCategory(menu); 
			}

			@Override
			void ask() {
				askCategories();  
			}
		};
	}

	private Menu projectsMenu(final Project[] found) {
		return new Menu(io) {
			@Override
			Menu nextMenu(Object selected) {
				Project project = (Project)selected;
				
				chooseProject(project); 													
				printProjectDetails(project);
				return projectMenu(project); 
			}

			@Override
			Object choose(int menu) {
				return chooseProject(menu, found); 
			}

			@Override
			void ask() {
				askProjects(found); 
			}
		};
	}
	
	private Menu projectMenu(final Project project) {
		return new Menu(io) {
			@Override
			Menu nextMenu(Object selected) {
				Integer menu = (Integer)selected;
				
				if (menu == 1) {
					println("Спасибо, что хотите помочь проекту!");

					println("Введите имя:");				
					String name = io.read(); 
					
					println("Введите номер вашей карточки:");
					String cardNumber = io.read(); 
					
					println("Введите размер суммы:");
					int amount = Integer.valueOf(io.read()); 
					
					println("Спасибо " + name + " Ваши деньги в размере " + amount + " успешно зачислились на счет проекта!");
					// TODO а что если ввели что-то не то? 
					println("--------------------------------------"); // TODO устранить это дублирование
					
					project.donate(amount); // тут нет защиты от дурака, а вдруг кто-то введет отрицательное значение? TODO
				} else if (menu == 2) {
					println("Введите ваш вопрос:");
					
					String qustion = io.read();
					
					println("Спасибо за ваш вопрос, вскоре Автора с вами свяжутся");
					
					project.addQuestionAnswer(qustion);
				}
				
				return null; 
			}

			@Override
			Object choose(int menu) {
				return menu;
			}

			@Override
			void ask() {
				askProject(project); 	
			}
		};
	}

	private void askProject(Project project) {
		println("Выберите что хотите сделать с проектом: \n"
				+ "[0 - выйти к списку проектов, 1 - инвестировать в проект, 2 - задать вопрос Авторам]");
	}

	private Project chooseProject(int menu, Project[] found) {
		if (menu <= 0 || found.length < menu) {
			println("Неверный индекс меню " + menu);
			return null;  
		}
		return found[menu - 1];
	}

	private void println(String message) {
		io.print(message + "\n");
	}
	
	private void askProjects(Project[] found) {
		if (found.length == 0) {
			println("Проектов в категории нет. Нажмите 0 - для выхода.");
		} else {
			int from = 1;
			int to = found.length;
			println("Выберите проект: [" + from + "..." + to + "] или 0 для выхода" );
		}
	}

	private void printProjectDetails(Project project) {
		printProject(project);
		
		println(project.getHistory()); 
		println(project.getDemoVideo()); 
		
		String questionAnswers = project.getQuestionAnswers();
		if (questionAnswers != null) {  
			println(questionAnswers); 
		}
		println("--------------------------------------"); 
	}

	private void chooseProject(Project project) {
		println("Вы выбрали проект: " + project.getName());
		println("--------------------------------------");
	}

	private void printProjects(Project[] found) {
		for (int index = 0; index < found.length; index++) {
			Project project = found[index];
			io.print((index + 1) + " - ");  
			printProject(project); 			
		}
	}

	private void printProject(Project project) {
		println(project.getName());
		println(project.getDescription()); 
		println("Уже собрали " + project.getAmount() + " грн за " + project.getDays() + " дней"); 
		println("Надо собрать " + project.getExist() + " грн");
		println("--------------------------------------");
	}

	private void askCategories() {
		println("Выберите категорию (или 0 для выхода):");
		println(Arrays.toString(categories.getCategories()));
	}

	private Category chooseCategory(int menu) {
		if (menu <= 0 || categories.size() < menu) {
			println("Неверный индекс меню " + menu);
			return null; // TODO не рекомендуется так делать, потому что потенциальный NPE у клиента 
		}
		
		// TODO тут надо привести либо везде к одному виду, либо разделять - на вьюхе от 1 до N а в моделе от 0 до N-1 
		Category category = categories.get(menu - 1); 
		println("Вы выбрали категорию: " + category.getName());
		println("--------------------------------------");
		return category;
	}
}
