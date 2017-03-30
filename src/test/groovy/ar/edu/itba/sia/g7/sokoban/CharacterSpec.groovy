package ar.edu.itba.sia.g7.sokoban;

import spock.lang.Specification
import spock.lang.Unroll

import ar.edu.itba.sia.g7.sokoban.entities.Entity;

class CharacterTest extends Specification {
  def "something"() {
    given: "Any character"
    def character = Entity.CHARACTER;

    when: "It does nothing"

    then: "Nothing happens"
    true == true
  }
}
