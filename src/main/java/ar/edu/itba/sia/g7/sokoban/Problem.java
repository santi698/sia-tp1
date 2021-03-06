package ar.edu.itba.sia.g7.sokoban;

import ar.edu.itba.sia.g7.sokoban.heuristics.CharacterToBoxDistanceHeuristic;
import ar.edu.itba.sia.g7.sokoban.heuristics.CornerHeuristic;
import ar.edu.itba.sia.g7.sokoban.heuristics.Heuristic;
import ar.edu.itba.sia.g7.sokoban.heuristics.GoalsToBoxesDistanceHeuristic;
import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem implements GPSProblem {
  private BoardState board;
  private List<Heuristic> heuristics;
  
  public Problem (BoardState board, List<Heuristic> heuristics){
    this.board = board;
    this.heuristics = heuristics;
  }

  public GPSState getInitState() {
    return this.board;
  }

  public boolean isGoal(GPSState state) {
    return ((BoardState)state).isSolved();
  }

  public List<GPSRule> getRules() {
    return Arrays.asList(Movement.values());
  };

  public Integer getHValue(GPSState state) {
    return Double.valueOf(
      heuristics.stream()
                .mapToDouble((h) -> h.getHValue((BoardState) state)).sum())
      .intValue();
  }

}
