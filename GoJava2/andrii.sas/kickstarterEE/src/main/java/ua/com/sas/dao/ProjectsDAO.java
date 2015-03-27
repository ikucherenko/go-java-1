package ua.com.sas.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Component;

import ua.com.sas.model.Project;
import ua.com.sas.model.Projects;

@Component
public class ProjectsDAO extends AbstractDAO implements Projects{

	@Override
	public void add(Project project) {
		Session session = getSession();
        session.save(project);
        session.close();
	}

	@Override
	public Project get(int id) {
		Session session = getSession();
	    Project project = (Project) session.get(Project.class, id);
	    session.close();
	    return project;
	}

	@Override
	public int size() {
		Session session = getSession();
		int size = 0;
		Criteria criteria = session.createCriteria(Project.class);
		criteria.setProjection(Projections.rowCount());
	    Long count = (Long) criteria.uniqueResult();
	    size = count.intValue();
	    criteria.setProjection(null);
	    criteria.setResultTransformer(Criteria.ROOT_ENTITY);
	    session.close();
		return size;
	}

}
