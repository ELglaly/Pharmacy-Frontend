package org.example.pharmacymanagmentfrontend.Model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Brush {
    private int size;
    private Color color;
    public enum BrushType{CIRCLE,SQUARE, TRIANGLE};
    private BrushType type;

    public Brush() {
        size=2;
        color=Color.BLACK;
        type=BrushType.CIRCLE;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public BrushType getType() {
        return type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(BrushType type) {
        this.type = type;
    }

    public void drawDot(int x, int y, Group group) {
                Circle circle = new Circle();
                circle.setCenterX(x);
                circle.setCenterY(y);
                circle.setFill(this.getColor());
                circle.setRadius(this.getSize());

        group.getChildren().add(circle);
    }
}
