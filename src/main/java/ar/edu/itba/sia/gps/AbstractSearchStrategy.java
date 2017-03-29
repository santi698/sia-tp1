package  ar.edu.itba.sia.gps;

import java.util.Map;
import java.util.HashMap;
import  ar.edu.itba.sia.gps.api.GPSState;
import java.util.Collection;
import java.util.Queue;

public abstract class AbstractSearchStrategy implements ISearchStrategy {
  private Map<GPSState, Integer> bestCosts;
  private Queue<GPSNode> open;

  public AbstractSearchStrategy() {
    bestCosts = new HashMap<>();
  }

  public void setOpen(Queue<GPSNode> open) {
    this.open = open;
  }

  public Queue<GPSNode> getOpen() {
    return open;
  }

  public Map<GPSState, Integer> getBestCosts() {
    return bestCosts;
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
