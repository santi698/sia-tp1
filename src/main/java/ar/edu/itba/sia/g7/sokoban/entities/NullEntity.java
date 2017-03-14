package ar.edu.itba.sia.g7.sokoban.entities;

public class NullEntity implements Entity {
  private static NullEntity instance;
  protected NullEntity() {}

  public static NullEntity getInstance() {
    if (instance == null) {
      instance = new NullEntity();
    }
    return instance;
  }
}
