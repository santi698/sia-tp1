package ar.edu.itba.sia.g7.sokoban.tiles;

import ar.edu.itba.sia.g7.sokoban.Point;
import ar.edu.itba.sia.g7.sokoban.entities.Entity;

import java.util.List;
import java.util.Objects;

public class Tile {
  private Point position;
  private Entity entity;
  private TileType type;

  public Tile(int xPosition, int yPosition, Entity entity, TileType type) {
    this.position = new Point(xPosition, yPosition);
    this.entity = entity;
    this.type = type;
  }

  public Tile(Point position, Entity entity, TileType type) {
    this(position.getX(), position.getY(), entity, type);
  }

  public Tile(Tile tile) {
    this(new Point(tile.getPosition()), tile.getEntity(), tile.getType());
  }

  public Entity getEntity(){
    return entity;
  }

  public void setEntity(Entity entity) {
    this.entity = entity;
  }

  public boolean isEmpty(){
    return this.type == TileType.FLOOR;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tile tile = (Tile) o;
    return entity == tile.entity &&
      type == tile.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(entity, type);
  }

  public Point getPosition() {
    return position;
  }

  public void setPosition(Point position) {
    this.position = position;
  }

  public TileType getType(){
    return type;
  }

  public boolean canMoveInto() {
    return type.canHoldEntities() && (entity == Entity.NOENTITY || entity == Entity.CHARACTER);
  }
  public int getMinDistFromTile(List<Tile> tiles){
    return tiles.stream()
                .mapToInt((box) -> position.distance(box.position))
                .min()
                .orElse(0);
  }

  public static enum TileType {
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
}
