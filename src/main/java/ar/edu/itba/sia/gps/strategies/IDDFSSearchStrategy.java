package ar.edu.itba.sia.gps.strategies;


import ar.edu.itba.sia.gps.AbstractSearchStrategy;
import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.api.GPSProblem;


import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by marlanti on 3/28/17.
 */
public class IDDFSSearchStrategy extends AbstractSearchStrategy {

  private Deque<GPSNode> frontier;
  private Integer depthBound;
  private GPSNode root;
  private GPSProblem problem;


  public IDDFSSearchStrategy(GPSProblem problem) {
    super();
    this.frontier = new LinkedList<>();
    this.depthBound = 0;
    setOpen(frontier);
    this.problem = problem;
  }

  @Override
  public boolean hasNextNode() {
    return !frontier.isEmpty();
  }

  @Override
  protected void concreteAddNode(GPSNode node) {
    if (node.isRoot()) {
      root = node;
    }
    frontier.addFirst(node);
  }

  @Override
  public GPSNode removeNextNode() {
    //GPSNode current = null;
    int level = 0;

    while(hasNextNode()){
      GPSNode current = frontier.remove();

      if(problem.isGoal(current.getState())){
        return current;
      }

      if(current.getLevel() != depthBound){
        addNodes(current.getNeighbors(problem.getRules()));
      }

      if(current.getLevel()>level){
        level = current.getLevel();
      }
    }

    //Finish when no solution.
    if((depthBound - level) == 1){
      return null;
    }
    depthBound++;
    resetBestCosts();
    return root;

  }




  @Override
  protected boolean canContinue(GPSNode node) {
    if (expanded(node)) {
      return false;
    }
    return true;
  }
}
