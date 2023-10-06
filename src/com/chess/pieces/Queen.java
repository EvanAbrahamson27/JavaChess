package com.chess.pieces;

import javafx.scene.shape.Rectangle;

public class Queen implements Piece {
    private int xPos;
    private int yPos;
    private Rectangle rectangle;

    Queen(int x, int y) {
        xPos = x;
        yPos = y;
    }
    public void move(int x, int y) {
        if ((x != 0 && y != 0) && Math.abs(x) != Math.abs(y)) {
            System.out.println("Cannot move like that. (Queen)");
            return;
        }
        xPos = xPos + x;
        yPos = yPos + y;
    }

    public int attack(int lr) {
        return 0;
    }

    public int getX() {return xPos;}
    public int getY() {
        return yPos;
    }
    public void setX(int x) {xPos = x;}
    public void setY(int y) {yPos = y;}
    public Rectangle getRectangle() {return rectangle;}
    public void setRectangle(Rectangle r) {rectangle = r;}

    public String toString() {
        return "q";
    }
}
