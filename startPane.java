package fall2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;//????????????????????????
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class startPane extends JFrame implements ActionListener {
	
	JButton next; //Global so ActionListener works
	twentyOne dU; //Instance of twentyOne
	
	public startPane(twentyOne dosUno) {
		super();
		this.dU = dosUno;
		this.setBackground(Color.GREEN.darker());
		setBackground(Color.GREEN.darker());
		this.setLayout(new GridLayout(5, 0, 0, 10));
		
		JPanel header = new JPanel();
		header.setBackground(Color.GREEN.darker());
		header.setOpaque(true); 
		header.setLayout(new GridBagLayout());
		JLabel message = new JLabel("Remember one of these cards and select Next to continue.");
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
		next = new JButton("Next");
		next.setPreferredSize(new Dimension(70, 20));
		next.addActionListener(this);
		buttonRow.add(next);
		this.add(buttonRow);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Trigger next Pane when button pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == next) {
			QuestionPane qp = new QuestionPane(dU);
			qp.pack();
			qp.setVisible(true);

		}
	}
	
	public static void main(String[] args) {
		twentyOne dosUno = new twentyOne();
		startPane sp = new startPane(dosUno);
		sp.pack();
		sp.setVisible(true);
		
	}

}
