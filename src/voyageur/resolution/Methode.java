package voyageur.resolution;

import java.util.ArrayList;

import voyageur.model.Circuit;
import voyageur.model.Point;
import voyageur.model.TSP;

/** Classe parente � toutes les classes de r�solution du TSP. */
public abstract class Methode
{

	/** Le Circuit cr��. */
	protected Circuit circuit;
	/** Le graphe concern�. */
	public final TSP graphe;

	public Methode(TSP graphe)
	{
		this.graphe = graphe;
	}

	/** @param restants - Un ensemble de points.
	 * @return Un Point al�atoire parmi les points en entr�e. */
	protected Point aleatoire(ArrayList<Point> restants)
	{
		return restants.get((int) (Math.random() * restants.size()));
	}

	/** Applique cette m�thode. Toutes les m�thodes suivent le m�me patterne: Choisir un point de d�part, puis choisir des points en fonction du pr�c�dent jusqu'� ce que tous les points soient choisis.
	 * 
	 * @return Le Circuit cr��. */
	public Circuit appliquer()
	{
		ArrayList<Point> restants = new ArrayList<Point>(); // Les points non s�lectionn�s.
		restants.addAll(this.graphe.getPoints());
		this.circuit = new Circuit();

		Point choisi = this.choisirPremier(restants);

		while (!restants.isEmpty()) // Tant qu'il reste des points
		{
			choisi = this.choisirSuivant(restants, choisi);
			restants.remove(choisi); // On choisi un nouveau point. On le retire des restants et on l'ajoute au Circuit.
			this.circuit.ajouterPoint(choisi);
		}

		return this.circuit;
	}

	/** @param restants - Les Points qu'il reste � ajouter.
	 * @return Le Premier point pour le Circuit cr��. */
	protected abstract Point choisirPremier(ArrayList<Point> restants);

	/** @param restants - Les Points qu'il reste � ajouter.
	 * @param precedent - Le dernier Point choisi.
	 * @return Le prochain Point � ajouter. */
	protected abstract Point choisirSuivant(ArrayList<Point> restants, Point precedent);

}
