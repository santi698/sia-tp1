package  gps.strategies;

import  gps.AbstractSearchStrategy;
import  gps.GPSNode;
import java.util.Queue;
import java.util.function.Function;
import java.util.PriorityQueue;
import  gps.api.GPSState;

public class GreedySearchStrategy extends AbstractSearchStrategy {
  Queue<GPSNode> nodes;
  public GreedySearchStrategy(Function<GPSState, Integer> heuristic) {
    super();
    /*
    (a, b) -> heuristic.apply(b.getState()) - heuristic.apply(a.getState())

    ES LO MISMO QUE HACER:

    new Comparator<GSPNode>() {
      @Override
      public int compare(GSPNode a, GSPNode b) {
        return heuristic.apply(b.getState()) - heuristic.apply(a.getState());
      }
    }*/

    nodes = new PriorityQueue<>(
      (a, b) -> heuristic.apply(a.getState()) - heuristic.apply(b.getState())
    );
    setOpen(nodes);
  }

  @Override
  protected boolean canContinue(GPSNode node) {
    return isBest(node.getState(), node.getCost());
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
