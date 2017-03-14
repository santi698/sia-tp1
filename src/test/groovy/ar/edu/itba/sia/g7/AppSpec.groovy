package ar.edu.itba.sia.g7;

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit test for simple App.
 */
class AppSpec extends Specification {
  def "true is true"() {
    given: "certainty"
    def a = true

    when: "whenever"

    then: "The result MUST be 22"
    a == true
  }
}
