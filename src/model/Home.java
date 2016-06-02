package model;

import java.awt.*;

/**
 * Created by Антон on 24.03.2016.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y);
        setHeight(3);
        setWidth(3);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.red);
        graphics.drawOval(getX()+Model.FIELD_SELL_SIZE/2,getY()+Model.FIELD_SELL_SIZE/2,getWidth(),getHeight());
    }
}
