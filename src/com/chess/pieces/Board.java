package com.chess.pieces;

import java.util.Random;

public class Board {
    public static final int X = 8;
    public static final int Y = 8;
    public Piece[][] board;
    private Random random = new Random();

    // Generate a random int (0 or 1)
    public int teamTurn = random.nextInt(2);

    public Board() {
        board = new Piece[X][Y];
    }

    public void initializeBoard() {
        System.out.println("\nInitializing Board...");

        for (int i = 0; i <= 7; i++) {
            board[i][1] = new Pawn(i, 1);
            board[i][6] = new Pawn(i, 6);
        }
        board[0][0] = new Rook(0,0);
        board[0][7] = new Rook(0,7);
        board[7][0] = new Rook(7,0);
        board[7][7] = new Rook(7,7);
        board[2][0] = new Bishop(2,0);
        board[5][0] = new Bishop(5,0);
        board[2][7] = new Bishop(2,7);
        board[5][7] = new Bishop(5,7);
        board[4][0] = new King(4,0);
        board[4][7] = new King(4,7);
        board[3][0] = new Queen(3,0);
        board[3][7] = new Queen(3,7);
        board[1][0] = new Knight(1,0);
        board[6][0] = new Knight(6,0);
        board[1][7] = new Knight(1,7);
        board[6][7] = new Knight(6,7);

        System.out.println("Board Initialized.\n");
    }

    public void move(Piece p, int x, int y) {
        // If there's no piece, don't do anything
        if (p == null) {
            return;
        }

        if (teamTurn != p.getTeam()) {
            System.out.println("Not your turn!");
            return;
        }

        // Get original coordinates
        int tempX = p.getX();
        int tempY = p.getY();

        // Update the .Piece's x and y coordinates
        p.move(x, y);

        // If piece is directly in front, then stop (for forward movements: pawn, queen, king, rook)
        if (x == 0 && y > 0) {
            for (int i = tempY + 1; i <= (tempY + y); i++) {
                if (board[p.getX()][i] != null) {
                    p.setX(tempX);
                    p.setY(tempY);
                    System.out.println("Piece in the way! ^");
                    return;
                }
            }
        }

        // If piece is directly behind, then stop (for backwards movements: queen, king, rook)
        if (x == 0 && y < 0) {
            for (int i = tempY - 1; i >= (tempY + y); i--) {
                if (board[p.getX()][i] != null) {
                    p.setX(tempX);
                    p.setY(tempY);
                    System.out.println("Piece in the way! v");
                    return;
                }
            }
        }

        // If piece is directly left, then stop (for left movements: queen, king, rook)
        if (x < 0 && y == 0) {
            for (int i = tempX - 1; i >= (tempX + x); i--) {
                if (board[i][p.getY()] != null) {
                    p.setX(tempX);
                    p.setY(tempY);
                    System.out.println("Piece in the way! <");
                    return;
                }
            }
        }

        // If piece is directly right, then stop (for right movements: queen, king, rook)
        if (x > 0 && y == 0) {
            for (int i = tempX + 1; i <= (tempX + x); i++) {
                if (board[i][p.getY()] != null) {
                    if ((p.getClass().equals(King.class) || p.getClass().equals(Rook.class)
                            || p.getClass().equals(Queen.class))
                            && p.getTeam() != board[i][p.getY()].getTeam() && p.getTeam() == 1) {
                        p.attack(board[i][p.getY()]);
                        board[i][p.getY()] = null;
                        p.setKill(true);
                        teamSwitch();
                        break;
                    }
                    p.setX(tempX);
                    p.setY(tempY);
                    System.out.println("Piece in the way! >");
                    return;
                }
            }
        }

        // If piece is up and right, then stop (for up-right diagonal movements: queen, bishop)
        if (x == y && x > 0) {
            for (int i = tempX + 1; i <= (tempX + x); i++) {
                for (int j = tempY + 1; j <= (tempY + y); j++){
                    if (board[i][j] != null) {
                        if ((p.getClass().equals(Pawn.class) || p.getClass().equals(Bishop.class)
                                || p.getClass().equals(Queen.class))
                                && p.getTeam() != board[i][j].getTeam() && p.getTeam() == 0) {
                            p.attack(board[i][j]);
                            board[i][j] = null;
                            p.setKill(true);
                            teamSwitch();
                            break;
                        }
                        p.setX(tempX);
                        p.setY(tempY);
                        System.out.println("Piece in the way! ^>");
                        return;
                    }
                }
            }
        }

        // If piece is up and left, then stop (for up-left diagonal movements: queen, bishop)
        if (x == (-y) && x < 0) {
            for (int i = tempX - 1; i >= (tempX + x); i--) {
                for (int j = tempY + 1; j <= (tempY + y); j++){
                    if (board[i][j] != null) {
                        if ((p.getClass().equals(Pawn.class) || p.getClass().equals(Bishop.class)
                                || p.getClass().equals(Queen.class))
                                && p.getTeam() != board[i][j].getTeam() && p.getTeam() == 0) {
                            p.attack(board[i][j]);
                            board[i][j] = null;
                            p.setKill(true);
                            teamSwitch();
                            break;
                        }
                        p.setX(tempX);
                        p.setY(tempY);
                        System.out.println("Piece in the way! <^");
                        return;
                    }
                }
            }
        }

        // If piece is down and right, then stop (for down-right diagonal movements: queen, bishop)
        if (x == -y && x > 0) {
            for (int i = tempX + 1; i <= (tempX + x); i++) {
                for (int j = tempY - 1; j >= (tempY + y); j--){
                    if (board[i][j] != null) {
                        if ((p.getClass().equals(Pawn.class) || p.getClass().equals(Bishop.class)
                                || p.getClass().equals(Queen.class))
                                && p.getTeam() != board[i][j].getTeam() && p.getTeam() == 1) {
                            p.attack(board[i][j]);
                            board[i][j] = null;
                            p.setKill(true);
                            teamSwitch();
                            break;
                        }
                        p.setX(tempX);
                        p.setY(tempY);
                        System.out.println("Piece in the way! v>");
                        return;
                    }
                }
            }
        }

        // If piece is down and left, then stop (for down-left diagonal movements: queen, bishop)
        if (x == y && x < 0) {
            for (int i = tempX - 1; i >= (tempX + x); i--) {
                for (int j = tempY - 1; j >= (tempY + y); j--){
                    if (board[i][j] != null) {
                        if ((p.getClass().equals(Pawn.class) || p.getClass().equals(Bishop.class)
                                || p.getClass().equals(Queen.class))
                                && p.getTeam() != board[i][j].getTeam() && p.getTeam() == 1) {
                            p.attack(board[i][j]);
                            board[i][j] = null;
                            p.setKill(true);
                            teamSwitch();
                            break;
                        }
                        p.setX(tempX);
                        p.setY(tempY);
                        System.out.println("Piece in the way! <v");
                        return;
                    }
                }
            }
        }

        // Update the board
        board[tempX][tempY] = null;
        board[p.getX()][p.getY()] = p;
        if (p.getMoveFail() == false) {
            teamSwitch();
        }
    }

    public void teamSwitch() {
        if (teamTurn == 0) {
            teamTurn = 1;
        } else {
            teamTurn = 0;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j <= 7; j++) {
                if (board[j][i] != null) {
                    s.append(board[j][i]).append(" ");
                } else {
                    s.append("_ ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }
}