import spock.lang.Specification;
import spock.lang.Unroll;
import ar.edu.itba.sia.g7.sokoban.Point;

class PointSpec extends Specification {
  def "distance"() {
    given: "Some points"
      def p1 = new Point(1, 1);
      def p2 = new Point(0, 0);
      def p3 = new Point(10, 1);
      def p4 = new Point(4, 4);
    when: "computing relative distance"
      
    then: "It returns the correct results"
      p1.distance(p2) == 2
      p1.distance(p3) == 9
      p2.distance(p3) == 11
      p4.distance(p2) == 8
  }
}
