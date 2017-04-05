package voyageur.model;

import java.util.*;

/** Représente un Arbre de recouvrement. */
public class Arbre
{

	/** Les Arcs de cet Arbre. */
	public List<Arc> arcs;
	/** Le Point parent. */
	public Point parent;

	public Arbre()
	{
		this.arcs = new ArrayList<Arc>();
	}

	public Arbre(Collection<Arc> arcs)
	{
		this.arcs = new ArrayList<Arc>();
		for (Arc a : arcs)
			this.ajouterArc(a);
	}

	/** Ajoute un Arc à cet Arbre. */
	public void ajouterArc(Arc a)
	{
		this.arcs.add(a);
	}

	/** @return Tous les points accessibles depuis le point en entrée. */
	public Set<Point> enfants(Point parent)
	{
		Set<Point> enfants = new HashSet<Point>();
		for (Arc a : this.arcs)
			if (a.depart == parent) enfants.add(a.arrivee);
		return enfants;
	}

	/** Trie les arcs de cet arbre en fonction de leur distance. */
	public void trier()
	{
		this.arcs.sort(Comparator.naturalOrder());
	}

}
