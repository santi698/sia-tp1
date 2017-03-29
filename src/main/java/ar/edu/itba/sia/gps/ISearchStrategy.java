package  ar.edu.itba.sia.gps;

import ar.edu.itba.sia.gps.api.GPSState;

import java.util.Collection;
import java.util.Map;
import java.util.Queue;

public interface ISearchStrategy {
  void addNode(GPSNode node);
  void addNodes(Collection<GPSNode> nodes);
  boolean expanded(GPSNode node);
  boolean hasNextNode();
  GPSNode removeNextNode();
  Queue<GPSNode> getOpen();
  Map<GPSState, Integer> getBestCosts();
  long getExplosionCounter();
}
