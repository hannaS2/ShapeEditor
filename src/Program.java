import processing.core.PApplet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program extends PApplet {

    private List<Shape> shapes = new ArrayList<>();
    private Shape selectedShape;
    private int offsetX;
    private int offsetY;
    private static final String FILE_PATH = "/Users/janghanna/IdeaProjects/170924/shape.txt";
    private boolean control;
    private boolean d;
    private boolean s;
    private boolean o;

    @Override
    public void settings() {
        this.size(500, 500);
    }

    @Override
    public void setup() {
        rectMode(CENTER);
        // draw에 필요한 값 초기화
        this.background(255);
    }

    @Override
    public void draw() {
        // 무한 루프 : 실제 드로잉
        background(255);
        for (Shape e : shapes) {
            e.draw(this);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Program");
    }

    @Override
    public void mousePressed() {
        Shape shape = null;

        if (!keyPressed)
            return;

        if (key == '1') {
            shapes.add(new Rect(mouseX, mouseY, 255));
        } else if (key == '2') {
            shapes.add(new Circle(mouseX, mouseY, 255));
        } else if (key == '3') {
            shapes.add(new Triangle(mouseX, mouseY, 255));
        } else if (key == 'D' || key == 'd') {
            Shape s = findShape(mouseX, mouseY);
            if (s != null) {
                shape = s.clone();
                shape.setX(s.getX() + 45);
            }
        }
        if (shape == null) {
            return;
        }
        shapes.add(shape);
    }

    @Override
    public void keyPressed() {
        if (key == 's') {
            saveFile();
        } else if (key == 'o') {
            openFile();
        }
    }
    
    @Override
    public void mouseDragged() {
        if (selectedShape == null)
            selectedShape = findShape(mouseX, mouseY);

        if (selectedShape != null) {
            selectedShape.setX(mouseX - offsetX);
            selectedShape.setY(mouseY - offsetY);
            selectedShape.setColor(0);
        }
    }

    @Override
    public void mouseReleased() {
        if (selectedShape != null) {
            selectedShape.setColor(255);
        }
        selectedShape = null;
        offsetX = 0;
        offsetY = 0;
    }

    private Shape findShape(int posX, int posY) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).checkCollision(posX, posY)) {
                offsetX = posX - shapes.get(i).getX();
                offsetY = posY - shapes.get(i).getY();
                return shapes.get(i);
            }
        }
        return null;
    }

    private void saveFile() {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            for (Shape s : shapes) {
                bos.write((s.getClass() + "/" + s.getX() + "/" + s.getY() + "/").getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void openFile() {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int ch;
            String str = "";

            while ((ch = bis.read()) != -1) {
                str += (char) ch;
            }

            createShapeFromString(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createShapeFromString(String str) {
        shapes = new ArrayList<>();
        String data[] = str.split("/");

        for (int i = 0; i < data.length; i += 3) {
            String type = data[i];
            int posX = parseInt(data[i+1]);
            int posY = parseInt(data[i+2]);

            if(type.contains("Rect")) {
                shapes.add(new Rect(posX, posY, 255));
            } else if(type.contains("Circle")) {
                shapes.add(new Circle(posX, posY, 255));
            } else if (type.contains("Triangle")) {
                shapes.add(new Triangle(posX, posY, 255));
            }
        }

    }
}
