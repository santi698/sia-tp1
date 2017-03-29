package  ar.edu.itba.sia.gps.strategies;

import  ar.edu.itba.sia.gps.AbstractSearchStrategy;
import  ar.edu.itba.sia.gps.GPSNode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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

  public GPSNode removeNextNode() {
    return nodes.remove();
  }

  public boolean hasNextNode() {
    return !nodes.isEmpty();
  };
}
