package voyageur.resolution;

import java.util.ArrayList;
import java.util.Stack;

import voyageur.model.Arbre;
import voyageur.model.Circuit;
import voyageur.model.Point;
import voyageur.model.TSP;

/** Méthode du Minimum Spanning Tree.<br />
 * Utilise un Arbre de recouvrement. Le Point suivant est l'un des enfants du point précédent. Si tous les enfants sont ajoutés, on remonte au parent. */
public class MST extends Methode
{

	/** Store les parents qui ont été traversés. */
	private Stack<Point> pile;
	/** L'arbre de recouvrement du graphe. */
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
	{ // Le premier point est le parent commun à tous les points.
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
			{ // On cherche un enfant parmi le dernier parent ajouté
				if (restants.contains(point))
				{
					this.pile.push(point);
					return point;
				}
			}
			this.pile.pop(); // Si on n'en trouve pas, on revient au parent précédent
		}
		return null;
	}

}
