package ua.com.goit.gojava.kickstarter;

import java.util.Arrays;
import java.util.Scanner;

public class Kickstarter {

	private Categories categories;
	private Projects projects; 

	public Kickstarter(Categories categories, Projects projects) {
		this.categories = categories;
		this.projects = projects;
	}

	// метод надо сделать пабликом, чтобы его было визно запределами класса. Это жеж наш основной класс
	public void run() {		
		QuoteGenerator generator = new QuoteGenerator();
		System.out.println(generator.nextQuote());
		
		// итак тут у нас реализована логика работы меню
		// все зациклено а потому погрузимся вовнутрь
		while (true) {
			// теперь программа стала компактнее и не обязательно больше держать эти отступы, они не нужны
			askCategory();
			int categoryIndex = selectMenu(); // автоматический рефакторинг лучше ручного. меньше вероятности сделать ошибку.
			Category category = chooseCategory(categoryIndex);
			// вот почему не рекомендуется возвращать null потому что можно забыть его проверить. В этом случае лучше пользоваться 
			// exception но так как пока мы его не рассматривали то поиграется с null
			if (category == null) {
				continue; // вот вам и вот :) NPE и я не знаю из за чего. Я ввел -1 и все ок, а 3 поломалось...
			}
			
			// получаю список проектов
			Project[] foundProjects = projects.getProjects(category);
			printProjects(foundProjects);
			// мне нравится. Запущу, потещу ручками и закоммичу этот подготовительный рефакторинг
			
			// конец цикла, возвращаемся вначало где снова спросим пользователя выбрать категорию
			// вот тут то нам и надо разрабатывать дальше. 
			// теперь можно дополнять код в местах, где я написал 
			// идея в том, что вы вначале написали программу на русском языке, в комментариях
			// увидели ее структуру и теперь можно спуститься глубже и начать прорабатывать детали
			// поехали? 
			
			// Всегда надо помнить на чем мы остановились, а потому очень рекомендую завести листик A4 и ручку, чтобы записывать
			// В голове это все держать не практично. у дивелопера должны быть навык переключения с стратегии на тактику и наоборот
			// делать это стоит между задачами. Иногда дробление будет несколькоуровневное и надо помнить какую большую задачу решаю, 
			// какую ее подзадачу, и какую ее под-под-задачу, а так же что уже попробовал и что еще осталось. Держать это все в голове сложно
			// потому список задач A4 - ваш лучший друг
			
			while (true) { // этот цикл пока пустой я его закомментирую
				// попросить пользователя выбрать проект по номеру
				// где-то "попросить пользователя" у нас уже было наверху, правда? Мы просили категорию.
				// есть два подхода - copy past - чем мы породим дублирование, его не рекомендую.
				// и extract method - это больше по ООП. 
				// где наш метод?
				
				// предложение выбрать фильм
				askProject(foundProjects);
	
				int projectIndex = selectMenu(); 
				// найти проект по индексу
				// тут projectIndex - индекс в меню (отфильтрованный список), а не в общем хранилище
				// приходится переделывать а как иначе?
				// если индекс выбран неправильно - тут ошибка! - проверим? пофиксили! теперь я вот что думаю, если 
				// есть такая ошибка с проектами, значит она есть везде, где есть запрос числа - с категориями тоже. Пофиксим и там? да!
				
				// вот тут надо делать проверку, если индекс не тот - выходим! вернее вывод ошибки и новый запрос 
				if (projectIndex < 0 || foundProjects.length <= projectIndex) {
					System.out.println("Неверный индекс меню " + projectIndex);
					continue; // так как мы находимся в цикле, то можно такой командой перейти в его начало. Такой себе аналог goto
					// кстати goto не пользуйтесь никогда! это усложняет понимание кода и выдает в вас новичков
				}
				Project project = foundProjects[projectIndex];
				
				// но в целом все работает как я хотел. Теперь нельзя выбрать проект из не той категории!!
				// чистая импровизация, это продумать на перед у меня не получилось бы - я жеж не знал всех историй заранее
				chooseProject(project); // тут по аналогии :) иногда по аналогии приводит к ошибкам :)													
				printProjectDetails(project);
				// ану попробуем...
				
				// ну и пока не перешли к следующей категории можно предложить пользователю снова выбрать другой проект,
				// то есть надо зациклить вот так
				// да цикл бесконечный, потому мы тут поставим TODO и разберемся что делать дальше (это уже следующая история)
			}
			// ну что, инкрементом я довлен, а потому коммичусь
			// осталось еще у меня пару тудушек. Сделаем защиту от дурака
			
			// вот мой А4 для задачек. Оставляю тут, чтобы знать что еще надо сделоать. Беру самую простую задачу. 
			// форматирование страдает :) - уже нет :)
		}
	}

