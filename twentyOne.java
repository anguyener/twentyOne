package fall2017;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFrame;

public class twentyOne {

	public static ArrayList<String> deck;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		ArrayList<String> twentyOne = new ArrayList<>();
		ArrayList<ArrayList<String>> currentDecks = new ArrayList<ArrayList<String>>(); //do it generically with stuff
		initializeDeck();
		//display(deck);
		initializeRandomDeck(twentyOne, 21);
		display(twentyOne);
		System.out.println("\nRemember one of these cards. Type y to proceed.");
		String ready = scan.nextLine();
		while(!ready.equals("y")) {
			System.out.println("Ready?");
			ready = scan.nextLine();
		}
		currentDecks = deal(twentyOne, 3);
		question(currentDecks);
		//print(currentDecks);
		


	}
	//10H, 3C, AS, QD
	public static void initializeDeck() {
		deck = new ArrayList<>();
		String suit = "CDSH";
		String value = "JQ";
		String card = "";
		int s = -1;
		for(int c = 1; c <= 52; c++) {
			card = "";
			if(c%13 == 1) {
				card = "A";
				s++;
			}
			else if( c%13 == 0) card+="K";
			else if(c%13 < 11) card += c%13;
			else card = value.substring(c%13-11, c%13-10);
			card+= suit.substring(s, s+1);
			deck.add(card);
			//System.out.println(deck[c-1]);
		}
	}

	//Initializes a deck with a certain size up to 52 pulling cards from the global deck

	public static void initializeRandomDeck(ArrayList<String> d, int size) {
		//d = new String[size];
		int rand;
		int c = 0;
		while(c < size) {
			rand = Math.round((float)(Math.random()*51));
			if(!d.contains(deck.get(rand))) {
				d.add(deck.get(rand));
				c++;
			}
		}
	}

	public static void question(ArrayList<ArrayList<String>> decks) {
		int q = 0;
		int c = 0;
		//	decks = deal(decks, decks.size()); initial deal in main...
		while(q < decks.size()) {
			System.out.println("Round: "+q+", stack: "+c);
			display(decks.get(c));
			System.out.println("\nIs your card in this deck? Type y or n.");
			String answer = scan.nextLine();
			if(answer.equals("y") && q == 2) { //dont want to deal 4th time...
				//System.out.println("THIS IS THE END...");
				ArrayList<String> t = collect(decks, c);
				LinkedList<String> temp = new LinkedList<String>();
				for(String card: t) {
					temp.add(card);
				}
				int count = 0;
				while(!temp.isEmpty()) {
					for(int i = 0; i < 21; i++) {
						//System.out.println(temp.getFirst());
						decks.get(count).set(i%7, temp.removeFirst());
						if(i%7 == 0 && i != 0) count++;
					}
				}
				q++;											
				c = 0;
				//print(decks);
				System.out.println("Your card is: "+ decks.get(1).get(3));
			}
			else if(answer.equals("y")) {
				decks = deal(collect(decks, c), decks.size()); //careful with decks.length in collect
				q++;											//currently only 3 cases and default
				c = 0;
			}
			else c++;
		}
	}

	//Displays one deck of cards
	public static void display(ArrayList<String> cards) {
		for(int i = 0; i < cards.size(); i++)
			System.out.print(cards.get(i) + " " );
	}


	//Deals a deck of cards into three piles
	static ArrayList<ArrayList<String>> deal(ArrayList<String> cards, int piles) {
		ArrayList<ArrayList<String>> decks = new ArrayList<ArrayList<String>>();
		decks.add(new ArrayList<String>());
		decks.add(new ArrayList<String>());
		decks.add(new ArrayList<String>());
		for(int i = 0; i < cards.size(); i++) {
			decks.get(i%piles).add(cards.get(i)); //?uses counter to get cards, mods counter to
			//switch between decks
		}
		return reverse(decks); // deal cards face down so reverse order in deck
		//return decks;
	}

	//collects the decks of cards, midds is which one goes in middle

	static ArrayList<String> collect(ArrayList<ArrayList<String>> decks, int midds) { //use cases for one in middle
		ArrayList<String> all = new ArrayList<>();

		switch(midds) {
		case 0: //wrote this case very generally(decks.size()/2) in case there's ever more than three decks
			//for(int i = 1; i < 3; i++) {
			for(String c: decks.get(1)) {
				all.add(c);
			}
			//}
			for(String c: decks.get(0)) {
				all.add(c);
			}
			//for(int i = 3; i < decks.size(); i++) {
			for(String c: decks.get(2)) {
				all.add(c);
			}
			//}
			break;
		case 1: //assumed three decks
			for(int i = 0; i < decks.size(); i++) {
				for(String c: decks.get(i)) {
					all.add(c);
				}
			}
			break;
		case 2: //assumed three decks
			for(String c: decks.get(0)) {
				all.add(c);
			}
			for(String c: decks.get(2)) {
				all.add(c);
			}
			for(String c: decks.get(1)) {
				all.add(c);
			}
			break;
		default: //just did in order, same as case 1
			for(int i = 0; i < decks.size(); i++) {
				for(String c: decks.get(i)) {
					all.add(c);
				}
				break;
			}
		}

		return all;
	}

	static ArrayList<ArrayList<String>> reverse(ArrayList<ArrayList<String>> decks) {
		String a = "";
		String b = "";
		String temp = "";
		for(ArrayList<String> pile: decks) {
			for(int i = 0; i < pile.size()/2; i++) {
				temp = pile.get(i);
				//	System.out.println("Swapping: "+temp+" and "+pile.get(pile.size()-i-1));
				pile.set(i, pile.get(pile.size()-i-1)); // -1 because size not index...
				pile.set(pile.size()-i-1, temp);

			}
		}
		return decks;
	}

	static void print(ArrayList<ArrayList<String>> deck) {
		for(ArrayList<String> d: deck) {
			for(String s: d) {
				System.out.print(s+" ");
			}
		}
	}

}
