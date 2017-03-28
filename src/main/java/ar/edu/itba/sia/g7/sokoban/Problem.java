package ar.edu.itba.sia.g7.sokoban;

import ar.edu.itba.sia.gps.api.GPSProblem;
import ar.edu.itba.sia.gps.api.GPSRule;
import ar.edu.itba.sia.gps.api.GPSState;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Problem implements GPSProblem {
  private BoardState board;
  
  public Problem (BoardState board){
	  this.board = board;
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
    return 0;
  }  //suma de las 2 H

}
