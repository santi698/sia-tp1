package ar.edu.itba.sia.g7.sokoban;

public enum Direction {
  LEFT(-1, 0),
  UP(0, -1),
  RIGHT(1, 0),
  DOWN(0, 1);

  private int deltaX;
  private int deltaY;

  Direction(int deltaX, int deltaY) {
    this.deltaX = deltaX;
    this.deltaY = deltaY;
  }

  public int getDeltaX() {
    return deltaX;
  }

  public int getDeltaY() {
    return deltaY;
  }
}
