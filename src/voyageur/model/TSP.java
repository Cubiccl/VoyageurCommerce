package voyageur.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import voyageur.Utils;
import voyageur.resolution.Aleatoire;
import voyageur.resolution.Glouton;
import voyageur.resolution.Kruskal;
import voyageur.resolution.MST;

/** Traveling Salesman Problem <br/>
 * Représente un ensemble de points à utiliser pour le TSP. */
public class TSP
{

	/** Tous les sommets du graphe. */
	private Set<Point> points;

	/** Crée un graphe vide. */
	public TSP()
	{
		this.points = new HashSet<Point>();
	}

	/** Crée un graphe à points définis.
	 * 
	 * @param points - Les points du graphe. */
	public TSP(Collection<Point> points)
	{
		this.points = new HashSet<Point>();
		this.points.addAll(points);
	}

	/** Crée un graphe à points aléatoires.
	 * 
	 * @param nbPoints - Le nombre de points à créer. */
	public TSP(int nbPoints)
	{
		this(Utils.creerPoints(nbPoints));
	}

	/** @return Un Circuit aléatoire pour ce graphe. */
	public Circuit aleatoire()
	{
		return new Aleatoire(this).appliquer();
	}

	/** @return Un Arbre complet correspondant à ce graphe. */
	public Arbre arbreComplet()
	{
		Arbre a = new Arbre();
		for (Point p : this.points)
			for (Point p1 : this.points)
				a.ajouterArc(new Arc(p, p1));
		a.trier();
		return a;
	}

	/** @return Un arbre de recouvrement pour ce graphe. */
	public Arbre arbreRecouvrement()
	{
		return new Kruskal(this).appliquer();
	}

	/** Getter pour {@link TSP#points} */
	public Set<Point> getPoints()
	{
		Set<Point> points = new HashSet<Point>();
		points.addAll(this.points);
		return points;
	}

	/** @return Un Circuit calculé par l'algorithme Glouton. */
	public Circuit glouton()
	{
		return new Glouton(this).appliquer();
	}

	/** @return Un Circuit calculé par l'algorithme de Krusal. */
	public Circuit kruskal()
	{
		return new MST(this).appliquer();
	}

	/** @return Le nombre de noeuds de ce graphe. */
	public int taille()
	{
		return this.points.size();
	}

}
