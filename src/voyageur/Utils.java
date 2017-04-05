package voyageur;

import java.util.HashSet;
import java.util.Set;

import voyageur.model.Point;

public class Utils
{

	/** Crée un ensemble de points aléatoires, d'abscisse et ordonnée entre 0 et 1.
	 * 
	 * @param nbPoints - Le nombre de points à créer. */
	public static Set<Point> creerPoints(int nbPoints)
	{
		Set<Point> points = new HashSet<Point>();
		for (int i = 0; i < nbPoints; ++i)
			points.add(new Point(Math.random(), Math.random()));
		return points;
	}

}
