package ar.edu.itba.sia.g7.sokoban.tiles;

import ar.edu.itba.sia.g7.sokoban.entities.Entity;

public class Tile {
  private int xPosition, yPosition;
  private Entity entity;
  private TileType type;

  public Tile(int xPosition, int yPosition, Entity entity, TileType type) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.entity = entity;
		this.type = type;
  }
  
  public Entity getEntity(){
	  return entity;
  }

  public boolean isEmpty(){
	  return this.type == TileType.FLOOR;
  }

  public int getxPosition() {
	return xPosition;
  }
  
  public int getyPosition() {
		return yPosition;
  }

  public TileType getType(){
    return type;
  }
}
