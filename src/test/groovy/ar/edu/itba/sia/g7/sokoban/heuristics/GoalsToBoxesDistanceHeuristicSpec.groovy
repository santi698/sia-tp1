package ar.edu.itba.sia.g7.sokoban;

import spock.lang.Specification;
import spock.lang.Unroll;
import spock.lang.Shared;

import ar.edu.itba.sia.g7.sokoban.entities.Entity;
import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.Direction;
import ar.edu.itba.sia.g7.sokoban.heuristics.GoalsToBoxesDistanceHeuristic;

class GoalsToBoxesDistanceHeuristicSpec extends Specification {
  def "#getHValue"() {
    given: "Some boards"
      def simpleBoard = BoardParser.boardFromFile("maps/3.txt");
      def solvedBoard = BoardParser.boardFromFile("maps/2.txt");
      def simpleBoard2 = BoardParser.boardFromFile("src/test/fixtures/hcost2.txt");
      def heuristic = new GoalsToBoxesDistanceHeuristic();

    when: "It calculates H value"

    then: "It returns the correct value"
      heuristic.getHValue(simpleBoard) == 1
      heuristic.getHValue(solvedBoard) == 0
      heuristic.getHValue(simpleBoard2) == 1
  }
}
