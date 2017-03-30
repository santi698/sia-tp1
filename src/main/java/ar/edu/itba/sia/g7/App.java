package ar.edu.itba.sia.g7;

import java.util.Scanner;

import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.BoardState;
import ar.edu.itba.sia.g7.sokoban.Direction;
import ar.edu.itba.sia.gps.api.GPSState;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args ) {
    Scanner userInput = new Scanner(System.in);

    System.out.println("Sokoban - SIA");
    System.out.println("Elegir un numero de mapa:");
    int n = userInput.nextInt();
    BoardState board = BoardParser.boardFromFile("maps/" + n +".txt");

    if (board != null) {
      System.out.println(board.getRows().get(2).get(2).getEntity());
      System.out.println(board.isSolved());
    } else {
      System.out.println("mapa invalido");
    }

    System.out.println(BoardParser.boardToString(board));
    board.moveCharacter(Direction.DOWN).ifPresent((state) -> {
      System.out.println(BoardParser.boardToString((BoardState) state));
    });
    Optional<GPSState> newBoard = board.moveCharacter(Direction.DOWN)
                                       .flatMap((state) -> ((BoardState) state).moveCharacter(Direction.DOWN));

    if (newBoard.isPresent()) { System.out.println(BoardParser.boardToString((BoardState) newBoard.get())); }
    else { System.out.println("Invalid movement"); }
  }
}
