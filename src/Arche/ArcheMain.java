package Arche;

import java.util.ArrayList;

public class ArcheMain {

	public static void main(String[] args) {
		System.out.println("Ausgabe: ");
		String fileTierliste = "C:\\Temp\\datenstrukturen\\tierliste.txt";
		
		// Aufgabe 1: Tiere aus Datei auslesen
		ArrayList<String> animalList = FileHelper.readFile(fileTierliste);
		Arche archeNoah = new Arche();
		
		//Aufgabe 1 + 2: Tiere in Arche und Beiboot einsortieren
		for(int i = 0; i < animalList.size(); i++) {
			Animal archeAnimal = new Animal(animalList.get(i));
			archeNoah.addAnimal(archeAnimal);
		}
		// Finde Spezien mit nur einem Tier:
		archeNoah.separateAnimals();
		archeNoah.print();
		System.out.println("");
		
		// Aufgabe 3: Tiere auf der Arche nach Futtermenge sortieren
		archeNoah.sortAnimalsByFeedAmount();		
		archeNoah.print();
		
		// Aufgabe 4.1: Tiere in Baum setzen und zum Ausscheiden schicken
		archeNoah.sendAnimalsToPoo();
		archeNoah.print();
		
		// Aufgabe 4.2: Tiere ausscheiden lassen und (vorerst unsortiert) zurück in die Arche schicken
		// Tiere aus der Sterbeliste einlesen
		String fileSterbeliste = "C:\\Temp\\datenstrukturen\\sterbeliste.txt";
		ArrayList<String> deadList = FileHelper.readFile(fileSterbeliste);
		archeNoah.letAnimalsPoo(deadList);
		archeNoah.print();
		
		//Aufgabe 4.3: Arche aus dem beiboot auffüllen, wenn möglich, sonst Tiere von der Arche 
		//auf das Beiboot schicken
		archeNoah.getAnimalsFromTender();
		
		// Aufgabe 5: Tierarten in der Arche alphabetisch sortieren
		archeNoah.sortSpeciesByName();
		archeNoah.print();
		
		System.out.println("Ende!");
	}

}
