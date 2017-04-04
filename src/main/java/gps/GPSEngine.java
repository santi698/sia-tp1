
package gps;


import gps.api.GPSProblem;
import gps.api.GPSState;
import gps.strategies.*;

import java.util.Map;
import java.util.Queue;


public class GPSEngine {

  private GPSProblem problem;

  private boolean finished;
  private boolean failed;
  private GPSNode solutionNode;
  private SearchStrategy strategy;
  private ISearchStrategy strategyObject;

  public GPSEngine(GPSProblem myProblem, SearchStrategy myStrategy) {
    problem = myProblem;
    strategy = myStrategy;
    strategyObject = initStrategy();
    finished = false;
    failed = false;
  }

  public void findSolution() {
    GPSNode rootNode = new GPSNode(problem.getInitState(), 0, null);
    strategyObject.addNode(rootNode);

    while (strategyObject.hasNextNode()) {
      GPSNode currentNode = strategyObject.removeNextNode();
      if (currentNode != null) {
        if (problem.isGoal(currentNode.getState())) {
          System.out.println("Estados visitados: " + strategyObject.getBestCosts().size());
          finished = true;
          solutionNode = currentNode;
          return;
        } else {

          strategyObject.addNodes(currentNode.getNeighbors(problem.getRules()));
        }
      }

    }
    failed = true;
    finished = true;
  }

  public ISearchStrategy initStrategy() {
    switch (strategy) {
      case DFS:
        return new DFSSearchStrategy();
      case BFS:
        return new BFSSearchStrategy();
      case IDDFS:
        return new IDDFSSearchStrategy(problem);
      case GREEDYSEARCH:
        return new GreedySearchStrategy(problem::getHValue);
      case ASTAR:
        return new AStarSearchStrategy(problem::getHValue);
      default:
        return null;
    }
  }

  // GETTERS FOR THE PEOPLE!

  public Queue<GPSNode> getOpen() {
    return strategyObject.getOpen();
  }

  public Map<GPSState, Integer> getBestCosts() {
    return strategyObject.getBestCosts();
  }

  public GPSProblem getProblem() {
    return problem;
  }

  public long getExplosionCounter() {
    return strategyObject.getExplosionCounter();
  }

  public boolean isFinished() {
    return finished;
  }

  public boolean isFailed() {
    return failed;
  }

  public GPSNode getSolutionNode() {
    return solutionNode;
  }

  public SearchStrategy getStrategy() {
    return strategy;
  }

}
