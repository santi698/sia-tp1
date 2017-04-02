package ar.edu.itba.sia.g7.sokoban.heuristics;

import ar.edu.itba.sia.g7.sokoban.BoardState;
import ar.edu.itba.sia.g7.sokoban.entities.Entity;

public class CornerHeuristic implements Heuristic{

  @Override
  public Float getHValue(BoardState board) {
    long freeBoxesCount = board.getBoxes().stream()
                                         .filter((box) -> !board.isInCorner(box))
                                         .count();
    long missingGoals = board.getGoals().stream()
                                        .filter((goal) -> goal.getEntity() != Entity.BOX)
                                        .count();
    if (freeBoxesCount < missingGoals) {
      return Float.POSITIVE_INFINITY;
    }
    return Float.valueOf(0);
  }
}
