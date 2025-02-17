// Brush.java
package org.example.pharmacymanagmentfrontend.Model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Brush {
    private int size;
    private Color color;
    public enum BrushType { CIRCLE, SQUARE, TRIANGLE }
    private BrushType type;

    public Brush() {
        this.size = 2;
        this.color = Color.BLACK;
        this.type = BrushType.CIRCLE;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BrushType getType() {
        return type;
    }

    public void setType(BrushType type) {
        this.type = type;
    }

    public void drawDot(int x, int y, Group group) {
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setFill(this.color);
        circle.setRadius(this.size);
        group.getChildren().add(circle);
    }
}