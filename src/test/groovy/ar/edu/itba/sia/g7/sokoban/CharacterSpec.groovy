package ar.edu.itba.sia.g7.sokoban;

import spock.lang.Specification
import spock.lang.Unroll

import ar.edu.itba.sia.g7.sokoban.entities.Character;

class CharacterTest extends Specification {
  def "something"() {
    given: "Any character"
    def character = new Character();

    when: "It does nothing"

    then: "Nothing happens"
    true == true
  }
}
