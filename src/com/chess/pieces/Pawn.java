package com.chess.pieces;

import javafx.scene.shape.Rectangle;

public class Pawn implements Piece {
    private int xPos;
    private int yPos;
    private Rectangle rectangle;
    private int team;
    private boolean kill;
    public boolean moveFail;
    public Pawn(int x, int y) {
        xPos = x;
        yPos = y;
        if (y > 3) {
            team = 1;
        }
    }
    public void move(int x, int y) {
        moveFail = false;
        if (y > 2 || y < -2 || (y == 2 && yPos > 1) || (y == -2 && yPos < 6)) {
            System.out.println("Can't move that far! (Pawn)");
            moveFail = true;
            return;
        }
        if (x != 0) {
            System.out.println("Pawn can only move forward!");
            moveFail = true;
            return;
        }
        if (x == 0 && y == 0) {
            System.out.println("Must move.");
            moveFail = true;
            return;
        }
        if ((team == 1 && y > 0) || (team == 0 && y < 0)) {
            System.out.println("Can't move backwards.");
            moveFail = true;
            return;
        }
        yPos = yPos + y;
    }

    public void attack(Piece p) {
        int tempX = p.getX();
        int tempY = p.getY();
        xPos = tempX;
        yPos = tempY;
    }

    public int getX() {
        return xPos;
    }
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
            return "P";
        }
        return "p";
    }
}
