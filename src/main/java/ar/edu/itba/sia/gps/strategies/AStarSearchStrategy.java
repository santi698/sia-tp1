package  ar.edu.itba.sia.gps.strategies;

import ar.edu.itba.sia.gps.AbstractSearchStrategy;
import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.api.GPSState;
import java.util.function.Function;
import java.util.Queue;
import java.util.PriorityQueue;

public class AStarSearchStrategy extends AbstractSearchStrategy {
  Queue<GPSNode> nodes;
  Function<GPSState, Integer> heuristic;
  public AStarSearchStrategy(Function<GPSState, Integer> heuristic) {
    super();
    this.heuristic = heuristic;
    nodes = new PriorityQueue<>(
      (a, b) -> getCostOf(a) - getCostOf(b)
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

  @Override
  protected boolean canContinue(GPSNode node) {
    return isBest(node.getState(), node.getCost());
  }

  private Integer getCostOf(GPSNode node) {
    int hValue = heuristic.apply(node.getState());
    if (hValue == Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    } else {
      return node.getCost() + hValue;
    }
  }

}
