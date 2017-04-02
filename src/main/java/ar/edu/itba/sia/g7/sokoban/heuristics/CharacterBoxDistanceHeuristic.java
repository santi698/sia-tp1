package ar.edu.itba.sia.g7.sokoban.heuristics;


import ar.edu.itba.sia.g7.sokoban.BoardState;

public class CharacterBoxDistanceHeuristic implements Heuristic{

  @Override
  public Integer getHvalue(BoardState board) {
    return board.getCharacter().getMinDistFromTile(board.getBoxes());
  }
}
