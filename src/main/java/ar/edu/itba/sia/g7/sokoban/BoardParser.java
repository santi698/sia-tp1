package ar.edu.itba.sia.g7.sokoban;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.sia.g7.sokoban.entities.Entity;
import ar.edu.itba.sia.g7.sokoban.exception.InvalidMapFormatException;
import ar.edu.itba.sia.g7.sokoban.exception.TooManyPlayersInBoardException;
import ar.edu.itba.sia.g7.sokoban.tiles.Tile;

public class BoardParser {
  private static final char CHARACTER = 'C';
  private static final char BOX = 'B';
  private static final char EMPTY = '·';
  private static final char WALL = 'W';
  private static final char GOAL = 'G';
  private static final char BOXINGOAL = 'X';
  private static final char CHARACTER_IN_GOAL = 'Q';


  public static BoardState boardFromFile(String filename){
  try {
    FileInputStream file = new FileInputStream(filename);
    BufferedReader br = new BufferedReader(new InputStreamReader(file));

    BoardState board = new BoardState();
    String line;
    int  i = 0;
    while ((line = br.readLine()) != null) {

      int j = 0;
      ArrayList<Tile> row = new ArrayList<Tile>();
      for (char ch : line.toCharArray()) {
        row.add(buildTile(ch, j, i, board));
        j++;
      }
      board.addRow(row);
      i++;
    }
    br.close();

    if (!board.hasCharacter()) {
      throw new InvalidMapFormatException("No character found.");
    }

    if (!board.isPlayable()) {
      throw new InvalidMapFormatException("A box or a goal is missing");
    }

    return board;
  } catch(Exception e) {
    System.err.println(e);
    return null;
  }
}


  private static Tile buildTile(char c, int x, int y, BoardState board) throws InvalidMapFormatException {
    Tile t;
    switch (c) {
      case CHARACTER:
        t = new Tile(x, y, Entity.CHARACTER, Tile.TileType.FLOOR);
        try {
          board.addCharacter(t);
          return t;
        } catch (TooManyPlayersInBoardException e) {
          throw new InvalidMapFormatException("");
        }
      case BOX:
        t = new Tile(x, y, Entity.BOX, Tile.TileType.FLOOR);
        board.addBox(t);
        return t;
      case BOXINGOAL:
        t = new Tile(x, y, Entity.BOX, Tile.TileType.GOAL);
        board.addBox(t);
        board.addGoal(t);
        return t;
      case CHARACTER_IN_GOAL:
        t = new Tile(x, y, Entity.CHARACTER, Tile.TileType.GOAL);
        try {
          board.addCharacter(t);
        } catch (TooManyPlayersInBoardException e) {
          throw new InvalidMapFormatException("");
        }
        board.addGoal(t);
        return t;
      case EMPTY:
        return new Tile(x, y, Entity.NOENTITY, Tile.TileType.FLOOR);
      case WALL:
        return new Tile(x, y, Entity.NOENTITY, Tile.TileType.WALL);
      case GOAL:
        t = new Tile(x, y, Entity.NOENTITY, Tile.TileType.GOAL);
        board.addGoal(t);
        return t;
      default:
        throw new InvalidMapFormatException("");
    }
  }

  public static String boardToString(BoardState board) {
    StringBuffer buffer = new StringBuffer();
    for (List<Tile> row : board.getRows()) {
      for (Tile t : row) {
        switch (t.getEntity()) {
          case CHARACTER:
            if (t.getType() == Tile.TileType.FLOOR) {
              buffer.append(CHARACTER);
            } else {
              buffer.append(CHARACTER_IN_GOAL);
            }
            break;
          case BOX:
            if (t.getType() == Tile.TileType.FLOOR)
              if (board.isInCorner(t)) {
                buffer.append("\u001B[31m" + BOX + "\u001B[0m");
              } else {
                buffer.append(BOX);
              }
            else
              buffer.append("\u001B[32m" + BOXINGOAL + "\u001B[0m");
            break;
          case NOENTITY:
            switch (t.getType()) {
              case FLOOR:
                buffer.append(EMPTY);
                break;
              case WALL:
                buffer.append(WALL);
                break;
              case GOAL:
                buffer.append(GOAL);
                break;
            }
            break;
        }
      }
      buffer.append('\n');
    }
    return buffer.toString();
  }
}
