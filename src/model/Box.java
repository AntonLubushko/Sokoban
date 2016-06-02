package model;

import java.awt.*;

/**
 * Created by Антон on 24.03.2016.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        Color color=new Color(223, 179, 19);
        graphics.setColor(color);

        graphics.fillRect(getX()+1,getY()+1,getWidth()-2,getHeight()-2);


    }

    @Override
    public void move(int x, int y)
    {
        setX(getX()+x);
        setY(getY()+y);
    }
}
