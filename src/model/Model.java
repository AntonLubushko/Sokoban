package model;

import controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by Антон on 24.03.2016.
 */
public class Model
{
    public static int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private static int level;

    {
        level = currentLevel;
    }

    private LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\JavaRushHomeWork\\src" +
            "\\com\\javarush\\test\\level34\\lesson15\\big01\\res\\levels.txt"));


    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        currentLevel++;
        level=currentLevel;
        restartLevel(currentLevel);
    }

    public void move(Direction direction)
    {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollision(direction)) return;
        int count = FIELD_SELL_SIZE;
        switch (direction)
        {
            case LEFT:
                player.move(-count, 0);
                break;
            case RIGHT:
                player.move(count, 0);
                break;
            case UP:
                player.move(0, -count);
                break;
            case DOWN:
                player.move(0, count);
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        for (Wall wall : gameObjects.getWalls())
        {
            if (gameObject.isCollision(wall, direction)) return true;
        }

        return false;
    }

    public boolean checkBoxCollision(Direction direction)
    {
        Player player = gameObjects.getPlayer();
        Box neighborBox = null;
        for (Box box : gameObjects.getBoxes())
        {
            if (player.isCollision(box, direction))
            {
                neighborBox = box;
                break;
            }
        }
        if (neighborBox != null)
        {
            for (Box box : gameObjects.getBoxes())
            {
                if (neighborBox.isCollision(box, direction)) return true;
            }

            if (checkWallCollision(neighborBox, direction)) return true;

            int count = FIELD_SELL_SIZE;
            switch (direction)
            {
                case LEFT:
                    neighborBox.move(-count, 0);
                    break;
                case RIGHT:
                    neighborBox.move(count, 0);
                    break;
                case UP:
                    neighborBox.move(0, -count);
                    break;
                case DOWN:
                    neighborBox.move(0, count);
            }
        }
        return false;

    }

    public void checkCompletion()
    {
        int countFilledBoxes = 0;
        for (Home home : gameObjects.getHomes())
        {
            for (Box box : gameObjects.getBoxes())
            {
                if (home.getX() == box.getX() && home.getY() == box.getY())
                {
                    countFilledBoxes++;
                }
            }
        }
        if (countFilledBoxes == gameObjects.getHomes().size())
        {

            eventListener.levelCompleted(currentLevel);
        }

    }

    public static int getCurrentLevel()
    {
        return level;
    }
}
