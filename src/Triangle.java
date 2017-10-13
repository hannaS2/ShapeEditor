import processing.core.PApplet;

public class Triangle extends Shape {

    public Triangle(int x, int y, int color) {
        super(x, y, color);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(getColor());
        pApplet.triangle((x - 20), y + 20, x, y - 20, (x + 20), y + 20);
    }

    @Override
    public Triangle clone() {
        return (Triangle) super.clone();
    }

    @Override
    public boolean checkCollision(int cx, int cy) {
        double a1 = (getX()-(0.5)*getY()+10-cx+0.5*cy)*0.025;
        double a2 = (20+getY()-cy)*0.025;
        double a3 = (10-getX()-(getY()*0.5)+cx+(cy*0.5))*0.025;

        return a1>=0 && a2>=0 && a3>=0;
    }

}
