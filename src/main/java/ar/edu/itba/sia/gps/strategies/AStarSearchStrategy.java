package  ar.edu.itba.sia.gps.strategies;

import  ar.edu.itba.sia.gps.AbstractSearchStrategy;
import  ar.edu.itba.sia.gps.GPSNode;
import  ar.edu.itba.sia.gps.api.GPSState;
import java.util.function.Function;
import java.util.Queue;
import java.util.PriorityQueue;

public class AStarSearchStrategy extends AbstractSearchStrategy {
  Queue<GPSNode> nodes;
  public AStarSearchStrategy(Function<GPSState, Integer> heuristic) {
    super();
    nodes = new PriorityQueue<>(
      (a, b) -> (b.getCost() + heuristic.apply(b.getState())) - (a.getCost() + heuristic.apply(a.getState()))
    );
    setOpen(nodes);
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
