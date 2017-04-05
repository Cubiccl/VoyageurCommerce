package voyageur.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import voyageur.Utils;
import voyageur.resolution.Aleatoire;
import voyageur.resolution.Glouton;
import voyageur.resolution.Kruskal;
import voyageur.resolution.MST;

/** Traveling Salesman Problem */
public class TSP
{

	private Set<Point> points;

	public TSP()
	{
		this.points = new HashSet<Point>();
	}

	public TSP(Collection<Point> points)
	{
		this.points = new HashSet<Point>();
		this.points.addAll(points);
	}

	public TSP(int nbPoints)
	{
		this(Utils.creerPoints(nbPoints));
	}

	public Circuit aleatoire()
	{
		return new Aleatoire(this).appliquer();
	}

	public Arbre arbreComplet()
	{
		Arbre a = new Arbre();
		for (Point p : this.points)
			for (Point p1 : this.points)
				a.ajouterArc(new Arc(p, p1));
		a.trier();
		return a;
	}

	public Arbre arbreRecouvrement()
	{
		return new Kruskal(this).appliquer();
	}

	public Set<Point> getPoints()
	{
		Set<Point> points = new HashSet<Point>();
		points.addAll(this.points);
		return points;
	}

	public Circuit glouton()
	{
		return new Glouton(this).appliquer();
	}

	public Circuit kruskal()
	{
		return new MST(this).appliquer();
	}

	public int taille()
	{
		return this.points.size();
	}

}
