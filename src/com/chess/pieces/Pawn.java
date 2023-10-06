package com.chess.pieces;

import javafx.scene.shape.Rectangle;

public class Pawn implements Piece {
    private int xPos;
    private int yPos;
    private Rectangle rectangle;
    public Pawn(int x, int y) {
        xPos = x;
        yPos = y;
    }
    public void move(int x, int y) {
        if (y > 2 || y < -2) {
            System.out.println("Can't move that far! (Pawn)");
            return;
        }
        if (x != 0) {
            System.out.println("Pawn can only move forward!");
            return;
        }
        yPos = yPos + y;
    }

    public int attack(int lr) {
        int newX = lr + xPos;
        yPos++;
        xPos = newX;
        return xPos;
    }

    public int getX() {
        return xPos;
    }
    public int getY() {
        return yPos;
    }
    public void setX(int x) {xPos = x;}
    public void setY(int y) {yPos = y;}
    public Rectangle getRectangle() {return rectangle;}
    public void setRectangle(Rectangle r) {rectangle = r;}

    public String toString() {
        return "p";
    }
}
