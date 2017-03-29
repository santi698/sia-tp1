package ar.edu.itba.sia.gps.strategies;

import ar.edu.itba.sia.gps.AbstractSearchStrategy;
import ar.edu.itba.sia.gps.GPSNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by marlanti on 3/28/17.
 */
public class IDDFSSearchStrategy  extends AbstractSearchStrategy {

  private Deque<GPSNode> nodes;
  private Integer depthBound;

  public IDDFSSearchStrategy() {
    super();
    this.nodes = new LinkedList<>();
    this.depthBound = 0;
    setOpen(nodes);
  }

  @Override
  public boolean hasNextNode() {
    return !nodes.isEmpty();
  }

  @Override
  protected void concreteAddNode(GPSNode node) {
    if(node.getLevel() < depthBound){
      nodes.addFirst(node);
    }
    depthBound++;
  }

  @Override
  public GPSNode removeNextNode() {
    return nodes.remove();
  }

  @Override
  protected boolean canContinue(GPSNode node) {
    if (expanded(node)) {
      return false;
    }
    return true;
  }
}
