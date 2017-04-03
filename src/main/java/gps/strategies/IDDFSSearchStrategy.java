package gps.strategies;

import gps.AbstractSearchStrategy;
import gps.GPSNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by marlanti on 3/28/17.
 */
public class IDDFSSearchStrategy extends AbstractSearchStrategy {

  private Deque<GPSNode> nodes;
  private Integer depthBound;
  private GPSNode root;

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
    if(nodes.isEmpty() && node.getLevel() == 0){
      root = node;
    }
    nodes.addFirst(node);
  }

  @Override
  public GPSNode removeNextNode() {

    if (nodes.peek().getLevel() <= depthBound) {
      depthBound++;
      return nodes.remove();
    }else{
      return root;
    }


  }

  @Override
  protected boolean canContinue(GPSNode node) {
    if (expanded(node)) {
      return false;
    }
    return true;
  }
}
