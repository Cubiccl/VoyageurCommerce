package voyageur.model;

public class Point
{

	/** Position de ce Point. */
	public final double x, y;

	public Point()
	{
		this(0, 0);
	}

	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/** @param autrePoint - Un autre point.
	 * @return La distance entre ce point et le point en entrée. */
	public double distance(Point autrePoint)
	{
		return Math.sqrt(Math.pow(autrePoint.x - this.x, 2) + Math.pow(autrePoint.y - this.y, 2));
	}

}
