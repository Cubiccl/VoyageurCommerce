package voyageur;

import java.util.ArrayList;

import voyageur.model.Circuit;
import voyageur.model.TSP;

public class Main
{

	public static Window window;

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

	public static void main(String[] args)
	{
		window = new Window();
		window.setVisible(true);
	}

	public static void previousMain(int graphes, int sommets, boolean r, boolean g, boolean k, boolean m)
	{
		window.addText("Graphes: " + graphes + ", Sommets: " + sommets);

		ArrayList<Double> aleatoire = new ArrayList<Double>(), glouton = new ArrayList<Double>(), kruskal = new ArrayList<Double>(), m2opt = new ArrayList<Double>();
		for (int i = 0; i < graphes; ++i)
		{
			if (graphes < 10) System.out.println("Calcul graphe " + i + "...");
			TSP graphe = new TSP(sommets);
			if (r) aleatoire.add(graphe.aleatoire().longueur());
			if (g) glouton.add(graphe.glouton().longueur());
			if (k)
			{
				Circuit c = graphe.kruskal();
				kruskal.add(c.longueur());
				if (m)
				{
					c = apply2Opt(c);
					m2opt.add(c.longueur());
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
			window.addText("Distance moyenne pour aléatoire: " + moy);
		}

		if (g)
		{
			moy = 0;
			for (Double d : glouton)
				moy += d;
			moy /= glouton.size();
			window.addText("Distance moyenne pour Glouton: " + moy);
		}

		if (k)
		{
			moy = 0;
			for (Double d : kruskal)
				moy += d;
			moy /= kruskal.size();
			window.addText("Distance moyenne pour Kruskal: " + moy);

			if (m)
			{
				moy = 0;
				for (Double d : m2opt)
					moy += d;
				moy /= m2opt.size();
				window.addText("Distance moyenne pour Kruskal après 2-opt: " + moy);
			}
		}
	}
}
