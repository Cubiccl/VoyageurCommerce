package voyageur.resolution;

import java.util.HashMap;

import voyageur.model.Arbre;
import voyageur.model.Arc;
import voyageur.model.Point;
import voyageur.model.TSP;

public class Kruskal
{

	public TSP graphe;
	private HashMap<Point, Point> parents;

	public Kruskal(TSP graphe)
	{
		this.graphe = graphe;
		this.parents = new HashMap<Point, Point>();
	}

	public Arbre appliquer()
	{
		Arbre complet = this.graphe.arbreComplet();
		Arbre recouvrement = new Arbre();
		for (Point p : this.graphe.getPoints())
			this.parents.put(p, p);

		for (int i = 0; i < this.graphe.taille() - 1; ++i)
		{
			Arc choisi = this.trouverArcSuivant(complet);
			this.union(choisi.depart, choisi.arrivee);
		}

		for (Point p : this.graphe.getPoints())
			recouvrement.ajouterArc(new Arc(this.parents.get(p), p));
		recouvrement.parent = this.find(this.graphe.getPoints().iterator().next());

		return recouvrement;
	}

	private Point find(Point p)
	{
		if (this.parents.get(p) != p) return this.find(this.parents.get(p));
		return this.parents.get(p);
	}

	private Arc trouverArcSuivant(Arbre complet)
	{
		for (Arc a : complet.arcs)
		{
			if (this.find(a.depart) != this.find(a.arrivee)) return a;
		}
		return null;
	}

	private void union(Point x, Point y)
	{
		this.parents.put(this.find(x), this.find(y));
	}

}
