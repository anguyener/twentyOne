package fall2017;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuestionPane extends JFrame implements ActionListener {

	//Global buttons for Action Listener
	JButton one; 
	JButton two;
	JButton three;
	
	twentyOne dU; //Instance of twentyOne passed on from startPane

	public QuestionPane(twentyOne dosUno) {
		super();
		dU = dosUno;
		this.setBackground(Color.GREEN.darker());
		this.setLayout(new GridLayout(5, 0, 0, 10));

		JPanel header = new JPanel();
		header.setBackground(Color.GREEN.darker());
		header.setOpaque(true); 
		header.setLayout(new GridBagLayout());
		JLabel message = new JLabel("Which row is your card in?");
		message.setFont(new Font("Serif", Font.PLAIN, 21));
		header.add(message);
		this.add(header);

		JPanel firstRow = new JPanel();
		firstRow.setBackground(Color.GREEN.darker());
		firstRow.setOpaque(true);
		firstRow.setLayout(new GridLayout(1, 7, 10, 0));
		for(int n = 0; n < 7; n++)
			firstRow.add(new JLabel(dU.XXI.get(n)));
		this.add(firstRow);

		JPanel secondRow = new JPanel();
		secondRow.setBackground(Color.GREEN.darker());
		secondRow.setOpaque(true);
		secondRow.setLayout(new GridLayout(1, 7));
		for(int n = 7; n < 14; n++)
			secondRow.add(new JLabel(dU.XXI.get(n)));
		this.add(secondRow);

		JPanel thirdRow = new JPanel();
		thirdRow.setBackground(Color.GREEN.darker());
		thirdRow.setOpaque(true);
		thirdRow.setLayout(new GridLayout(1, 7, 10, 0));
		for(int n = 14; n < 21; n++)
			thirdRow.add(new JLabel(dU.XXI.get(n)));
		this.add(thirdRow);

		JPanel buttonRow = new JPanel();
		buttonRow.setBackground(Color.GREEN.darker());
		buttonRow.setOpaque(true);
		one = new JButton("1");
		one.setPreferredSize(new Dimension(70, 20));
		one.addActionListener(this);
		buttonRow.add(one);

		two = new JButton("2");
		two.setPreferredSize(new Dimension(70, 20));
		two.addActionListener(this);
		buttonRow.add(two);

		three = new JButton("3");
		three.setPreferredSize(new Dimension(70, 20));
		three.addActionListener(this);
		buttonRow.add(three);

		this.add(buttonRow);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Depending on button pressed changes global variables of twentyOne
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == one) {
			dU.rowNum = 1;
			dU.caseNum++;
			if(dU.caseNum == 3)
				getAnswer();
			else
				questioning();
		}
		else if(e.getSource() == two) {
			dU.rowNum = 2;
			dU.caseNum++;
			if(dU.caseNum == 3)
				getAnswer();
			else
				questioning();
		}
		else {
			dU.rowNum = 3;
			dU.caseNum++;
			if(dU.caseNum == 3)
				getAnswer();
			else
				questioning();
		}
	}
	
	//Triggers twentyOne question and new questionPane
	public void questioning() {
		dU.question();
		QuestionPane qp = new QuestionPane(dU);
		qp.pack();
		qp.setVisible(true);
	}
	
	//Triggers AnswerPane, gives it answer card as address string
	public void getAnswer() {
		AnswerPane ap = new AnswerPane(dU.collect(dU.rowNum-1).get(10)); 
		ap.pack();
		ap.setVisible(true);
	}

}

