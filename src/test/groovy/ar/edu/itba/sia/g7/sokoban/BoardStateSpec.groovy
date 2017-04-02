package ar.edu.itba.sia.g7.sokoban;

import spock.lang.Specification;
import spock.lang.Unroll;
import spock.lang.Shared;

import ar.edu.itba.sia.g7.sokoban.entities.Entity;
import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.Direction;

class BoardStateSpec extends Specification {
  def "can't move character up"() {
    given: "Some boards where we can't move up"
      def cantmove = BoardParser.boardFromFile("src/test/fixtures/cantmove.txt");
      def cantmove1 = BoardParser.boardFromFile("src/test/fixtures/cantmoveup1.txt");
      def cantmove2 = BoardParser.boardFromFile("src/test/fixtures/cantmoveup2.txt");
      def cantmove3 = BoardParser.boardFromFile("src/test/fixtures/cantmoveup3.txt");
      def boards = [cantmove, cantmove1, cantmove2, cantmove3];

    when: "It tries to move up"
      def results = boards.stream().map { board -> board.moveCharacter(Direction.UP) };

    then: "It can't move up"
      results.allMatch { opt -> !opt.isPresent() };
  }

  def "move character up"() {
    given: "Some boards where we can move up"
      def moveup1_initial = BoardParser.boardFromFile("src/test/fixtures/moveup1_initial.txt");
      def moveup2_initial = BoardParser.boardFromFile("src/test/fixtures/moveup2_initial.txt");
      def boards = [moveup1_initial, moveup2_initial];
    when: "Character moves up"
      def result_1 = moveup1_initial.moveCharacter(Direction.UP).get();
      def result_2 = moveup2_initial.moveCharacter(Direction.UP).get();
    then: "We get the expected boards"
      def moveup1_result = BoardParser.boardFromFile("src/test/fixtures/moveup1_result.txt");
      def moveup2_result = BoardParser.boardFromFile("src/test/fixtures/moveup2_result.txt");
      result_1 == moveup1_result
      result_2 == moveup2_result
  }
}
