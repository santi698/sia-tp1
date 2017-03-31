package ar.edu.itba.sia.g7;

import java.util.Scanner;

import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.BoardState;
import ar.edu.itba.sia.g7.sokoban.Problem;
import ar.edu.itba.sia.gps.api.GPSProblem;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.GPSNode;

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
    userInput.close();
    BoardState board = BoardParser.boardFromFile("maps/" + n +".txt");
    GPSProblem problem = new Problem(board);
    GPSEngine engine = new GPSEngine(problem, SearchStrategy.BFS);
    engine.findSolution();
    GPSNode solution =  engine.getSolutionNode();
    if (solution == null) {
      System.out.println("No solution found.");
      return;
    }
    System.out.println("The solution found is:\n");
    System.out.println(solution.getSolution());
  }
}
