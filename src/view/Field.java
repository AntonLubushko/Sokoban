package view;

import controller.EventListener;
import model.Direction;
import model.GameObject;
import model.Home;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Антон on 24.03.2016.
 */
public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;

    public Field(View view)
    {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g)
    {
        Color color = new Color(220, 216, 216);
        g.setColor(color);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        Set<GameObject> gameObjects = view.getGameObjects().getAll();
        Set<Home> homes = new HashSet<>();
        int max = 0;
        for (GameObject gameObject : gameObjects)
        {
            if ((gameObject instanceof Home))
            {
                homes.add((Home) gameObject);
            }
        }
        for (Home home : homes)
        {
            home.draw(g);
        }
        for (GameObject gameObject : gameObjects)
        {
            if (!(gameObject instanceof Home))
            {
                gameObject.draw(g);
                if (max < gameObject.getY()) max = gameObject.getY();
            }
        }
        g.setColor(Color.black);
        g.drawString("Уровень " + Model.getCurrentLevel() + " из 60. Используй стрелки для движения, R - перезапуск уровня", Model.FIELD_SELL_SIZE, max + 2 * Model.FIELD_SELL_SIZE);


    }

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;

                case KeyEvent.VK_RIGHT:

                    eventListener.move(Direction.RIGHT);
                    break;

                case KeyEvent.VK_UP:

                    eventListener.move(Direction.UP);
                    break;

                case KeyEvent.VK_DOWN:

                    eventListener.move(Direction.DOWN);
                    break;

                case KeyEvent.VK_R:

                    eventListener.restart();
                    break;
                case KeyEvent.VK_N:
                    eventListener.startNextLevel();
                    break;
            }
        }
    }
}
