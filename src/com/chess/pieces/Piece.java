package com.chess.pieces;

import javafx.scene.shape.Rectangle;

public interface Piece {
    public void move(int x, int y);

    public int attack(int lr);

    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public Rectangle getRectangle();
    public void setRectangle(Rectangle r);

    public String toString();
}
