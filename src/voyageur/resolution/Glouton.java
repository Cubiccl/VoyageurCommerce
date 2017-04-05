package voyageur.resolution;

import java.util.ArrayList;

import voyageur.model.Point;
import voyageur.model.TSP;

/** Une méthode qui compare les distances de tous les points restants pour prendre le plus proche. */
public class Glouton extends Methode
{

	public Glouton(TSP graphe)
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
		Point proche = restants.get(0);

		for (Point point : restants)
			if (point.distance(precedent) < proche.distance(precedent)) proche = point;

		return proche;
	}

}
