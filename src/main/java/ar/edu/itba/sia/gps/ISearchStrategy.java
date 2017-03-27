package  ar.edu.itba.sia.gps;

import java.util.Collection;

public interface ISearchStrategy {
  void addNode(GPSNode node);
  void addNodes(Collection<GPSNode> nodes);
  boolean expanded(GPSNode node);
  boolean hasNextNode();
  GPSNode removeNextNode();
}
