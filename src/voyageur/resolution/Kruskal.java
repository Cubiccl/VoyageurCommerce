package voyageur.resolution;

import java.util.HashMap;

import voyageur.model.Arbre;
import voyageur.model.Arc;
import voyageur.model.Point;
import voyageur.model.TSP;

/** Kruskal crée un Arbre de recouvrement. */
public class Kruskal
{

	/** Le graphe concerné. */
	public TSP graphe;
	/** Représente les parents de chaque point. Des points ayant le même parent sont connexes (dans le même groupe). */
	private HashMap<Point, Point> parents;

	public Kruskal(TSP graphe)
	{
		this.graphe = graphe;
		this.parents = new HashMap<Point, Point>();
	}

	/** Applique l'algorithme de Kruskal.
	 * 
	 * @return L'arbre de recouvrement. */
	public Arbre appliquer()
	{
		Arbre complet = this.graphe.arbreComplet(); // On récupèren l'arbre complet
		Arbre recouvrement = new Arbre();
		for (Point p : this.graphe.getPoints())
			this.parents.put(p, p); // On initialise tous les points dans un groupe indépendant chacun

		for (int i = 0; i < this.graphe.taille() - 1; ++i) // On cherche n - 1 arcs
		{
			Arc choisi = this.trouverArcSuivant(complet);
			this.union(choisi.depart, choisi.arrivee);
		}

		// Pour chaque point, on ajoute à l'arbre de recouvrement un arc de lui-même vers son parent direct.
		for (Point p : this.graphe.getPoints())
			recouvrement.ajouterArc(new Arc(this.parents.get(p), p));
		recouvrement.parent = this.find(this.graphe.getPoints().iterator().next());

		return recouvrement;
	}

	/** Trouve le point parent du point en entrée. Méthode récursive. */
	private Point find(Point p)
	{
		if (this.parents.get(p) != p) return this.find(this.parents.get(p));
		return this.parents.get(p);
	}

	/** Trouve le prochain arc à ajouter à l'arbre de recouvrement.
	 * 
	 * @param complet - L'arbre complet. */
	private Arc trouverArcSuivant(Arbre complet)
	{
		for (Arc a : complet.arcs)
		{ // Si deux points ne sont pas connexes, on renvoie l'arc correspondant
			if (this.find(a.depart) != this.find(a.arrivee)) return a;
		}
		return null;
	}

	/** Assemble les groupes des points x et y. */
	private void union(Point x, Point y)
	{
		this.parents.put(this.find(x), this.find(y));
	}

}
