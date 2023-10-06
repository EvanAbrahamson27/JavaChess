package com.chess.pieces;

import javafx.scene.shape.Rectangle;

public class King implements Piece {
    private int xPos;
    private int yPos;
    private Rectangle rectangle;
    private int team;
    private boolean kill;
    public boolean moveFail;

    King(int x, int y) {
        xPos = x;
        yPos = y;
        if (y > 3) {
            team = 1;
        }
    }

    public void move(int x, int y) {
        moveFail = false;
        if (x != 0 && y != 0) {
            System.out.println("King can only move one way!");
            moveFail = true;
            return;
        }
        if (x > 1 || y > 1) {
            System.out.println("Can't move that far! (King)");
            moveFail = true;
            return;
        }
        if (x == 0 && y == 0) {
            System.out.println("Must move.");
            moveFail = true;
            return;
        }
        xPos = xPos + x;
        yPos = yPos + y;
    }

    public void attack(Piece p) {

    }

    public int getX() {return xPos;}
    public int getY() {
        return yPos;
    }
    public void setX(int x) {xPos = x;}
    public void setY(int y) {yPos = y;}
    public int getTeam() {return team;}
    public Rectangle getRectangle() {return rectangle;}
    public void setRectangle(Rectangle r) {rectangle = r;}
    public void setKill(boolean b) {
        kill = b;
    }
    public boolean getKill() {return kill;}
    public boolean getMoveFail() {return moveFail;}

    public String toString() {
        if (team == 1) {
            return "K";
        }
        return "k";
    }
}
