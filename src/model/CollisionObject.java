package model;

/**
 * Created by Антон on 24.03.2016.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        int x = this.getX();
        int y = this.getY();
        switch (direction)
        {
            case DOWN:
                y += Model.FIELD_SELL_SIZE;
                break;
            case UP:
                y -= Model.FIELD_SELL_SIZE;
                break;
            case LEFT:
                x -= Model.FIELD_SELL_SIZE;
                break;
            case RIGHT:
                x += Model.FIELD_SELL_SIZE;
                break;
        }
        if(x==gameObject.getX()&&y==gameObject.getY())return true;
        return false;
    }
}
