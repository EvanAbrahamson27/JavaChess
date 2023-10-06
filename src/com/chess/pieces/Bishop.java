package com.chess.pieces;

import javafx.scene.shape.Rectangle;

public class Bishop implements Piece {

    private int xPos;
    private int yPos;
    private Rectangle rectangle;

    Bishop(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void move(int x, int y) {
        if (Math.abs(x) != Math.abs(y)) {
            System.out.println("Bishop has to move diagonally!" + x + " " + y);
            return;
        }
        xPos = xPos + x;
        yPos = yPos + y;
    }

    public int attack(int lr) {
        return 0;
    }
    public int getX() {return xPos;}
    public int getY() {return yPos;}
    public void setX(int x) {xPos = x;}
    public void setY(int y) {yPos = y;}
    public Rectangle getRectangle() {return rectangle;}
    public void setRectangle(Rectangle r) {rectangle = r;}

    public String toString() {
        return "b";
    }
}
