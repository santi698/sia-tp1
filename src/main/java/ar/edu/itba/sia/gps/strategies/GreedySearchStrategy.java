package  ar.edu.itba.sia.gps.strategies;

import  ar.edu.itba.sia.gps.AbstractSearchStrategy;
import  ar.edu.itba.sia.gps.GPSNode;
import java.util.Queue;
import java.util.function.Function;
import java.util.PriorityQueue;
import  ar.edu.itba.sia.gps.api.GPSState;

public class GreedySearchStrategy extends AbstractSearchStrategy {
  Queue<GPSNode> nodes;
  public GreedySearchStrategy(Function<GPSState, Integer> heuristic) {
    super();
    nodes = new PriorityQueue<>(
      (a, b) -> heuristic.apply(b.getState()) - heuristic.apply(a.getState())
    );
  }

  @Override
  protected void concreteAddNode(GPSNode node) {
    nodes.add(node);
  }

  public GPSNode removeNextNode() {
    return nodes.remove();
  }

  public boolean hasNextNode() {
    return !nodes.isEmpty();
  };
}
