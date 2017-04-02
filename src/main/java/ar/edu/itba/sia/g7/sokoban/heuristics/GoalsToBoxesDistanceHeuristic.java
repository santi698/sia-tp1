package ar.edu.itba.sia.g7.sokoban.heuristics;

import ar.edu.itba.sia.g7.sokoban.BoardState;

public class GoalsToBoxesDistanceHeuristic implements Heuristic{
  @Override
  public Float getHValue(BoardState board) {
    return Float.valueOf(board.getGoals().stream()
                                         .mapToInt((goal) -> goal.getMinDistFromTile(board.getBoxes()))
                                         .sum());
  }
}
