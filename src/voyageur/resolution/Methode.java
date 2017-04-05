package voyageur.resolution;

import java.util.ArrayList;

import voyageur.model.Circuit;
import voyageur.model.Point;
import voyageur.model.TSP;

/** Classe parente à toutes les classes de résolution du TSP. */
public abstract class Methode
{

	/** Le Circuit créé. */
	protected Circuit circuit;
	/** Le graphe concerné. */
	public final TSP graphe;

	public Methode(TSP graphe)
	{
		this.graphe = graphe;
	}

	/** @param restants - Un ensemble de points.
	 * @return Un Point aléatoire parmi les points en entrée. */
	protected Point aleatoire(ArrayList<Point> restants)
	{
		return restants.get((int) (Math.random() * restants.size()));
	}

	/** Applique cette méthode. Toutes les méthodes suivent le même patterne: Choisir un point de départ, puis choisir des points en fonction du précédent jusqu'à ce que tous les points soient choisis.
	 * 
	 * @return Le Circuit créé. */
	public Circuit appliquer()
	{
		ArrayList<Point> restants = new ArrayList<Point>(); // Les points non sélectionnés.
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

	/** @param restants - Les Points qu'il reste à ajouter.
	 * @return Le Premier point pour le Circuit créé. */
	protected abstract Point choisirPremier(ArrayList<Point> restants);

	/** @param restants - Les Points qu'il reste à ajouter.
	 * @param precedent - Le dernier Point choisi.
	 * @return Le prochain Point à ajouter. */
	protected abstract Point choisirSuivant(ArrayList<Point> restants, Point precedent);

}
