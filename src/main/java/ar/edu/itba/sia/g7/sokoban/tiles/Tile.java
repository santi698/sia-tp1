package ar.edu.itba.sia.g7.sokoban.tiles;

import ar.edu.itba.sia.g7.sokoban.entities.Entity;

public interface Tile {
  Entity getEntity();

  void setEntity(Entity entity);

  boolean isEmpty();
}
