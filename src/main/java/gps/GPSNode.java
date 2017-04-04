package gps;

import  gps.api.GPSState;
import java.util.Collection;
import  gps.api.GPSRule;
import java.util.LinkedList;
import java.util.Optional;

public class GPSNode {
  private final GPSRule generationRule;
  private GPSState state;
  private GPSNode parent;
  private Integer cost;
  private Integer level;


  public GPSNode(GPSState state, Integer cost, GPSRule generationRule) {
    this(state,cost,null,0,generationRule);
  }


  public GPSNode(GPSState state, Integer cost, GPSNode parent, Integer level, GPSRule generationRule) {
    this.state = state;
    this.cost = cost;
    this.parent = parent;
    this.level = level;
    this.generationRule = generationRule;
  }

  public boolean isRoot() {
    return parent==null && level==0;
  }


  public Integer getLevel() {
    return level;
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
    return "Level: " + level.toString() + "\n" +
           "Cost: " + cost.toString() + "\n\n" + state.toString();
  }

  public String getSolution() {
    if (this.parent == null) {
      return toString();
    }
    return parent.getSolution() +
      "\n---------------------------\n\n" +
      toString();
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
      Optional<GPSState> newState = rule.evalRule(getState());
      //FIXME: ver si pasarle el level asi está ok.
      newState.ifPresent(
        (state) -> neighbors.add(new GPSNode(state, cost + rule.getCost(), this, level+1, rule))
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

  public GPSRule getGenerationRule() {
    return generationRule;
  }


}
