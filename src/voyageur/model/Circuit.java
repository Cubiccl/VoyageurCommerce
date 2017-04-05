package voyageur.model;

import java.util.ArrayList;
import java.util.List;

public class Circuit
{

	private ArrayList<Point> circuit;

	public Circuit()
	{
		this.circuit = new ArrayList<Point>();
	}

	public Circuit(List<Point> circuit)
	{
		this.circuit = new ArrayList<Point>();
		for (Point p : circuit)
			this.ajouterPoint(p);
	}

	public void ajouterPoint(Point p)
	{
		if (!this.circuit.contains(p)) this.circuit.add(p);
	}

	public Circuit applique2Opt(int p1, int p2)
	{
		ArrayList<Point> c2 = new ArrayList<Point>();

		// De 0 à p1
		for (int i = 0; i < p1; ++i)
			c2.add(this.circuit.get(i));
		// De p1 à p2, à l'envers
		for (int i = p2; i >= p1; --i)
			c2.add(this.circuit.get(i));
		// De p2 à end
		for (int i = p2 + 1; i < this.circuit.size(); ++i)
			c2.add(this.circuit.get(i));

		return new Circuit(c2);
	}

	public Point fin()
	{
		return this.circuit.get(this.circuit.size() - 1);
	}

	public double gain(int p1, int p2)
	{
		return this.applique2Opt(p1, p2).longueur() - this.longueur();
	}

	public double longueur()
	{
		double longueur = 0;
		for (int i = 1; i < this.circuit.size(); ++i)
			longueur += this.circuit.get(i - 1).distance(this.circuit.get(i));

		longueur += this.fin().distance(this.circuit.get(0));
		return longueur;
	}

	public int taille()
	{
		return this.circuit.size();
	}

	@Override
	public String toString()
	{
		String s = "";
		for (Point point : circuit)
			s += point.x + "," + point.y + "\n";
		return s;
	}

}
