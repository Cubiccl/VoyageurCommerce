package voyageur;

import java.util.ArrayList;

import voyageur.model.Circuit;
import voyageur.model.TSP;

public class Main
{

	public static Window window;

	/** Applique la m�thode 2-opt pour le Circuit en entr�e.
	 * 
	 * @param c - Le circuit � optimiser.
	 * @return Un nouvel Objet Circuit */
	public static Circuit apply2Opt(Circuit c)
	{
		boolean improved = true;
		while (improved)
		{
			improved = false;
			for (int i = 0; i < c.taille(); i++)
				for (int j = 0; j < c.taille(); j++)
				{
					Circuit c2 = c.applique2Opt(i, j);
					if (c2.longueur() < c.longueur())
					{
						improved = true;
						c = c2;
					}
				}
		}
		return c;
	}

	/** Applique diff�rentes M�thodes de r�solution du probl�me.
	 * 
	 * @param graphes - Le nombre de graphes � tester.
	 * @param sommets - Le nombre de sommets par graphe.
	 * @param r - Si vrai, la m�thode Random est appliqu�e.
	 * @param g - Si vrai, la m�thode Glouton est appliqu�e.
	 * @param k - Si vrai, la m�thode de Kruskal est appliqu�e.
	 * @param m - Si vrai, la m�thode de Kruskal sera optimis�e avec 2-opt.<br/>
	 *            Toutes ces m�thodes peuvent �tre appliqu�es ou non ind�pendemment. */
	public static void calcul(int graphes, int sommets, boolean r, boolean g, boolean k, boolean m)
	{
		window.addText("Graphes: " + graphes + ", Sommets: " + sommets);

		ArrayList<Double> aleatoire = new ArrayList<Double>(), rTimes = new ArrayList<Double>();
		ArrayList<Double> glouton = new ArrayList<Double>(), gTimes = new ArrayList<Double>();
		ArrayList<Double> kruskal = new ArrayList<Double>(), kTimes = new ArrayList<Double>();
		ArrayList<Double> m2opt = new ArrayList<Double>(), mTimes = new ArrayList<Double>();
		double t = 0;

		for (int i = 0; i < graphes; ++i)
		{
			if (graphes < 10) System.out.println("Calcul graphe " + i + "...");
			TSP graphe = new TSP(sommets);
			if (r)
			{
				t = System.currentTimeMillis();
				aleatoire.add(graphe.aleatoire().longueur());
				t = System.currentTimeMillis() - t;
				rTimes.add(t);
			}
			if (g)
			{
				t = System.currentTimeMillis();
				glouton.add(graphe.glouton().longueur());
				t = System.currentTimeMillis() - t;
				gTimes.add(t);
			}
			if (k)
			{
				t = System.currentTimeMillis();
				Circuit c = graphe.kruskal();
				kruskal.add(c.longueur());
				t = System.currentTimeMillis() - t;
				kTimes.add(t);
				if (m)
				{
					t = System.currentTimeMillis();
					c = apply2Opt(c);
					m2opt.add(c.longueur());
					t = System.currentTimeMillis() - t;
					mTimes.add(t);
				}
			}
		}

		double moy;
		if (r)
		{
			moy = 0;
			for (Double d : aleatoire)
				moy += d;
			moy /= aleatoire.size();
			window.addText("Distance moyenne pour al�atoire: " + moy);
			moy = 0;
			for (Double d : rTimes)
				moy += d;
			moy /= rTimes.size();
			window.addText("Temps moyen pour al�atoire: " + moy);
		}

		if (g)
		{
			moy = 0;
			for (Double d : glouton)
				moy += d;
			moy /= glouton.size();
			window.addText("Distance moyenne pour Glouton: " + moy);
			for (Double d : gTimes)
				moy += d;
			moy /= gTimes.size();
			window.addText("Temps moyen pour Glouton: " + moy);
		}

		if (k)
		{
			moy = 0;
			for (Double d : kruskal)
				moy += d;
			moy /= kruskal.size();
			window.addText("Distance moyenne pour Kruskal: " + moy);
			for (Double d : kTimes)
				moy += d;
			moy /= kTimes.size();
			window.addText("Temps moyen pour Kruskal: " + moy);

			if (m)
			{
				moy = 0;
				for (Double d : m2opt)
					moy += d;
				moy /= m2opt.size();
				window.addText("Distance moyenne pour 2-opt: " + moy);
				for (Double d : mTimes)
					moy += d;
				moy /= mTimes.size();
				window.addText("Temps moyen pour 2-opt: " + moy);
			}
		}
	}

	public static void main(String[] args)
	{
		window = new Window();
		window.setVisible(true);
	}
}
