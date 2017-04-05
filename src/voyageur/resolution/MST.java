package voyageur.resolution;

import java.util.ArrayList;
import java.util.Stack;

import voyageur.model.Arbre;
import voyageur.model.Circuit;
import voyageur.model.Point;
import voyageur.model.TSP;

public class MST extends Methode
{

	private Stack<Point> pile;
	private Arbre recouvrement;

	public MST(TSP graphe)
	{
		super(graphe);
		this.recouvrement = graphe.arbreRecouvrement();
		this.pile = new Stack<Point>();
	}
	
	@Override
	public Circuit appliquer()
	{
		this.pile.clear();
		return super.appliquer();
	}

	@Override
	protected Point choisirPremier(ArrayList<Point> restants)
	{
		Point a = this.recouvrement.parent;
		this.pile.push(a);
		return a;
	}

	@Override
	protected Point choisirSuivant(ArrayList<Point> restants, Point precedent)
	{
		while (!this.pile.isEmpty())
		{
			for (Point point : this.recouvrement.enfants(this.pile.peek()))
			{
				if (restants.contains(point))
				{
					this.pile.push(point);
					return point;
				}
			}
			this.pile.pop();
		}
		return null;
	}

}
