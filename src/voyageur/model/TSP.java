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
 * Repr�sente un ensemble de points � utiliser pour le TSP. */
public class TSP
{

	/** Tous les sommets du graphe. */
	private Set<Point> points;

	/** Cr�e un graphe vide. */
	public TSP()
	{
		this.points = new HashSet<Point>();
	}

	/** Cr�e un graphe � points d�finis.
	 * 
	 * @param points - Les points du graphe. */
	public TSP(Collection<Point> points)
	{
		this.points = new HashSet<Point>();
		this.points.addAll(points);
	}

	/** Cr�e un graphe � points al�atoires.
	 * 
	 * @param nbPoints - Le nombre de points � cr�er. */
	public TSP(int nbPoints)
	{
		this(Utils.creerPoints(nbPoints));
	}

	/** @return Un Circuit al�atoire pour ce graphe. */
	public Circuit aleatoire()
	{
		return new Aleatoire(this).appliquer();
	}

	/** @return Un Arbre complet correspondant � ce graphe. */
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

	/** @return Un Circuit calcul� par l'algorithme Glouton. */
	public Circuit glouton()
	{
		return new Glouton(this).appliquer();
	}

	/** @return Un Circuit calcul� par l'algorithme de Krusal. */
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
