package ar.edu.itba.sia.g7.sokoban;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ar.edu.itba.sia.g7.sokoban.entities.Entity;
import ar.edu.itba.sia.g7.sokoban.exception.InvalidMapFormatException;
import ar.edu.itba.sia.g7.sokoban.exception.TooManyPlayersInBoardException;
import ar.edu.itba.sia.g7.sokoban.tiles.Tile;
import ar.edu.itba.sia.g7.sokoban.tiles.TileType;

public class BoardParser {
	private static final char CHARACTER = 'C';
	private static final char BOX = 'B';
	private static final char EMPTY = ' ';
	private static final char WALL = 'W';
	private static final char GOAL = 'G';
	private static final char BOXINGOAL = 'I';

	
	public static BoardState boardFromFile(String filename){
	try{	
		FileInputStream file = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		
		BoardState board = new BoardState();
		String line;
		int  i = 0;
		while ((line = br.readLine()) != null) {
		
			int j = 0;
			ArrayList<Tile> row= new ArrayList<Tile>();
			for (char ch : line.toCharArray()) {
				row.add(buildTile(ch,i,j,board));
				j++;
			}
			board.addRow(row);
			i++;
		}
		br.close();
		
		if (!board.hasCharacter()) {
			throw new InvalidMapFormatException("No character found.");
		}
		
		if(!board.isPlayable()){
			throw new InvalidMapFormatException("A box or a goal is missing");
		}
		
		return board;	
	}catch(Exception e){
		return null;
	}
}
	
	
	private static Tile buildTile(char c, int x, int y,BoardState board) throws InvalidMapFormatException{
		Tile t;
		switch (c){
			case CHARACTER :
				t = new Tile(x,y,Entity.CHARACTER,TileType.FLOOR);
				try{
					board.addCharacter(t);
					return t;
				}catch(TooManyPlayersInBoardException e){
					throw new InvalidMapFormatException("");
				}
			case BOX :
				t = new Tile(x,y,Entity.BOX,TileType.FLOOR);
				board.addBox(t);
				return t;
			case BOXINGOAL :
				t = new Tile(x,y,Entity.BOX,TileType.GOAL);
				board.addBox(t);
				board.addGoal(t);
				return t;
			case EMPTY :
				return new Tile(x,y,Entity.NOENTITY,TileType.FLOOR);
			case WALL :
				return new Tile(x,y,Entity.NOENTITY,TileType.WALL);
			case GOAL :
				t = new Tile(x,y,Entity.NOENTITY,TileType.GOAL);
				board.addGoal(t);
				return t;
			default:
				throw new InvalidMapFormatException("");
		}
	}
}
