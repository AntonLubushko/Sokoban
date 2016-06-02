package model;


import java.awt.*;

/**
 * Created by Антон on 24.03.2016.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        Color color=new Color(111, 61, 15);

        graphics.setColor(color);

        graphics.fillRect(getX(),getY(),getWidth(),getHeight());

    }
}
