package voyageur.resolution;

import java.util.ArrayList;

import voyageur.model.Point;
import voyageur.model.TSP;

public class Aleatoire extends Methode
{

	public Aleatoire(TSP graphe)
	{
		super(graphe);
	}

	@Override
	protected Point choisirPremier(ArrayList<Point> restants)
	{
		return this.aleatoire(restants);
	}

	@Override
	protected Point choisirSuivant(ArrayList<Point> restants, Point precedent)
	{
		return this.aleatoire(restants);
	}

}
