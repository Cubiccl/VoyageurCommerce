package voyageur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fr.cubi.cubigui.*;

public class Window extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -908351157512569132L;

	private CTextArea areaOutput;
	private CButton buttonDo;
	private CEntry entryGraphe, entrySommets;
	private CCheckBox random, glouton, kruskal, m2opt;

	public Window()
	{
		JScrollPane s = new JScrollPane(this.areaOutput = new CTextArea(""));

		CPanel p = new CPanel();
		GridBagConstraints gbc = p.createGridBagLayout();
		gbc.gridwidth = 2;
		p.add((this.entryGraphe = new CEntry("Nombre de graphes :")).container, gbc);
		++gbc.gridy;
		p.add((this.entrySommets = new CEntry("Nombre de sommets par graphe :")).container, gbc);

		++gbc.gridy;
		gbc.gridwidth = 1;
		p.add(this.random = new CCheckBox("Méthode aléatoire"), gbc);
		++gbc.gridx;
		p.add(this.glouton = new CCheckBox("Méthode Glouton"), gbc);
		--gbc.gridx;
		++gbc.gridy;
		p.add(this.kruskal = new CCheckBox("Méthode Kruskal"), gbc);
		++gbc.gridx;
		p.add(this.m2opt = new CCheckBox("Optimisation 2-opt pour Kruskal"), gbc);

		--gbc.gridx;
		++gbc.gridy;
		gbc.gridwidth = 2;
		p.add(this.buttonDo = new CButton("Go !"), gbc);

		gbc.gridwidth = 1;
		gbc.gridheight = 5;
		gbc.gridx = 2;
		gbc.gridy = 0;
		p.add(s, gbc);

		this.areaOutput.setBackground(Color.WHITE);
		this.areaOutput.setBorder(new RoundedCornerBorder(true));
		s.setPreferredSize(new Dimension(400, 300));
		s.setMinimumSize(new Dimension(400, 300));
		s.setBorder(null);

		this.kruskal.addActionListener(this);
		this.buttonDo.addActionListener(this);
		this.m2opt.setEnabled(false);

		this.setContentPane(p);
		this.setTitle("Voyageur de Commerce");
		this.setSize(1000, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.kruskal) this.m2opt.setEnabled(this.kruskal.isSelected());
		else if (e.getSource() == this.buttonDo) this.doAlgorithm();
	}

	public void addText(String text)
	{
		if (this.areaOutput.getText().equals("")) this.areaOutput.setText(text);
		else this.areaOutput.setText(this.areaOutput.getText() + "\n" + text);
	}

	private void doAlgorithm()
	{
		Main.previousMain(Integer.parseInt(this.entryGraphe.getText()), Integer.parseInt(this.entrySommets.getText()), this.random.isSelected(), this.glouton.isSelected(), this.kruskal.isSelected(), this.m2opt.isSelected());
	}

}
