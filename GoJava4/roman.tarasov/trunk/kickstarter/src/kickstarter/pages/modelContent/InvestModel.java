package kickstarter.pages.modelContent;

import kickstarter.repository.facade.RepositoryException;


public class InvestModel extends PageModel {

	@Override
	public void updateStateOfPageModel(String message) throws RepositoryException  {

		if (message.equals("p")) {
			imodel.next(DETAILED_PROJECT);
			return;
		}
		modelOptions = imodel.getModelOptions();
		project = repository.getProjectById(modelOptions.intSelectedProject);
		double amount = 0;
		try {
			int selected = Integer.parseInt(message);
			amount = project.amount[selected - 1];

		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			imodel.goToAndBack(ERROR_PAGE, INVEST_PAGE);
			return;
		}
		modelOptions.intOption = intOption;
		modelOptions.amountToInvest = Double.toString(amount);
		imodel.nextWithOptions(APPLY_TRANSACTION_PAGE, modelOptions);
	}
}
