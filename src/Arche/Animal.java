package Arche;

import java.util.ArrayList;

public class Animal {
	private String animalSpecies;
	private String name;
	private char gender;
	private int feedAmount;
	private int pooAmount;
	
	//Konstruktor normal
	public Animal(String animalSpecies, String name, char gender, int feedAmount, int pooAmount) {
		this.animalSpecies = animalSpecies;
		this.name = name;
		this.gender = gender;
		this.feedAmount = feedAmount;
		this.pooAmount = pooAmount;
	}
	
	//Konstruktor von Datei eingelesen
	public Animal(String fileLine) {
		String[] animalParts = fileLine.split(";");
		
		animalSpecies = animalParts[0];
		name = animalParts[1];
		gender = animalParts[2].charAt(0);
		feedAmount = Integer.parseInt(animalParts[3]);
		pooAmount = Integer.parseInt(animalParts[4]);

	}

	public String getName() {
		return name;
	}
	
	public String getAnimalSpecies() {
		return animalSpecies;
	}
	
	public char getGender() {
		return gender;
	}
	
	public int getFeedAmount() {
		return feedAmount;
	}
	
	public int getPooAmount() {
		return pooAmount;
	}
	
	public void print() {
		System.out.println(animalSpecies + " " + name
				+ " " + gender + " " + feedAmount
				+ " " + pooAmount);	
	}
	
	public void printShort() {
		System.out.print(animalSpecies.charAt(0) + "-" + name + "(" + feedAmount + ") ");	
	}
	
	public void printShortPoo() {
		System.out.print(animalSpecies.charAt(0) + "-" + name + "(" + pooAmount + ") ");	
	}
	
	//Spezien vergleichen und Einhorn zu einem Pferd machen	
	public boolean compareSpecies(Animal animalToCompare) {
		if(this.animalSpecies.equals(animalToCompare.getAnimalSpecies())) {
			return true;
		}

		if((this.animalSpecies.equals("Pferd") && animalToCompare.getAnimalSpecies().equals("Einhorn")) ||
			(this.animalSpecies.equals("Einhorn") && animalToCompare.getAnimalSpecies().equals("Pferd"))
			) {
			return true;
		}
		
		return false;
	}
	
	//Überprüfe, ob das Tier beim Ausscheiden über Board gegangen ist
	public boolean isAnimalGoingOverBoard(Animal animal, ArrayList<String> deadList) {
			
		for(int i = 0; i < deadList.size(); i++) {
			if(this.name.equals(deadList.get(i))) {
				return true;
			}
		}
		return false;
	}
}
