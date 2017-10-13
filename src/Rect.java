import processing.core.PApplet;

public class Rect extends Shape {

    public Rect(int x, int y, int color) {
        super(x, y, color);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(getColor());
        pApplet.rect(x, y, 40, 40);
    }

    @Override
    public Rect clone() {
        return (Rect) super.clone();
    }

    @Override
    public boolean checkCollision(int cx, int cy) {
        return cx - 20 < x && cx + 20 > x && cy + 20 > y && cy - 20 < y;
    }

}
