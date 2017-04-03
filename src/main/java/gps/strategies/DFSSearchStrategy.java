package  gps.strategies;

import  gps.AbstractSearchStrategy;
import  gps.GPSNode;
import java.util.Deque;
import java.util.LinkedList;

public class DFSSearchStrategy extends AbstractSearchStrategy {

  private Deque<GPSNode> nodes;

  public DFSSearchStrategy() {
    super();
    nodes = new LinkedList<>();
    setOpen(nodes);
  }

  @Override
  public void concreteAddNode(GPSNode node) {
    nodes.addFirst(node);
  }
  @Override
  protected boolean canContinue(GPSNode node) {
    if (expanded(node)) {
      return false;
    }
    return true;
  }

  public GPSNode removeNextNode() {
    return nodes.remove();
  }

  public boolean hasNextNode() {
    return !nodes.isEmpty();
  };
}
