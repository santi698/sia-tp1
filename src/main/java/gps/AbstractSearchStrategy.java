package gps;

import java.util.Map;
import java.util.HashMap;

import gps.api.GPSState;

import java.util.Collection;
import java.util.Queue;

public abstract class AbstractSearchStrategy implements ISearchStrategy {
  private Map<GPSState, Integer> bestCosts;
  private Queue<GPSNode> open;
  private long explosionCounter;


  public AbstractSearchStrategy() {
    bestCosts = new HashMap<>();
    explosionCounter = 0;

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
    updateBest(node);
    concreteAddNode(node);
  }


  private void updateBest(GPSNode node) {
    bestCosts.put(node.getState(), node.getCost());
  }

  public void addNodes(Collection<GPSNode> nodes) {

    nodes.stream().forEach((node) -> {
      if (!canContinue(node)) {
        return;
      }
      explosionCounter++;
      addNode(node);
    });
  }

  protected abstract boolean canContinue(GPSNode node);

  public boolean expanded(GPSNode node) {
    return bestCosts.containsKey(node.getState());
  }

  protected boolean isBest(GPSState state, Integer cost) {
    return !bestCosts.containsKey(state) || cost < bestCosts.get(state);
  }

  protected abstract void concreteAddNode(GPSNode node);

  public abstract GPSNode removeNextNode();

  public long getExplosionCounter() {
    return explosionCounter;
  }

  protected void resetBestCosts() {
    bestCosts = new HashMap<>();
    explosionCounter=0;
  }
}
