package  ar.edu.itba.sia.gps.strategies;

import  ar.edu.itba.sia.gps.AbstractSearchStrategy;
import  ar.edu.itba.sia.gps.GPSNode;
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
