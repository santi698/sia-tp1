package ar.edu.itba.sia.g7.sokoban;

import spock.lang.Specification;
import spock.lang.Unroll;
import spock.lang.Shared;

import ar.edu.itba.sia.g7.sokoban.entities.Entity;
import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.Direction;
import ar.edu.itba.sia.g7.sokoban.heuristics.CornerHeuristic;

class CornerHeuristicSpec extends Specification {
  def "#getHValue"() {
    given: "Some boards"
      def simpleBoard = BoardParser.boardFromFile("maps/3.txt");
      def solvedBoard = BoardParser.boardFromFile("maps/2.txt");
      def complexBoard = BoardParser.boardFromFile("maps/6.txt");
      def hiperComplexBoard = BoardParser.boardFromFile("maps/8.txt");
      def unsolvableBoard = BoardParser.boardFromFile("src/test/fixtures/unsolvable.txt");
      def simpleBoard2 = BoardParser.boardFromFile("src/test/fixtures/hcost2.txt");
      def simpleBoard3 = BoardParser.boardFromFile("src/test/fixtures/simple-no-corners.txt");
      def simpleBoard4 = BoardParser.boardFromFile("src/test/fixtures/test-corners.txt");
      def simpleBoard5 = BoardParser.boardFromFile("src/test/fixtures/test-corners2.txt");
      def heuristic = new CornerHeuristic();

    when: "It calculates H value"

    then: "It returns the correct value"
      heuristic.getHValue(simpleBoard) == 0
      heuristic.getHValue(solvedBoard) == 0
      heuristic.getHValue(complexBoard) == 0
      heuristic.getHValue(simpleBoard2) == 0
      heuristic.getHValue(hiperComplexBoard) == 0
      heuristic.getHValue(simpleBoard3) == 0
      heuristic.getHValue(simpleBoard5) == 0
      simpleBoard4.isInCorner(simpleBoard4.getTileAt(new Point(2,2)).get()) == false
      heuristic.getHValue(unsolvableBoard) == Float.POSITIVE_INFINITY
  }
}
