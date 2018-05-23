package Arche;

public class ListElement {
	private AnimalSpecies species;
	private ListElement nextElement = null, prevElement = null;
	
	public ListElement(Animal archeAnimal) {
		species = new AnimalSpecies(archeAnimal);
	}
	
	public void setNextElement(ListElement nextElement) {
		this.nextElement = nextElement;
	}
	
	public void setPrevElement(ListElement prevElement) {
		this.prevElement = prevElement;
	}
	
	public ListElement getNextElement() {
		return nextElement;
	}
	
	public ListElement getPrevElement() {
		return prevElement;
	}
	
	public AnimalSpecies getSpecies() {
		return species;
	}
}
