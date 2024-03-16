package com.example.astaralgorithm;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Screen {
    final int maxColumn = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int screenWidth = nodeSize * maxColumn;
    final int screenHeight = nodeSize * maxRow;
    boolean goalReached = false;

    Node[][] node = new Node[maxColumn][maxRow];
    Node startNode, goalNode, currentNode;

    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    Group root = new Group();

    private Scene scene = new Scene(root, screenWidth, screenHeight);
    public Screen() {
        for (int column = 0; column < maxColumn; column++) {
            for (int row = 0; row < maxRow; row++) {
                node[column][row] = new Node(column,row);
                node[column][row].rectangle = new Rectangle( column * nodeSize + 1, row * nodeSize + 1,nodeSize - 1, nodeSize - 1);
                node[column][row].rectangle.setFill(Color.WHITE);
                root.getChildren().add(node[column][row].rectangle);
            }
        }
        setSolidNode(10,2);
        setSolidNode(10,3);
        setSolidNode(10,4);
        setSolidNode(10,5);
        setSolidNode(10,6);
        setSolidNode(10,7);
        setSolidNode(6,2);
        setSolidNode(7,2);
        setSolidNode(8,2);
        setSolidNode(9,2);
        setSolidNode(9,7);
        setSolidNode(8,7);
        setSolidNode(7,7);
        setSolidNode(6,7);
        setSolidNode(5,7);
        setSolidNode(4,7);

        setStartNode(5,4);
        setGoalNode(14, 8);

        setCostOnNodes();

        scene.setFill(Color.BLACK);
    }

    private void setStartNode(int column, int row) {
        node[column][row].setAsStart();
        startNode = node[column][row];
        currentNode = startNode;
    }

    private void setGoalNode(int column, int row) {
        node[column][row].setAsGoal();
        goalNode = node[column][row];
    }

    private void setSolidNode(int column, int row) {
        node[column][row].setAsSolid();
    }

    private void setCostOnNodes() {
        for (int column = 0; column < maxColumn; column++) {
            for (int row = 0; row < maxRow; row++) {
                getCost(node[column][row]);
            }
        }
    }

    private void getCost(Node node) {
        int xDistance = Math.abs(node.column - startNode.column);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        xDistance = Math.abs(node.column - goalNode.column);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        node.fCost = node.gCost + node.hCost;

    }

    public void search() {
        if (!goalReached) {
            int column = currentNode.column;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            if (row - 1 >= 0) {
                openNode(node[column][row - 1]);
            }
            if (column - 1 >= 0) {
                openNode(node[column - 1][row]);
            }
            if (row + 1 < maxRow) {
                openNode(node[column][row + 1]);
            }
            if (column + 1 < maxColumn) {
                openNode(node[column + 1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
    }

    public void trackThePath() {
        Node current = goalNode;

        while (current != startNode) {
            current = current.parent;
            if (current != startNode) {
                current.setAsPath();
            }
        }
    }

    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }
    Scene getScene() {
        return scene;
    }
}
