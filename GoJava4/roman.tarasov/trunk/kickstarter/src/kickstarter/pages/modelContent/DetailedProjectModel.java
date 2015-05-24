package kickstarter.pages.modelContent;

import kickstarter.mvc.interfaces.iModel;

public class DetailedProjectModel extends PageModel {
	public DetailedProjectModel(iModel imodel) {
		super(imodel);
	}

	@Override
	public void updateStateOfPageModel(String message) {

		if (message.equals("p")) {
			modelOptions = imodel.getModelOptions();
			imodel.nextWithOptions(PROJECTS, modelOptions);
			return;
		}
		if (message.equals("c")) {
			imodel.next(COMMENT_PAGE);
			return;
		}
		if (message.equals("i")) {
			modelOptions = imodel.getModelOptions();
			modelOptions.intOption = intOption;
			imodel.nextWithOptions(INVEST_PAGE, modelOptions);
			return;
		}
		if (message.equals("d")) {
			modelOptions = imodel.getModelOptions();
			modelOptions.intOption = intOption;
			imodel.nextWithOptions(DONATE_PAGE, modelOptions);
			return;
		}
		imodel.goToAndBack(ERROR_PAGE, DETAILED_PROJECT);
	}
}