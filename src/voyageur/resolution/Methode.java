package voyageur.resolution;

import java.util.ArrayList;

import voyageur.model.Circuit;
import voyageur.model.Point;
import voyageur.model.TSP;

public abstract class Methode
{

	protected Circuit circuit;
	public final TSP graphe;

	public Methode(TSP graphe)
	{
		this.graphe = graphe;
	}

	protected Point aleatoire(ArrayList<Point> restants)
	{
		return restants.get((int) (Math.random() * restants.size()));
	}

	public Circuit appliquer()
	{
		ArrayList<Point> restants = new ArrayList<Point>();
		restants.addAll(this.graphe.getPoints());
		this.circuit = new Circuit();

		Point choisi = this.choisirPremier(restants);

		while (!restants.isEmpty())
		{
			choisi = this.choisirSuivant(restants, choisi);
			restants.remove(choisi);
			this.circuit.ajouterPoint(choisi);
		}

		return this.circuit;
	}

	protected abstract Point choisirPremier(ArrayList<Point> restants);

	protected abstract Point choisirSuivant(ArrayList<Point> restants, Point precedent);

}
