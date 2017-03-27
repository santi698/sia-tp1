package  ar.edu.itba.sia.gps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import ar.edu.itba.sia.gps.api.GPSProblem;
import ar.edu.itba.sia.gps.api.GPSRule;
import ar.edu.itba.sia.gps.api.GPSState;
import ar.edu.itba.sia.gps.strategies.*;

public class GPSEngine {
  private Queue<GPSNode> open;
  private Map<GPSState, Integer> bestCosts;
  private GPSProblem problem;
  private long explosionCounter;
  private boolean finished;
  private boolean failed;
  private GPSNode solutionNode;
  private SearchStrategy strategy;
  private ISearchStrategy strategyObject;

  public GPSEngine(GPSProblem myProblem, SearchStrategy myStrategy) {
    problem = myProblem;
    strategy = myStrategy;
    strategyObject = initStrategy();
    explosionCounter = 0;
    finished = false;
    failed = false;
  }

  public void findSolution() {
    GPSNode rootNode = new GPSNode(problem.getInitState(), 0);
    strategyObject.addNode(rootNode);
    // TODO: ¿Lógica de IDDFS?
    while (strategyObject.hasNextNode()) {
      GPSNode currentNode = strategyObject.removeNextNode();
      if (problem.isGoal(currentNode.getState())) {
        finished = true;
        solutionNode = currentNode;
        return;
      } else {
        strategyObject.addNodes(currentNode.getNeighbors(problem.getRules()));
      }
    }
    failed = true;
    finished = true;
  }

  public ISearchStrategy initStrategy() {
    switch (strategy) {
      case DFS: return new DFSSearchStrategy();
      case BFS: return new BFSSearchStrategy();
      case IDDFS: return null;
      case GREEDYSEARCH: return new GreedySearchStrategy(problem::getHValue);
      case ASTAR: return new AStarSearchStrategy(problem::getHValue);
      default: return null;
    }
  }

  // GETTERS FOR THE PEOPLE!

  public Queue<GPSNode> getOpen() {
    return open;
  }

  public Map<GPSState, Integer> getBestCosts() {
    return bestCosts;
  }

  public GPSProblem getProblem() {
    return problem;
  }

  public long getExplosionCounter() {
    return explosionCounter;
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
