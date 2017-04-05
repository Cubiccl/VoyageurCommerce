package voyageur.model;

import java.util.*;

public class Arbre
{

	public List<Arc> arcs;
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

	public void ajouterArc(Arc a)
	{
		this.arcs.add(a);
	}

	public Set<Point> enfants(Point parent)
	{
		Set<Point> enfants = new HashSet<Point>();
		for (Arc a : this.arcs)
			if (a.depart == parent) enfants.add(a.arrivee);
		return enfants;
	}

	public void trier()
	{
		this.arcs.sort(Comparator.naturalOrder());
	}

}
