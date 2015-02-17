package com.gojava.projects;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileCategoryStorage implements CategoryStorage {
    File file;
    BufferedReader in = null;
    BufferedWriter out = null;
    public ArrayList<Category> categoriesList = new ArrayList<Category>();

    public FileCategoryStorage(String fileName) {
        file = createFileIfNeed(fileName);
        if(file.length() != 0){
            file.delete();
            createFileIfNeed(fileName);
        }
        getCategoriesFromFileToList();
    }

    @Override
    public void add(String name, int categoryId) {
        try {
            initOut();
            try {
                out.append(String.valueOf(categoryId) + ";" + name + "\n");
            } catch (IOException e) {
                throw new RuntimeException("Не могу записать строку!", e);
            }

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException("Не можем закрыть файл!", e);
                }
            }
        }
    }

    @Override
    public String getCategoryToString() {
        StringBuffer sb = new StringBuffer();
        for (Category category : categoriesList) {
            sb.append(category.toString()).append("\n");
        }
        return sb.toString();

    }

    @Override
    public Category getCategory(int index) {
        return categoriesList.get(index);
    }

    public ArrayList<Category> getCategoriesFromFileToList() {
        initIn();
        Category category;
        try {
            try {
                String read;
                read = in.readLine();
                while (read != null) {
                    category = parseLineToCategory(read);
                    categoriesList.add(category);
                    read = in.readLine();
                }

            } catch (IOException e) {
                throw new RuntimeException("Не могу прочитать строку!", e);
            }

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("Не можем закрыть файл!", e);
                }
            }
        }
        return categoriesList;
    }

    private Category parseLineToCategory(String read) {
        Category category;
        String result;
        result = read;
        String[] tmp = result.split(";");
        int id = Integer.parseInt(tmp[0]);
        String name = tmp[1];
        category = new Category(name, id);
        return category;
    }

    private void initOut() {
        try {
            out = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
            throw new RuntimeException("Не могу записать в файл!", e);
        }
    }

    private File createFileIfNeed(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Не смогли создать файл контейнер!",
                        e);
            }
        }
        return file;
    }

    private void initIn() {
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            try {
                file.createNewFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                throw new RuntimeException("Не смогли создать файл!", e);
            }
        }
    }

    @Override
    public ArrayList<Category> getList() {
        return categoriesList;
    }
}
