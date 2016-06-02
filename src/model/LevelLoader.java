package model;


import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Антон on 25.03.2016.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
        level = (level - 1) % 60 + 1;
        GameObjects gameObjects = null;
        final int step = Model.FIELD_SELL_SIZE / 2;
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile())))
        {
            String line = null;
            while (reader.ready())
            {
                line = reader.readLine();
                if (line.contains("Maze:") && line.contains(String.valueOf(level)))
                {
                    break;
                }
            }
            reader.readLine();
            line = reader.readLine();
            int width = Integer.parseInt(line.substring(8));
            line = reader.readLine();
            int height = Integer.parseInt(line.substring(8));
            reader.readLine();
            reader.readLine();
            reader.readLine();
            Player player = null;
            Set<Wall> walls = new HashSet<>();
            Set<Box> boxes = new HashSet<>();
            Set<Home> homes = new HashSet<>();
            for (int y = 0; y < height; y++)
            {
                line = reader.readLine();
                char[] sequance = line.toCharArray();
                for (int x = 0; x < width; x++)
                {
                    switch (sequance[x])
                    {
                        case ' ':

                            break;
                        case 'X':
                            Wall wall = new Wall(step + x * 2 * step, step + y * 2 * step);
                            walls.add(wall);
                            break;
                        case '.':
                            Home home = new Home(step + x * 2 * step, step + y * 2 * step);
                            homes.add(home);
                            break;
                        case '*':
                            Box box = new Box(step + x * 2 * step, step + y * 2 * step);
                            boxes.add(box);
                            break;
                        case '@':
                            player = new Player(step + x * 2 * step, step + y * 2 * step);
                            break;
                        case '&':
                            Home home2 = new Home(step + x * 2 * step, step + y * 2 * step);
                            homes.add(home2);
                            Box box2 = new Box(step + x * 2 * step, step + y * 2 * step);
                            boxes.add(box2);
                    }
                }
            }
            gameObjects = new GameObjects(walls, boxes, homes, player);
        }
        catch (IOException e)
        {
        }
        return gameObjects;
    }
}
