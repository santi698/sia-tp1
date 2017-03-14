package ar.edu.itba.sia.g7.sokoban.tiles;

import ar.edu.itba.sia.g7.sokoban.entities.Entity;
import ar.edu.itba.sia.g7.sokoban.entities.NullEntity;

public class WallTile implements Tile {
  public Entity getEntity() {
    return NullEntity.getInstance();
  }

  public void setEntity(Entity entity) {
    throw new IllegalAccessError();
  }

  public boolean isEmpty() {
    return false;
  }
}
