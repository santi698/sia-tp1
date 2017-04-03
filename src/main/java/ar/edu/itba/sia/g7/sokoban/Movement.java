package ar.edu.itba.sia.g7.sokoban;


import gps.api.GPSRule;
import gps.api.GPSState;

import java.util.Optional;

public enum Movement implements GPSRule {
  UP(Direction.UP),
  DOWN(Direction.DOWN),
  LEFT(Direction.LEFT),
  RIGHT(Direction.RIGHT);

  private Direction direction;

  Movement(Direction direction) {
    this.direction = direction;
  }

  @Override
  public Integer getCost() {
    return 1;
  }

  @Override
  public String getName() {
    return "Move " + direction.toString();
  }

  @Override
  public Optional<GPSState> evalRule(GPSState state) {
    if (!(state instanceof BoardState)) { return Optional.empty(); }
    BoardState boardState = (BoardState) state;
    return boardState.moveCharacter(direction);
  }
}
