package fall2017;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnswerPane extends JFrame {
	
	public AnswerPane(String card) {
		super();
		int n;
		this.setLayout(new GridLayout(1, 1));
		String stub = "/home/anguyen/workspace/2017/src/fall2017/PlayingCards/";
		
		if(card.substring(0, 2).equals("10"))
			n = 2;
		else n = 1;
		
		String address = stub+card.substring(n)+"/"+card.substring(0, n)+".png";
		
		JPanel header = new JPanel();
		header.setSize(200, 70);
		JLabel words = new JLabel("Your card is:", new ImageIcon(address), JLabel.CENTER);
		
		words.setVerticalTextPosition(JLabel.TOP);
		words.setHorizontalTextPosition(JLabel.CENTER);
		words.setFont(new Font("Serif", Font.BOLD, 20));
		header.setBackground(Color.GREEN.darker());
		header.setOpaque(true); 
		header.add(words);
		this.add(header);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
