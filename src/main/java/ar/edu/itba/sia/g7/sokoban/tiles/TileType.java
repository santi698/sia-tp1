package ar.edu.itba.sia.g7.sokoban.tiles;

public enum TileType {
  WALL(false),
  GOAL(true),
  FLOOR(true);

  private boolean canHold;

  TileType(boolean canHold) {
    this.canHold = canHold;
  }

  public boolean canHoldEntities() {
    return canHold;
  }
}
