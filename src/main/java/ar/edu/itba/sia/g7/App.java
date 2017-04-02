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
    int level = userInput.nextInt();
    System.out.println("Elegir una estrategia:");
    printStrategies();
    int strategy = userInput.nextInt();
    userInput.close();
    BoardState board = BoardParser.boardFromFile("maps/" + level +".txt");
    GPSProblem problem = new Problem(board);
    GPSEngine engine = new GPSEngine(problem, chooseStrategy(strategy));
    engine.findSolution();
    GPSNode solution =  engine.getSolutionNode();
    if (solution == null) {
      System.out.println("No solution found.");
      return;
    }
    System.out.println("The solution found is:\n");
    System.out.println(solution.getSolution());
  }

  public static SearchStrategy chooseStrategy(int number) {
    switch(number) {
      case 1: return SearchStrategy.BFS;
      case 2: return SearchStrategy.DFS;
      case 3: return SearchStrategy.IDDFS;
      case 4: return SearchStrategy.GREEDYSEARCH;
      case 5: return SearchStrategy.ASTAR;
      default: return SearchStrategy.BFS;
    }
  }

  public static void printStrategies() {
    System.out.println("1. BFS");
    System.out.println("2. DFS");
    System.out.println("3. IDDFS");
    System.out.println("4. Greedy Search");
    System.out.println("5. A*");
  }
}
