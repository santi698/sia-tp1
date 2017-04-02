package ar.edu.itba.sia.g7.sokoban.heuristics;


import ar.edu.itba.sia.g7.sokoban.BoardState;

public class CharacterToBoxDistanceHeuristic implements Heuristic{

  @Override
  public Float getHValue(BoardState board) {
    return Float.valueOf(board.getCharacter().getMinDistFromTile(board.getBoxes()) - 1);
  }
}
