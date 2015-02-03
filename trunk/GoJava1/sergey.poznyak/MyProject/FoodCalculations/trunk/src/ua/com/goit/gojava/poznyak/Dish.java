package ua.com.goit.gojava.poznyak;

import java.util.List;

/**
 * The Dish bean.
 * 
 * This bean implements the dish image.
 * 
 * @version 0.04 28 Jan 2015
 * @author Sergey Poznyak
 */
public class Dish {
	
	private Integer id;
	
	private String name;
	
	private List<Ingredient> ingredients;

	public Dish() {}
	
	protected Dish(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> dishIngredients) {
		this.ingredients = dishIngredients;
	}

	@Override
	public String toString() {
		return id + ". " + name;
	}
	
}
