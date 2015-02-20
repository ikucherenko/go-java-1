package ua.com.goit.gojava.andriidnikitin.service;

import java.util.ArrayList;
import java.util.List;

import ua.com.goit.gojava.andriidnikitin.model.Good;
import ua.com.goit.gojava.andriidnikitin.model.GoodType;
import ua.com.goit.gojava.andriidnikitin.model.util.Attribute;

public class GoodDAO implements IDAO<Good> {
	
	List<Good> list;
	
	public GoodDAO() {
		list = new ArrayList<Good>();
		init();
	}

	@Override
	public boolean create(Good arg) {		
		return list.add(arg);		
	}

	@Override
	public void update(Good arg) {
		for (Good good: list) {
			if (good.getId().equals(arg.getId())) {
				list.remove(good);
				list.add(arg);
				return;
			}
		}
		create(arg);
	}

	@Override
	public List<Good> getAll() {
		List<Good> result = new ArrayList<Good>(list);
		return result;
	}
	
private void init() {
	
		GoodTypeDAO dao = new GoodTypeDAO();
		GoodType type = dao.getAll().get(0);
		List<Attribute> description = new ArrayList<Attribute>();
		
		Good good = new Good();
		good.setId(1);
		good.setName("Fender Strat");		
		good.setType(type);
		good.setDescription(description);
		list.add(good);
		
		good = new Good();
		good.setId(2);
		good.setName("Fender Telecaster");		
		good.setType(type);
		good.setDescription(description);
		list.add(good);
		
		
		good = new Good();
		good.setId(3);
		good.setName("Gibson Les Paul");		
		good.setType(type);
		good.setDescription(description);
		list.add(good);
		
		type = dao.getAll().get(1);
		
		good = new Good();
		good.setId(4);
		good.setName("Fender Rhodes");		
		good.setType(type);
		good.setDescription(description);
		list.add(good);
		
		good = new Good();
		good.setId(5);
		good.setName("Korg MS-20");		
		good.setType(type);
		good.setDescription(description);
		list.add(good);
		
		type = dao.getAll().get(2);
		
		good = new Good();
		good.setId(6);
		good.setName("Boss Delay");		
		good.setType(type);
		good.setDescription(description);
		list.add(good);
	}
	
	
}
