package  ar.edu.itba.sia.gps.strategies;

import  ar.edu.itba.sia.gps.AbstractSearchStrategy;
import java.util.LinkedList;
import java.util.Queue;
import  ar.edu.itba.sia.gps.GPSNode;

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
