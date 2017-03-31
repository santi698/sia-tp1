package ar.edu.itba.sia.g7.sokoban;

import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.entities.Entity;
import ar.edu.itba.sia.g7.sokoban.exception.TooManyPlayersInBoardException;
import ar.edu.itba.sia.g7.sokoban.tiles.Tile;
import ar.edu.itba.sia.g7.sokoban.tiles.TileType;
import ar.edu.itba.sia.gps.api.GPSState;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardState implements GPSState {

  private List<List<Tile>> rows;
  private Tile characterTile;
  private List<Tile> boxes;
  private List<Tile> goals;

  public BoardState() {
    this.characterTile = null;
    this.rows = new ArrayList<>();
    this.boxes = new ArrayList<>();
    this.goals = new ArrayList<>();
  }

  private BoardState(List<List<Tile>> rows,
                     Tile characterTile,
                     List<Tile> boxes,
                     List<Tile> goals) {
    this.rows = cloneRows(rows);
    this.characterTile = new Tile(characterTile);
    this.boxes = boxes.stream()
                      .map((box) -> new Tile(box))
                      .collect(Collectors.toCollection(ArrayList::new));
    this.goals = goals.stream()
                      .map((goal) -> new Tile(goal))
                      .collect(Collectors.toCollection(ArrayList::new));
  }

  public BoardState clone() {
    return new BoardState(this.rows, this.characterTile, this.boxes, this.goals);
  }

  private ArrayList<List<Tile>> cloneRows(List<List<Tile>> rows) {
    ArrayList<List<Tile>> clonedRows = new ArrayList<>();
    for (List<Tile> row : rows) {
      ArrayList<Tile> newRow = new ArrayList<>();
      for (Tile tile : row) {
        newRow.add(new Tile(tile));
      }
      clonedRows.add(newRow);
    }
    return clonedRows;
  }

  public void addRow(ArrayList<Tile> row) {
    this.rows.add(row);
  }

  public void addCharacter(Tile characterTile) throws TooManyPlayersInBoardException{
    if (this.characterTile != null) {
      throw new TooManyPlayersInBoardException("Board can only have one player");
    }
    this.characterTile = characterTile;
  }

  public void addBox(Tile boxTile) {
    this.boxes.add(boxTile);
  }

  public void addGoal(Tile goalTile) {
    this.goals.add(goalTile);
  }

  public boolean hasCharacter() {
    return this.characterTile != null;
  }

  public boolean isPlayable() {
    return !this.boxes.isEmpty() && !this.goals.isEmpty();
  }

  public List<List<Tile>> getRows() {
    return rows;
  }

  public boolean isSolved() {
    for (Tile t : this.goals) {
      Point position = t.getPosition();
      if (this.rows.get(position.getY()).get(position.getX()).getEntity() != Entity.BOX) {
        return false;
      }
    }
    return true;
  }

  public Optional<GPSState> moveCharacter(Direction direction) {
    Point newCharacterPosition = characterTile.getPosition().add(direction.getDeltaX(), direction.getDeltaY());
    if (!canMoveTo(newCharacterPosition, direction)) {
      return Optional.empty();
    }
    BoardState newState = clone();
    Point targetCharacterPosition = characterTile.getPosition().add(direction.getDeltaX(),
                                                                    direction.getDeltaY());
    Tile newCharacterTile = newState.getTileAt(targetCharacterPosition).get();
    if (newCharacterTile.getEntity() == Entity.BOX) {
      newState.moveBox(targetCharacterPosition, direction);
    }

    newCharacterTile.setEntity(Entity.CHARACTER);
    newState.getTileAt(newState.characterTile.getPosition()).get().setEntity(Entity.NOENTITY);
    newState.characterTile = newCharacterTile;
    return Optional.of(newState);
  }

  public void moveBox(Point position, Direction direction) {
    Tile boxTile = getTileAt(position).get();
    boxes.remove(boxTile);
    boxTile.setEntity(Entity.NOENTITY);
    Tile targetBoxTile = getTileAt(position.add(direction.getDeltaX(),
                                                direction.getDeltaY())).get();
    targetBoxTile.setEntity(Entity.BOX);
    boxes.add(targetBoxTile);
  }

  public Optional<Tile> getTileAt(Point position) {
    if (!hasTile(position)) { return Optional.empty(); }
    return Optional.of(rows.get(position.getY()).get(position.getX()));
  }

  public boolean hasTile(Point position) {
    if (position.getY() < 0 || position.getY() >= rows.size() ||
        position.getX() < 0 || position.getX() >= rows.get(0).size()) {
      return false;
    }
    return true;
  }

  public boolean canMoveTo(Point position, Direction direction) {
    if (!hasTile(position)) { return false; }
    Optional<Tile> tileAtTarget = getTileAt(position);
    return tileAtTarget.filter((tile) -> tile.getType() != TileType.WALL)
                       .map((tile) -> {
      if (tile.canMoveInto()) { return true; }
      Point positionNextToTarget = position.add(direction.getDeltaX(), direction.getDeltaY());
      return getTileAt(positionNextToTarget).filter((nextTile) -> nextTile.canMoveInto())
                                            .isPresent();

    }).orElse(false);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof BoardState)) {
      return false;
    }
    BoardState aState = (BoardState) o;
    for(int i = 0; i < rows.size(); i++) {
      List<Tile> row = rows.get(i);
      for(int j = 0; j < row.size(); j++) {
        Tile tile = row.get(j);
        if (!tile.equals(aState.rows.get(i).get(j))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return rows.hashCode();
  }

  @Override
  public String toString() {
    return BoardParser.boardToString(this);
  }
}
