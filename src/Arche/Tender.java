package Arche;

import java.util.LinkedList;

public class Tender {
	private LinkedList<Animal> tenderAnimalList = new LinkedList<>();
	
	public void addAnimalToTender(Animal tenderAnimal) {
		tenderAnimalList.add(tenderAnimal);
	}
	
	public void removeAnimalFromTender (Animal tenderAnimal) {
		tenderAnimalList.remove(tenderAnimal);
	}
	
	public Animal takeAnimalFromTender(Animal animal) {
		
		for(int i = 0; i < tenderAnimalList.size(); i++) {
			if(animal.getAnimalSpecies().equals(tenderAnimalList.get(i).getAnimalSpecies())
					&& animal.getGender() != tenderAnimalList.get(i).getGender()) {
				Animal newAnimal = tenderAnimalList.get(i);
				tenderAnimalList.remove(newAnimal);
				return newAnimal;
			}
		}
		return null;
	}
	
	public void print() {
		for(int i = 0; i < tenderAnimalList.size(); i++) {
			tenderAnimalList.get(i).print();
		}
	}
}
