package kickstarter.model;

import java.util.ArrayList;
import java.util.List;

public class TheEndModel implements Model {

	@Override
	public List<String> getData() {
		return new ArrayList<>();
	}

	@Override
	public boolean showOnly() {
		return true;
	}

	@Override
	public List<Object> getParameters(int item) {
		return null;
	}
}
