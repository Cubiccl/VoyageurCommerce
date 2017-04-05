package voyageur.model;

public class Arc implements Comparable<Arc>
{

	public final Point depart, arrivee;
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

	public boolean joins(Point point)
	{
		return point == this.depart || point == this.arrivee;
	}

}
