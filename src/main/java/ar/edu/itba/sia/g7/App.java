package ar.edu.itba.sia.g7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.BoardState;
import ar.edu.itba.sia.g7.sokoban.Problem;
import ar.edu.itba.sia.g7.sokoban.heuristics.*;
import gps.api.GPSProblem;
import gps.SearchStrategy;
import gps.GPSEngine;
import gps.GPSNode;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args ) {
    Scanner userInput = new Scanner(System.in);
    System.out.println("Sokoban - SIA");
    System.out.println("Elegir un numero de mapa (1 al 11):");
    int level = userInput.nextInt();
    System.out.println("Elegir una estrategia:");
    printStrategies();
    int strategy = userInput.nextInt();
    BoardState board = BoardParser.boardFromFile("maps/" + level +".txt");
    GPSProblem problem;
    if(strategy > 3){
      System.out.println("Elegir una heuristica:");
      printHauristics();
      int heuristic = userInput.nextInt();
      userInput.close();
      problem = new Problem(board, chosenHeuristic(heuristic));
    } else {
      userInput.close();
      problem = new Problem(board, null);
    }
    GPSEngine engine = new GPSEngine(problem, chosenStrategy(strategy));
    long startTime = System.nanoTime();
    engine.findSolution();
    GPSNode solution =  engine.getSolutionNode();
    if (solution == null) {
      System.out.println("El mapa no tiene solución.");
      return;
    }
    System.out.println("La solución es:\n");
    System.out.println(solution.getSolution());
    System.out.println("Estados visitados: " + engine.getBestCosts().size());
    System.out.println("Estados frontera: " + engine.getOpen().size());
    System.out.println("Solución encontrada en " + (System.nanoTime() - startTime)/1000000f + " milisegundos");

  }

  private static List<Heuristic> chosenHeuristic(int heuristic) {
    List<Heuristic> heuristics = new ArrayList<Heuristic>();
    switch(heuristic) {
      case 1: {
        heuristics.add(new CharacterToBoxDistanceHeuristic());
        heuristics.add(new CornerHeuristic());
        heuristics.add(new GoalsToBoxesDistanceHeuristic());
        break;
      }
      case 2: {
        heuristics.add(new GoalsToBoxesDistanceHeuristic());
        break;
      }
      case 3: {
        heuristics.add(new CharacterToBoxDistanceHeuristic());
        heuristics.add(new CornerHeuristic());
        heuristics.add(new GoalsToBoxesStraightLineHeuristic());
        break;
      }
      default: {
        heuristics.add(new CharacterToBoxDistanceHeuristic());
        heuristics.add(new CornerHeuristic());
        heuristics.add(new GoalsToBoxesDistanceHeuristic());
        break;
      }
    }
    return heuristics;
  }

  public static SearchStrategy chosenStrategy(int number) {
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
  public static void printHauristics(){
    System.out.println("1. Distancia Manhattan con corner");
    System.out.println("2. Distancia Manhattan");
    System.out.println("3. Distancia Linea Recta");
  }
}
