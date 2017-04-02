package ar.edu.itba.sia.g7.sokoban.heuristics;


import ar.edu.itba.sia.g7.sokoban.BoardState;

public interface Heuristic {

  Integer getHvalue(BoardState board);

}
