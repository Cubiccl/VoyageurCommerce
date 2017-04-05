package voyageur.model;

/** Repr�sente un Arc entre deux points. */
public class Arc implements Comparable<Arc>
{

	/** Les points li�s. */
	public final Point depart, arrivee;
	/** La distance entre ces deux points. */
	public final double distance;

	public Arc(Point depart, Point arrivee)
	{
		this.depart = depart;
		this.arrivee = arrivee;
		this.distance = this.depart.distance(this.arrivee);
	}

	@Override
	public int compareTo(Arc o)
	{
		return new Double(this.distance).compareTo(new Double(o.distance));
	}

	/** @param point - Le point concern�.
	 * @return True si le point en entr�e et soit le d�part, soit l'arriv�e de cet Arc. */
	public boolean joins(Point point)
	{
		return point == this.depart || point == this.arrivee;
	}

}
