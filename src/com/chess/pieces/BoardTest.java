package com.chess.pieces;

public class BoardTest {
    public static void main(String[] theArgs) {
        Board b = new Board();

        b.initializeBoard();

        // com.chess.pieces.Queen tests work as expected
        b.move(b.board[0][1], 0, 1);
//        b.board[3][3] = new com.chess.pieces.Queen(3,3);
//        b.move(b.board[3][3], 1, 1);
//        b.move(b.board[4][4], -1, -1);
//        b.move(b.board[3][3], 1, 0);
//        b.move(b.board[4][3], 2, 1);


        System.out.println(b);
    }
}
