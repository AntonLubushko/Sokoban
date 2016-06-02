package model;

import java.awt.*;

/**
 * Created by Антон on 24.03.2016.
 */
public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        Color color=new Color(90, 42, 225);
        graphics.setColor(color);


       graphics.fillOval(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void move(int x, int y)
    {
        setX(getX()+x);
        setY(getY()+y);
    }
}
