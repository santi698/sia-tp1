package ar.edu.itba.sia.g7.sokoban;

import java.util.Arrays;

import ar.edu.itba.sia.g7.gps.api.GPSState;

import ar.edu.itba.sia.g7.sokoban.tiles.Tile;
import ar.edu.itba.sia.g7.sokoban.entities.Box;
import ar.edu.itba.sia.g7.sokoban.entities.Character;

public class State implements GPSState {
  private Tile[] map;
  private Character character;
  private Box[] boxes;

  public Tile[] getMap() {
    return map;
  }

  public boolean equals(Object o) {
    if (!(o instanceof State)) {
      return false;
    }
    return Arrays.equals(map, ((State) o).getMap());
  }

  public int hashCode() {
    return Arrays.hashCode(map);
  }
}
