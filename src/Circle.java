import processing.core.PApplet;

public class Circle extends Shape {

    public Circle(int x, int y, int color) {
        super(x, y, color);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(getColor());
        pApplet.ellipse(x, y, 40, 40);
    }

    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }

    @Override
    public boolean checkCollision(int cx, int cy) {
        return (cx - x) * (cx - x) + (cy - y) * (cy - y) < 20 * 20;
    }

}
