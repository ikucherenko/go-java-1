package ua.com.goit.gojava2.vova.kickstarter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.com.goit.gojava2.vova.kickstarter.model.Category;
import ua.com.goit.gojava2.vova.kickstarter.model.Project;
import ua.com.goit.gojava2.vova.kickstarter.service.CategoryService;
import ua.com.goit.gojava2.vova.kickstarter.service.ProjectService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProjectService projectService;

	@RequestMapping(value = {"/", "/categories"}, method = RequestMethod.GET)
	public String listCategories(ModelMap model, HttpServletRequest req) {
		List<Category> categories = categoryService.findAllCategories();
		String message = req.getParameter("message");
		model.addAttribute("message", message);
		model.addAttribute("categories", categories);
		return "categories";
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public String showCategory(ModelMap model, @PathVariable int id) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		return "category";
	}

	@RequestMapping(value = "/categories/{id}", params = "delete", method = RequestMethod.GET)
	public String deleteCategory(ModelMap model, @PathVariable int id) {
		if (categoryService.getCategoryById(id).getProjects().isEmpty()){
			categoryService.deleteCategoryById(id);
			model.addAttribute("message", "Category successfully deleted");
		} else {
			model.addAttribute("message", "This category can not be removed because it is still not empty");
		}
		return "redirect:/categories";
	}
	
	@RequestMapping(value = "/categories", params = "add", method = RequestMethod.GET)
	public String newCategory(ModelMap model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "addcategory";
	}

	@RequestMapping(value = "/categories", params = "add", method = RequestMethod.POST)
	public String saveCategory(@Valid Category category, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "addcategory";
		}
		categoryService.saveCategory(category);
		model.addAttribute("message", "Category registered successfully");
		return "redirect:/categories";
	}

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public String listProjects(@PathVariable int id, ModelMap model) {
		List<Project> projects = projectService.findAllProjects(id);
		if(projects.isEmpty()){
			model.addAttribute("message", "This category does not have a project");
			return "redirect:/categories";
		} else {
			model.addAttribute("projects", projects);
			return "projects";
		}
	}
	
	@RequestMapping(value = "/projects/{id}", params = "show", method = RequestMethod.GET)
	public String showProject(@PathVariable int id, ModelMap model) {
		Project project = projectService.getProgect(id);
		model.addAttribute("project", project);
		return "project";
	}

	@RequestMapping(value = "/projects/{id}/{idCategory}", params = "delete", method = RequestMethod.GET)
	public String deleteProject(ModelMap model, @PathVariable int id) {
		projectService.deleteProjectById(id);
		return "redirect:/projects/{idCategory}";
	}
	
	@RequestMapping(value = "/projects/{id}", params = "add", method = RequestMethod.GET)
	public String newProject(ModelMap model, @PathVariable int id) {
		Project project = new Project();
		model.addAttribute("id", id);
		model.addAttribute("project", project);
		return "addproject";
	}

	@RequestMapping(value = "/projects/{id}", params = "add", method = RequestMethod.POST)
	public String saveProject(@Valid Project project, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "addproject";
		}
		projectService.saveProject(project);
		model.addAttribute("message", "Project registered successfully");
		return "redirect:/categories";
	}

	@RequestMapping(value = "/donate/{id}", method = RequestMethod.GET)
	public String donate(ModelMap model, @PathVariable int id) {
		model.addAttribute("id", id);
		return "donate";
	}

	@RequestMapping(value = "/donatesuccess", method = RequestMethod.GET)
	public String saveDonate(ModelMap model, HttpServletRequest req) {
		int amount = Integer.valueOf(req.getParameter("amount"));
		int project = Integer.valueOf(req.getParameter("project"));
		projectService.addDonate(amount, project);

		model.addAttribute("success", "Donate " + amount + " successfully");
		model.addAttribute("project", project);
		return "donatesuccess";
	}
}
