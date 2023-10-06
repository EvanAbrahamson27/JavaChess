package com.graphics.chessboard;

import com.chess.pieces.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BoardUI extends Application {
    private static final Image pawnImage = new Image("C:\\Users\\Owner\\IdeaProjects\\Chess\\src\\com\\graphics\\chessboard\\pawn.png");
    private static final Image knightImage = new Image("C:\\Users\\Owner\\IdeaProjects\\Chess\\src\\com\\graphics\\chessboard\\knight.png");
    private static final Image kingImage = new Image("C:\\Users\\Owner\\IdeaProjects\\Chess\\src\\com\\graphics\\chessboard\\king.png");
    private static final Image queenImage = new Image("C:\\Users\\Owner\\IdeaProjects\\Chess\\src\\com\\graphics\\chessboard\\queen.png");
    private static final Image rookImage = new Image("C:\\Users\\Owner\\IdeaProjects\\Chess\\src\\com\\graphics\\chessboard\\rook.png");
    private static final Image bishopImage = new Image("C:\\Users\\Owner\\IdeaProjects\\Chess\\src\\com\\graphics\\chessboard\\bishop.png");
    private Piece selectedPiece = null;
    private Paint selectedPieceColor;
    private Group board = new Group();
    int size = 120;
    static Board b = new Board();
    private ImageView[][] imageViewGrid = new ImageView[8][8];

    public static void main(String[] args) {
        b.initializeBoard();
        launch(args);
    }

    private Group createChessBoard() {
        boolean isLight = true;

        for (int row = 0; row < 8; row++) {
            isLight = !isLight;
            for (int col = 0; col < 8; col++) {
                final int finalRow = row;
                final int finalCol = col;
                Rectangle square = new Rectangle(col * size, row * size, size, size);
                isLight = !isLight;
                square.setOnMouseClicked(event -> handleSquareClick(square, finalRow, finalCol));
                if (isLight) {
                    square.setFill(Color.LIGHTSLATEGRAY);
                } else {
                    square.setFill(Color.DARKSLATEGRAY);
                }

                board.getChildren().add(square);

                if (b.board[col][7 - row] != null) {
                    ImageView imageView;
                    if (b.board[col][7 - row].getClass() == Pawn.class) {
                        imageView = new ImageView(pawnImage);
                    } else if (b.board[col][7 - row].getClass() == Knight.class) {
                        imageView = new ImageView(knightImage);
                    } else if (b.board[col][7 - row].getClass() == Bishop.class) {
                        imageView = new ImageView(bishopImage);
                    } else if (b.board[col][7 - row].getClass() == King.class) {
                        imageView = new ImageView(kingImage);
                    } else if (b.board[col][7 - row].getClass() == Queen.class) {
                        imageView = new ImageView(queenImage);
                    } else {
                        imageView = new ImageView(rookImage);
                    }
                    if (b.board[col][7 - row].getTeam() == 1) {
                        imageView.setBlendMode(BlendMode.DARKEN);
                    }
                    imageView.setMouseTransparent(true);
                    imageView.setLayoutX(col * size);
                    imageView.setLayoutY(row * size);
                    imageView.setPickOnBounds(false);

                    imageViewGrid[col][row] = imageView;

                    board.getChildren().add(imageView);
                }
            }
        }

        return board;
    }

    private void updateDisplayedPieces() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ImageView imageView = imageViewGrid[col][row];
                if (b.board[col][row] != null) {
                        if (imageView == null) {
                            if (b.board[col][row].getClass() == Pawn.class) {
                            imageView = new ImageView(pawnImage);
                            } else if (b.board[col][row].getClass() == Knight.class) {
                                imageView = new ImageView(knightImage);
                            } else if (b.board[col][row].getClass() == Bishop.class) {
                                imageView = new ImageView(bishopImage);
                            } else if (b.board[col][row].getClass() == King.class) {
                                imageView = new ImageView(kingImage);
                            } else if (b.board[col][row].getClass() == Queen.class) {
                                imageView = new ImageView(queenImage);
                            } else if (b.board[col][row].getClass() == Rook.class) {
                                imageView = new ImageView(rookImage);
                            }

                            if (b.board[col][row].getTeam() == 1) {
                                imageView.setBlendMode(BlendMode.BLUE);
                            }
                            imageView.setMouseTransparent(true);
                            imageViewGrid[col][row] = imageView;
                            board.getChildren().add(imageView);
                        }

                        imageView.setLayoutX(col * size);
                        imageView.setLayoutY((7 -row) * size);
                    } else {
                        if (imageView != null) {
                            board.getChildren().remove(imageView);
                            imageViewGrid[col][row] = null;
                        }
                    }
            }
        }
    }

    private void handleSquareClick(Rectangle s, int row, int col) {
        Piece piece = b.board[col][7 - row];

        if (selectedPiece == null) {
            if (piece != null) {
                selectedPiece = piece;
                selectedPiece.setRectangle(s);
                selectedPieceColor = s.getFill();
                s.setFill(Color.DARKSEAGREEN);
            }
        } else {
            b.move(selectedPiece, col - selectedPiece.getX(), (7 - row - selectedPiece.getY()));
            if (selectedPiece.getKill() == true) {
                b.move(selectedPiece, col - selectedPiece.getX(), (7 - row - selectedPiece.getY()));
                selectedPiece.setKill(false);
            }
            System.out.println(b.toString());
            selectedPiece.getRectangle().setFill(selectedPieceColor);
            selectedPiece = null;
            selectedPieceColor = null;
            createChessBoard();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 960, 960);

        Group board = createChessBoard();
        root.getChildren().add(board);

        stage.setTitle("Java Chess");
        stage.setScene(scene);
        stage.show();
    }
}
