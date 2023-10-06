package com.chess.pieces;

import javafx.scene.shape.Rectangle;

public interface Piece {
    public void move(int x, int y);

    public void attack(Piece p);

    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public int getTeam();
    public boolean getKill();
    public void setKill(boolean b);
    public Rectangle getRectangle();
    public void setRectangle(Rectangle r);
    public boolean getMoveFail();

    public String toString();
}
