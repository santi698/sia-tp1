package  gps.strategies;

import  gps.AbstractSearchStrategy;
import java.util.LinkedList;
import java.util.Queue;
import  gps.GPSNode;

public class BFSSearchStrategy extends AbstractSearchStrategy {
  Queue<GPSNode> nodes;
  public BFSSearchStrategy() {
    super();
    nodes = new LinkedList<>();
    setOpen(nodes);
  }

  @Override
  protected boolean canContinue(GPSNode node) {
    if (expanded(node)) {
      return false;
    }
    return true;
  }


  public void concreteAddNode(GPSNode node) {
    nodes.add(node);
  }

  public GPSNode removeNextNode() {
    return nodes.remove();
  }

  public boolean hasNextNode() {
    return !nodes.isEmpty();
  };
}
