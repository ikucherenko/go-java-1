package com.morkva;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.logic.ConsolePrinter;
import com.morkva.logic.ConsoleReader;
import com.morkva.logic.Printer;
import com.morkva.logic.Reader;
import com.morkva.model.CategoryRepositoryImpl;
import com.morkva.model.ProjectRepositoryImpl;
import com.morkva.model.QuoteRepositoryImpl;
import com.morkva.model.dao.DAO;
import com.morkva.model.dao.DAOFactory;
import com.morkva.model.dao.PersistException;
import com.morkva.model.dao.jdbc.mysql.MySQLDaoFactory;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

/**
 * Created by vladyslav on 07.05.15.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        initTempData();
        DAOFactory<Connection> daoFactory = new MySQLDaoFactory();

        Printer printer = new ConsolePrinter();
        Reader reader = new ConsoleReader();
        KickstarterApp app = new KickstarterApp(printer, reader);
        app.setQuoteRepository(new QuoteRepositoryImpl(daoFactory));
        try {
            app.setCategoryRepository(new CategoryRepositoryImpl(daoFactory.getDao(daoFactory.getContext(), Category.class)));
        } catch (PersistException e) {
            e.printStackTrace();
        }
        try {
            app.setProjectRepository(new ProjectRepositoryImpl(daoFactory.getDao(daoFactory.getContext(), Project.class)));
        } catch (PersistException e) {
            e.printStackTrace();
        }
        app.run();
    }

    public static void initTempData() {
        DAOFactory<Connection> daoFactory = new MySQLDaoFactory();
        Connection connection = null;
        DAO<Category, Integer> categoryDao;
        DAO<Project, Integer> projectDao;

        try {
            connection = daoFactory.getContext();
            categoryDao = daoFactory.getDao(connection, Category.class);
            projectDao = daoFactory.getDao(connection, Project.class);

            for (int i = 0; i < 5; i++) {
                categoryDao.persist(new Category("Name " + i));
            }

            Category category = categoryDao.getByPK(1);
            for (int i = 0; i < 5; i++) {
                projectDao.persist(new Project("Name " + i, "Short description " + i, new Random().nextInt(10) * i, 0, 30, "History " + i, "Url video " + i, category));
            }

        } catch (PersistException e) {
            e.printStackTrace();
        }
    }
}
