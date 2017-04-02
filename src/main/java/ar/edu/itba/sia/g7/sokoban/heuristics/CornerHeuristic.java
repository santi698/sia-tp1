package ar.edu.itba.sia.g7.sokoban.heuristics;


import ar.edu.itba.sia.g7.sokoban.BoardState;
import ar.edu.itba.sia.g7.sokoban.tiles.Tile;

public class CornerHeuristic implements Heuristic{

  @Override
  public Integer getHvalue(BoardState board) {
    for (Tile t : board.getBoxes()) {

    }
    return null;
  }
}
