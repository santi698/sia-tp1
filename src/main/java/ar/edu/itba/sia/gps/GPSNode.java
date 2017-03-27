package  ar.edu.itba.sia.gps;

import  ar.edu.itba.sia.gps.api.GPSState;
import java.util.Collection;
import  ar.edu.itba.sia.gps.api.GPSRule;
import java.util.LinkedList;
import java.util.Optional;

public class GPSNode {
  private GPSState state;
  private GPSNode parent;
  private Integer cost;

  public GPSNode(GPSState state, Integer cost) {
    this(state, cost, null);
  }

  public GPSNode(GPSState state, Integer cost, GPSNode parent) {
    this.state = state;
    this.cost = cost;
    this.parent = parent;
  }

  public GPSNode getParent() {
    return parent;
  }

  public GPSState getState() {
    return state;
  }

  public Integer getCost() {
    return cost;
  }

  @Override
  public String toString() {
    return state.toString();
  }

  public String getSolution() {
    if (this.parent == null) {
      return this.state.toString();
    }
    return this.parent.getSolution() + this.state.toString();
  }

  /**
   * Gets all neighbor states applying given rules
   * @param rules
   *            The rules to apply
   * @return a list of neighbor states
   */

  public Collection<GPSNode> getNeighbors(Iterable<GPSRule> rules) {
    Collection<GPSNode> neighbors = new LinkedList<>();
    for (GPSRule rule : rules) {
      Optional<GPSState> newState = rule.evalRule(this.getState());
      newState.ifPresent(
        (state) -> neighbors.add(new GPSNode(state, cost + rule.getCost(), this))
      );
    }
    return neighbors;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GPSNode other = (GPSNode) obj;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    return true;
  }

}
