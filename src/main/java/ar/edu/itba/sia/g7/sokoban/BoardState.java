package ar.edu.itba.sia.g7.sokoban;

import java.util.ArrayList;

import ar.edu.itba.sia.g7.gps.api.GPSState;
import ar.edu.itba.sia.g7.sokoban.exception.TooManyPlayersInBoardException;
import ar.edu.itba.sia.g7.sokoban.tiles.Tile;

public class BoardState implements GPSState{

  private ArrayList<ArrayList<Tile>> rows; 
  private Tile characterTile;
  private ArrayList<Tile> boxes;
  private ArrayList<Tile> goals;
	
  public BoardState() {
		this.rows = new ArrayList<ArrayList<Tile>>();
		this.characterTile = null;
		this.boxes = new ArrayList<Tile>();
		this.goals = new ArrayList<Tile>();
	}
  
  public void addRow(ArrayList<Tile> row){
	  this.rows.add(row);
  }
  
  public void addCharacter(Tile characterTile) throws TooManyPlayersInBoardException{
	  if(this.characterTile!=null){
		  throw new TooManyPlayersInBoardException("Board can only have one player");
	  }
	  this.characterTile = characterTile;
  }
  
  public void addBox(Tile boxTile){
	  this.boxes.add(boxTile);
  }
  
  public void addGoal(Tile goalTile){
	  this.goals.add(goalTile);
  }
  
  public boolean hasCharacter(){
	  return this.characterTile != null;
  }
  
  public boolean isPlayable(){
	  return !this.boxes.isEmpty() && !this.goals.isEmpty();
  }
  
  public ArrayList<ArrayList<Tile>> getRows() {
	return rows;
  }
	
}
