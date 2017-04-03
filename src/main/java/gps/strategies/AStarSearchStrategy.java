package  gps.strategies;

import gps.AbstractSearchStrategy;
import gps.GPSNode;
import gps.api.GPSState;
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
    System.out.println("Adding");
    System.out.println("G = " + node.getCost());
    System.out.println("H = " + heuristic.apply(node.getState()));
    nodes.add(node);
  }

  public GPSNode removeNextNode() {
    System.out.println("Removing");
    System.out.println("G = " + nodes.peek().getCost());
    System.out.println("H = " + heuristic.apply(nodes.peek().getState()));
    return nodes.remove();
  }

  public boolean hasNextNode() {
    return !nodes.isEmpty();
  };

  @Override
  protected boolean canContinue(GPSNode node) {
    //TODO chequear que esté ok la lógica
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
