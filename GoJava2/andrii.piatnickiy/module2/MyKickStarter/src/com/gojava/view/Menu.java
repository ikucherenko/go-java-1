package com.gojava.view;

import java.util.ArrayList;

import com.gojava.projects.ProjectManager;

public class Menu {
    ProjectManager manager = new ProjectManager();
    private int currentLevelPosition;
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    int categoryPosition;

    private ArrayList<Level> levelsList = new ArrayList<>();

    public Menu() {
        this.level1 = new Level1(manager);
        this.level2 = new Level2(manager);
        this.level3 = new Level3(manager);
        level3.setMenu(this);
        levelsList.add(level1);
        levelsList.add(level2);
        levelsList.add(level3);
        currentLevelPosition = 1;
        initMenu();
    }

    public void initMenu() {
        level1.displayMySelf(currentLevelPosition);
    }

    
    public void nextLevel(int nubberForNextLevel) {
        beyondLevel(nubberForNextLevel);
    }

    private void beyondLevel(int nubberForNextLevel) {
        Level level;
        if ((nubberForNextLevel == 0 && currentLevelPosition == 1) || (nubberForNextLevel > 0 && currentLevelPosition == 3)) {
            System.out.println("not allowed to go below this level");
        }else{
            if (nubberForNextLevel == 0) {
                currentLevelPosition--;
                if (currentLevelPosition == 2) {
                    level = getCurrentLevel(categoryPosition);
                    level.displayMySelf(categoryPosition);
                } else {
                    level = getCurrentLevel(nubberForNextLevel);
                    level.displayMySelf(nubberForNextLevel);
                }
            } else {
                currentLevelPosition++;
                if (currentLevelPosition == 2) {
                    categoryPosition = nubberForNextLevel;
                }
                level = getCurrentLevel(nubberForNextLevel);
                level.displayMySelf(nubberForNextLevel);
            }
        } 
    }

    private Level getCurrentLevel(int nubberForNextLevel) {
        for (Level level : levelsList) {
            if (currentLevelPosition == level.getPosition()) {
                return level;
            }
        }
    return null;
    }
}
