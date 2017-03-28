package  ar.edu.itba.sia.gps;

import java.util.Map;
import java.util.HashMap;
import  ar.edu.itba.sia.gps.api.GPSState;
import java.util.Collection;

public abstract class AbstractSearchStrategy implements ISearchStrategy {
  private Map<GPSState, Integer> bestCosts;

  public AbstractSearchStrategy() {
    bestCosts = new HashMap<>();
  }

  public void addNode(GPSNode node) {
    bestCosts.put(node.getState(), node.getCost());
    concreteAddNode(node);
  }

  public void addNodes(Collection<GPSNode> nodes) {
    nodes.stream().forEach((node) -> addNode(node));
  }

  public boolean expanded(GPSNode node) {
    return bestCosts.containsKey(node.getState());
  }

  protected abstract void concreteAddNode(GPSNode node);
  public abstract GPSNode removeNextNode();
}