	private void askProject(Project[] foundProjects) {
		// по аналогии
		// предлагаем выбрать проект
		int from = 0;
		int to = foundProjects.length - 1;
		System.out.println("Выберите проект: [" + from + "..." + to + "]" ); // я хочу (эстетика) чтобы тут вывелось все возможные варианты
		
	}

	private void printProjectDetails(Project project) {
		// распечатать подробности проекта, включающие в себя 
		// + все то же что в списке - это уже есть и надо повторно использовать
		printProject(project);
		// + история проекта 
		System.out.println(project.getHistory()); // новое понятие
		// + линк на видео с демо  
		System.out.println(project.getDemoVideo()); // новое понятие
		// + вопросы/ответы  
		System.out.println(project.getQuestionAnswers()); // новое понятие
		System.out.println("--------------------------------------"); // добавил строку разделитель
	}

	// Метод реализован, сделаем его по аналогии c категорией 
	private void chooseProject(Project project) {
		System.out.println("Вы выбрали проект: " + project.getName());
		System.out.println("--------------------------------------");
	}

	private void printProjects(Project[] foundProjects) {
		// вывожу информацию про каждый проект по очереди
		// вот по этому списку проектов строится индекс меню, но оно инкапсулировано в методе и из него не выходит, п
		// потому я вынесу это строку за пределы метода
		for (int index = 0; index < foundProjects.length; index++) {
			Project project = foundProjects[index];
			System.out.print(index + " - "); // тут заметь print без ln
			printProject(project); 				
			// тут у нас индекса нет, а потому надо его как-то родить. 
			// параллельно я помню, что проекты у меня все со всех категорий хранятся в одном хранилище, и их 
			// нумерация не совпадает с нумерацией в меню
			// сейчас покажу. Я зайду в категорию в корой нет проектов и выберу 1 и увижу проект из другой 
			// категории... это бага 
			// и еще одна ошибка (исправлена!), я в другой категории могу выбрать проект из другой категории
			// наверное надо придумать как-то как мы буем различать проекты - это одно число
			// а меню строится по другому числу
			// но пока хоть выведу тут индекс
		}
	}

	private void printProject(Project project) {
		// тело цикла обычно так же стоит выделить, что тут делается? печатается меню проектов
		System.out.println(project.getName());
		System.out.println(project.getDescription()); 
		System.out.println("Уже собрали " + project.getAmount() + " грн за " + project.getDays() + " дней"); 
		System.out.println("Надо собрать " + project.getExist() + " грн");
		System.out.println("--------------------------------------");
	}

	private void askCategory() {
		// предлагаем выбрать категорию
		System.out.println("Выберите категорию:");
		System.out.println(Arrays.toString(categories.getCategories()));
	}

	private Category chooseCategory(int categoryIndex) {
		// получаю выбранную категорию 
		
		// тут сложнее :) но мы справились! Проверим?
		if (categoryIndex < 0 || categories.size() <= categoryIndex) {
			System.out.println("Неверный индекс меню " + categoryIndex);
			return null; // не рекомендуется так делать, потому что потенциальный NPE у клиента, но что поделать, пока так - оставим TODO
		}
		
		Category category = categories.get(categoryIndex);
		System.out.println("Вы выбрали категорию: " + category.getName());
		System.out.println("--------------------------------------");
		return category;
	}

	// он выделил private внутренний метод. класс! теперь мы можем его повторно использовать. 
	private int selectMenu() {
		// спрашиваем пользователя что он хочет выбрать <- вот он!
		// этот заголовок намекает на название будущего метода
		Scanner scanner = new Scanner(System.in);
		int categoryIndex = scanner.nextInt();
		// обрати внимание, что каждый блок кода отделен пробелами и у некоторых даже может быть коммент
		// это значит одно - автор сделал специально так, потому что эти две строки друг без друга не имеют смысла
		// а значит так, надо выделить их в метод отдельный. Это можно было сделать сразу, но я специально подождал
		// пока строки мне не понадобятся. Вот этот отступ указывает границы будущшего метода, а 
		return categoryIndex;
	}
}
