package ar.edu.itba.sia.g7.sokoban.tiles;

import ar.edu.itba.sia.g7.sokoban.entities.NullEntity;
import ar.edu.itba.sia.g7.sokoban.entities.Entity;

public class FloorTile implements Tile {
  private Entity entity;

  public FloorTile() {}

  public FloorTile(Entity entity) {
    this.entity = entity;
  }

  public Entity getEntity() {
    return entity;
  }

  public void setEntity(Entity entity) {
    this.entity = entity;
  }

  public boolean isEmpty() {
    return this.entity == NullEntity.getInstance();
  }
}
