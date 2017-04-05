package voyageur;

import java.util.HashSet;
import java.util.Set;

import voyageur.model.Point;

public class Utils
{

	/** Cr�e un ensemble de points al�atoires, d'abscisse et ordonn�e entre 0 et 1.
	 * 
	 * @param nbPoints - Le nombre de points � cr�er. */
	public static Set<Point> creerPoints(int nbPoints)
	{
		Set<Point> points = new HashSet<Point>();
		for (int i = 0; i < nbPoints; ++i)
			points.add(new Point(Math.random(), Math.random()));
		return points;
	}

}
