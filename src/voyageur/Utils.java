package voyageur;

import java.util.HashSet;
import java.util.Set;

import voyageur.model.Point;

public class Utils
{

	public static Set<Point> creerPoints(int nbPoints)
	{
		Set<Point> points = new HashSet<Point>();
		for (int i = 0; i < nbPoints; ++i)
			points.add(new Point(Math.random(), Math.random()));
		return points;
	}

}
