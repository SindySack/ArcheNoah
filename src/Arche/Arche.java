package Arche;

import java.util.ArrayList;

public class Arche {
	private AnimalList animals = new AnimalList();
	private Tender tender = new Tender();
	private AVLTree pooTree = new AVLTree();
	
	public void addAnimal(Animal animal) {
		if(!animals.add(animal)) {
			tender.addAnimalToTender(animal);
		}
	}
	
	//Tiere ins Beiboot setzen, die nicht auf die Arche sollen
	public void separateAnimals() {
		ListElement current = animals.getStart();
		
		while(current != null) {
			// Get a lonely animal for a species
			Animal lonelyAnimal = current.getSpecies().getLonely();
			
			if(lonelyAnimal != null) {
				// Remove the lonely animal from the arche and move it to the tender
				animals.removeAnimal(lonelyAnimal);
				tender.addAnimalToTender(lonelyAnimal);
			}
			
			current = current.getNextElement();
		}
	}
	
	//Tiere aufs Deck schicken
	public void sendAnimalsToPoo() {
		ListElement currentElement = animals.getStart();
		while(currentElement != null)
		{
			AnimalSpecies currentSpecies = currentElement.getSpecies();
			pooTree.addSpecies(currentSpecies);
			animals.removeSpecies(currentSpecies);
			
			currentElement = animals.getStart();
		}
	}
	
	//Tiere aus dem Baum sortieren
	public void letAnimalsPoo(ArrayList<String> deadList) {
		// Nimm die Tiere aus dem Baum
		while(!pooTree.isEmpty()) {		
			Animal animalWithMaxPoo = pooTree.removeAnimalWithMaxPooAmount();
			Animal animalWithMinPoo = pooTree.removeAnimalWithMinPooAmount();
			
			// Lösche die Tiere, die bei Ausscheiden über Bord gegangen sind
			if(!animalWithMaxPoo.isAnimalGoingOverBoard(animalWithMaxPoo, deadList)) {
				// Verschiebe die Tiere (unsortiert) in die Arche, wenn sie nicht auf der Sterbeliste stehen
				animals.add(animalWithMaxPoo);
			}
			
			if(!animalWithMinPoo.isAnimalGoingOverBoard(animalWithMinPoo, deadList)) {
				animals.add(animalWithMinPoo);
			}
		}
	}
	
	public void sortAnimalsByFeedAmount() {
		animals.sortByFeedAmount();
	}
	
	public void sortSpeciesByName() {
		animals.sortByName();
	}
	
	public void getAnimalsFromTender() {
		ListElement current = animals.getStart();
		
		while(current != null) {
			// Get a lonely animal for a species
			Animal lonelyAnimal = current.getSpecies().getLonely();
			
			if(lonelyAnimal != null) {
				Animal tenderAnimal = tender.takeAnimalFromTender(lonelyAnimal);
				if(tenderAnimal == null) {
					// Remove the lonely animal from the arche and move it to the tender					
					animals.removeAnimal(lonelyAnimal);
					tender.addAnimalToTender(lonelyAnimal);
				} else {					
					animals.add(tenderAnimal);
				}
			}
			
			current = current.getNextElement();
		}
	}
	
	public void print() {
		System.out.println("--Tiere an Bord der Arche--");
		animals.printList();
		
		System.out.println("--Tiere auf dem Beiboot--");
		tender.print();
		
		System.out.println("--Tiere im Baum vor dem Ausscheiden--");
		pooTree.print();
	}
}
