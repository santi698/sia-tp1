package ar.edu.itba.sia.g7;

import java.util.Scanner;

import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.BoardState;
import ar.edu.itba.sia.g7.sokoban.Direction;
import ar.edu.itba.sia.g7.sokoban.Problem;
import ar.edu.itba.sia.gps.api.GPSState;
import ar.edu.itba.sia.gps.api.GPSProblem;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.GPSEngine;
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
    GPSProblem problem = new Problem(board);
    GPSEngine engine = new GPSEngine(problem, SearchStrategy.DFS);
    engine.findSolution();
    System.out.println(engine.getSolutionNode().getSolution());
  }
}
