package fall2017;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class twentyOne {

	public static ArrayList<String> deck; //52 deck of cards
	public static ArrayList<String> twentyOne; //the 21 cards the instance is using
	public static ArrayList<ImageIcon> XXI; //twentyOne but with images
	public static ArrayList<ArrayList<String>> decks; //twentyOne but split into three separate arrayLists for shuffling
	static int caseNum; //how many times questioned
	static int rowNum; //what row card is in
	static String stub = "/home/anguyen/workspace/2017/src/fall2017/PlayingCards/"; //address for card pngs

	public twentyOne() {
		caseNum = 0;
		rowNum = 0;
		initializeDeck();
		twentyOne = new ArrayList<>();
		decks = new ArrayList<ArrayList<String>>();
		initializeRandomDeck(twentyOne, 21);
		decks = deal(twentyOne, 3);
		twentyOne = collect(1);
		XXI = translate(twentyOne);
	}
	//Creates the 52 deck of cards
	public static void initializeDeck() {
		deck = new ArrayList<>();
		String[] suit = {"Clubs", "Diamonds", "Spades", "Hearts"};
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
			card+= suit[s];
			deck.add(card);
		}
	}

	//Initializes a random deck with a certain size up to 52 pulling cards from the global deck
	public static void initializeRandomDeck(ArrayList<String> d, int size) {
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
	
	//Translates cards from Strings to ImageIcons for JFrames
	public static ArrayList<ImageIcon> translate(ArrayList<String> cards) {
		ArrayList<ImageIcon> cardImgs = new ArrayList<>();
		int n;
		for(String card: cards) {
			//System.out.print(card);
			if(card.substring(0, 2).equals("10"))
				n = 2;
			else n = 1;
			String address = stub+card.substring(n)+"/"+card.substring(0, n)+".png";
		//	System.out.println("--"+address);
			ImageIcon cardImg = new ImageIcon(address);
			Image cI = cardImg.getImage().getScaledInstance(85, 127, java.awt.Image.SCALE_SMOOTH);
			cardImgs.add(new ImageIcon(cI));
		}
		return cardImgs;
	}

	//Collects and deals the deck differently according to which deck the answer is in
	public static void question() {
		
		if(caseNum == 0) {
			twentyOne.clear();
			for(int n = 0; n < 3; n++) {
				for(String s: decks.get(n))
					twentyOne.add(s);
			}
		}
		
		else if(caseNum < 3) {
			System.out.println("OLD DECK: ");
			print(decks);
			decks = deal(collect(rowNum-1), 3);
		//	twentyOne = decks.get(0); //bad. for some reason equals goes both ways?
			twentyOne.clear();
			for(int n = 0; n < 3; n++) {
				for(String s: decks.get(n))
					twentyOne.add(s);
			//	System.out.println("N: "+ n);
			//	print(decks);
			}
		}
		
		else {
			twentyOne = collect(rowNum-1);
		}
		XXI = translate(twentyOne);
	}

	//Displays one deck of cards
	public static void display(ArrayList<String> cards) {
		for(int i = 0; i < cards.size(); i++)
			System.out.print(cards.get(i) + " " );
		System.out.println();
	}


	//Deals a deck of cards into three piles
	static ArrayList<ArrayList<String>> deal(ArrayList<String> cards, int piles) {
		decks.clear();

		decks.add(new ArrayList<String>());
		decks.add(new ArrayList<String>());
		decks.add(new ArrayList<String>());
		for(int i = 0; i < cards.size(); i++) {
			decks.get(i%piles).add(cards.get(i)); //uses counter to get cards, mods counter to
			//switch between decks
		}
		System.out.println("dealing... reversing decks");
		return reverse(); // deal cards face down so reverse order in deck
	}

	//collects the decks of cards, midds is which one goes in middle
	static ArrayList<String> collect(int midds) { //use cases for one in middle
		ArrayList<String> all = new ArrayList<>();

		switch(midds) {
		case 0: 
			for(String c: decks.get(1)) {
				all.add(c);
			}
			for(String c: decks.get(0)) {
				all.add(c);
			}
			for(String c: decks.get(2)) {
				all.add(c);
			}
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

	//Reverses the order of the deck because cards are dealt face down and turned over
	static ArrayList<ArrayList<String>> reverse() { //made decks global not local
		String a = "";
		String b = "";
		String temp = "";
		for(ArrayList<String> pile: decks) {
			for(int i = 0; i < pile.size()/2; i++) {
				temp = pile.get(i);
				pile.set(i, pile.get(pile.size()-i-1)); // -1 because size not index...
				pile.set(pile.size()-i-1, temp);

			}
		}
		return decks;
	}

	//Print function for decks
	static void print(ArrayList<ArrayList<String>> deck) {
		for(ArrayList<String> d: deck) {
			for(String s: d) {
				System.out.print(s+" ");
			}
			System.out.println();
		}
	}
}
