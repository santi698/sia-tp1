package ar.edu.itba.sia.g7.sokoban;

import ar.edu.itba.sia.g7.sokoban.heuristics.CharacterToBoxDistanceHeuristic;
import ar.edu.itba.sia.g7.sokoban.heuristics.CornerHeuristic;
import ar.edu.itba.sia.g7.sokoban.heuristics.Heuristic;
import ar.edu.itba.sia.g7.sokoban.heuristics.GoalsToBoxesDistanceHeuristic;
import ar.edu.itba.sia.gps.api.GPSProblem;
import ar.edu.itba.sia.gps.api.GPSRule;
import ar.edu.itba.sia.gps.api.GPSState;

import java.util.Arrays;
import java.util.List;

public class Problem implements GPSProblem {
  private BoardState board;
  private List<Heuristic> heuristics;
  
  public Problem (BoardState board){
    this.board = board;
    this.heuristics = Arrays.asList(
      new CharacterToBoxDistanceHeuristic(),
      new GoalsToBoxesDistanceHeuristic(),
      new CornerHeuristic()
    );
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
