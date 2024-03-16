package com.example.astaralgorithm;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Node{
    Node parent;
    int column;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;
    Rectangle rectangle;

    public Node(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public void setAsStart() {
        start = true;
        rectangle.setFill(Color.BLUE);
    }

    public void setAsGoal() {
        goal = true;
        rectangle.setFill(Color.GREEN);
    }

    public void setAsSolid() {
        solid = true;
        rectangle.setFill(Color.BLACK);
    }

    public void setAsOpen() {
        open = true;
    }

    public void setAsChecked() {
        if (!start && !goal) {
            rectangle.setFill(Color.ORANGE);
        }
        checked = true;
    }

    public void setAsPath() {
        rectangle.setFill(Color.RED);
    }
}
