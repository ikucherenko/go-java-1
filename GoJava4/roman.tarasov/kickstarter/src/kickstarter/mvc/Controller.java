package kickstarter.mvc;

import kickstarter.dao.interfaces.iDAO;
import kickstarter.mvc.interfaces.iController;
import kickstarter.mvc.interfaces.iModel;
import kickstarter.mvc.interfaces.iView;

public class Controller implements iController {

	private iView iview;
	private iModel imodel;
	private iDAO dao;
	private iDAO defaultDAO;
	private iDAO databaseDAO;

	public void showPage() {
		iview.showPage();
	}

	public void updateStateOfModel(String message) {
		imodel.updateStateOfModel(message);
	}

	public void setPage(int currentPage) {
		imodel.setPage(currentPage);
	}

	public void setModel(iModel setModel) {
		iview.setModel(setModel);
		imodel.setModel(setModel);
	}

	public void setView(iView setView) {
		iview.setView(setView);
		imodel.setView(setView);
	}

	public void setDao(iDAO idao) {
		iview.setIDAO(idao);
		imodel.setIDAO(idao);
	}

	@Override
	public void setInterfaces(iView iview, iModel imodel) {
		this.iview = iview;
		this.imodel = imodel;
	}

	@Override
	public iDAO getDao() {
		return dao;
	}

	@Override
	public void setDaoInterfaces(iDAO defaultDAO, iDAO databaseDAO) {
		this.defaultDAO = defaultDAO;
		this.databaseDAO = databaseDAO;
	}

	@Override
	public void setDefaultDAO() {
		setDao(defaultDAO);
	}

	@Override
	public void setDatabaseDAO() {
		setDao(databaseDAO);
	}
}