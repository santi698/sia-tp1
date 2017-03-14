package ar.edu.itba.sia.g7.gps;

import ar.edu.itba.sia.g7.gps.api.GPSProblem;
import ar.edu.itba.sia.g7.gps.api.GPSRule;
import ar.edu.itba.sia.g7.gps.api.GPSState;

import java.util.List;
import java.util.Collections;

public class Problem implements GPSProblem {
  public GPSState getInitState() {
    return null;
  }

  public boolean isGoal(GPSState state) {
    return false;
  }

  public List<GPSRule> getRules() {
    return Collections.<GPSRule>emptyList();
  };

  public Integer getHValue(GPSState state) {
    return 0;
  }

}
