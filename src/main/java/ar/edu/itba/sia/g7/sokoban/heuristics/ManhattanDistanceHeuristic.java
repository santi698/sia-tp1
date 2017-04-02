package ar.edu.itba.sia.g7.sokoban.heuristics;

import ar.edu.itba.sia.g7.sokoban.BoardState;
import ar.edu.itba.sia.g7.sokoban.tiles.Tile;

public class ManhattanDistanceHeuristic implements Heuristic{


  @Override
  public Integer getHvalue(BoardState board) {
    int distance = 0;
    for (Tile t : board.getGoals()) {
      distance += t.getMinDistFromTile(board.getBoxes());
    }
    return distance + board.getCharacter().getMinDistFromTile(board.getBoxes());

  }
}
