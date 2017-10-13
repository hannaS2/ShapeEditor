import processing.core.PApplet;

public abstract class Shape implements Cloneable {
    int x;
    int y;
    int color;

    public Shape(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract void draw(PApplet pApplet);

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkCollision(int cx, int cy) {
        return false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

}
