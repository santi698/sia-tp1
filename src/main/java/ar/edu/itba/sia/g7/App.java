package ar.edu.itba.sia.g7;

import java.util.Scanner;

import ar.edu.itba.sia.g7.sokoban.BoardParser;
import ar.edu.itba.sia.g7.sokoban.BoardState;

/**
 * Hello world!
 *
 */
public class App {
  public static void main( String[] args )
  {
	  Scanner userInput = new Scanner(System.in);
		
		System.out.println("Sokoban - SIA");
		System.out.println("Elegir un numero de mapa:");
		int n = userInput.nextInt();
		BoardState board = BoardParser.boardFromFile("maps/" + n +".txt");
		
		if(board!=null){
			System.out.println(board.getRows().get(2).get(2).getEntity());
			System.out.println(board.isSolved());
		}else{
			System.out.println("mapa invalido");
		}
		

  }
}
