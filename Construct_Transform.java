import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Dumitrache Daniela Andreea Clasa folosita pentru a construi o
 *         transformare pe baza fisierului de intrare dat
 *
 */
public class Construct_Transform {
	/***
	 * reader pentru citirea din fisier
	 */
	private BufferedReader reader;
	/**
	 * writer pentru scrierea in fisier
	 */
	private BufferedWriter writer;

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	/**
	 * 
	 * @param in_file
	 *            fisierul de intrare
	 * @param out_file
	 *            fisierul de iesire
	 * @throws IOException
	 *             in cazul in care apar erori la scriere sau citire Metoda
	 *             realizeaza citirea din fisier a datelor de intrare ce
	 *             caracterizeaza graful si scrierea formulei rezultate din
	 *             aplicarea transformarii descrire in readme.
	 * 
	 */
	public void create_Transform(FileReader in_file, FileWriter out_file) throws IOException {
		reader = new BufferedReader(in_file);
		writer = new BufferedWriter(out_file);

		String str = reader.readLine();
		String[] tokens = str.split(" ");
		/**
		 * se citesc numarul de noduri si dimensiunea acoperirii
		 */
		int nV = Integer.parseInt(tokens[0]);
		int k = Integer.parseInt(tokens[2]);

		/**
		 * res va reprezenta forumula finala in s se va construi cate o clauza
		 * la fiecare calcul de indici noi cele 4 variabile index vor fi
		 * folosite pentru calculul indicilor variabilelor din clauze
		 */
		String s = "";
		Integer index_1 = 0;
		Integer index_2 = 0;
		Integer index_11 = 0;
		Integer index_22 = 0;
		String res = "";

		/**
		 * construim clauzele pentru prima parte a formulei nV*i va reprezenta
		 * versiunea variabilei, iar j numarul ei de ordine din cadrul
		 * versiunii. Pentru fiecare variabila dintr-o versiune vom face V intre
		 * negata acesteia si negatele tuturor variabilele care ii urmeaza in
		 * respectiva versiune.
		 */
		for (int i = 0; i < k; i++) {
			for (int j = 1; j <= nV - 1; j++) {
				index_1 = nV * i + j;
				for (int l = j + 1; l <= nV; l++) {
					index_2 = nV * i + l;
					s += "(~X";
					s += index_1.toString();
					s += "V~X";
					s += index_2.toString();
					s += ")";
					res += s;
					s = "";
					res += "^";
				}

			}
		}
		str = reader.readLine();

		/**
		 * citim linie cu linie din fisier
		 */
		while (str != null) {
			tokens = str.split(" ");
			/**
			 * cele doua noduri care alcatuiesc muchia Eticheta asociata unui va
			 * reprezenta si numarul de ordine al variabilelor corespunzatoare
			 * nodului respectiv din fiecare versiune
			 */
			index_1 = Integer.parseInt(tokens[0]);
			index_2 = Integer.parseInt(tokens[1]);
			s += "(";
			for (int i = 0; i < k; i++) {
				/**
				 * construim toate versiunile nodurilor ce altcatuiesc muchia
				 * Construim clauza specifica muchiei, facand V intre toate
				 * versiunile nodurilor din muchie
				 */

				index_11 = i * nV + index_1;
				index_22 = i * nV + index_2;
				s += "X";
				s += index_11.toString();
				s += "VX";
				s += index_22.toString();
				s += "V";
			}
			s = s.substring(0, s.length() - 1);
			s += ")";
			res += s;
			s = "";
			res += "^";
			str = reader.readLine();
		}

		/**
		 * eliminam ultimul ^ de la sfarsitul rezultatului si il punem in fisier
		 */
		res = res.substring(0, res.length() - 1);
		writer.write(res);
		writer.close();
		reader.close();

	}
}
