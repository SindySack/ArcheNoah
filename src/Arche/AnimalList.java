package Arche;

public class AnimalList {
	private ListElement start = null;
	private ListElement end = null;
	
	public boolean add(Animal animal) {
		// Schaue, ob die Spezies bereits existiert
		AnimalSpecies speciesElement = getSpecies(animal.getAnimalSpecies());
		if(speciesElement != null) {
			// falls die Spezies existiert, versuche das Tier hinzuzufügen
			return speciesElement.add(animal);
		}
		
		// Die Spezies existiert noch nicht, also lege eine neue an und füge das Tier direkt hinzu
		ListElement newElement = new ListElement(animal);
		
		if(start == null || end == null) {
			start = newElement;
			end = newElement;
		} else {		
			newElement.setPrevElement(end);		
			end.setNextElement(newElement);
			end = newElement;
		}
		
		return true;
	}
	
	//Species aufrufen
	public AnimalSpecies getSpecies(String speciesName) {
		if(start == null) {
			return null;
		}
		
		ListElement current = start;
		while(current != null) {
			String currentName = current.getSpecies().getSpeciesName();
			if(currentName.equals(speciesName) ||
			   currentName.equals("Pferd") && speciesName.equals("Einhorn") ||
			   currentName.equals("Einhorn") && speciesName.equals("Pferd")
			   ) {
				return current.getSpecies();
			}
			current = current.getNextElement();
		}
		
		return null;
	}
	
	//Liste nach Futtermenge der Spezien sortieren
	public void sortByFeedAmount() {
		boolean isSorted = false;
		
		while(!isSorted) {
			isSorted = true;
			ListElement current = start;
			
			while(current != null && current.getNextElement() != null) {
				AnimalSpecies species1 = current.getSpecies();
				AnimalSpecies species2 = current.getNextElement().getSpecies();
				
				if(species1.getFeedAmount() > species2.getFeedAmount()) {
					ListElement next = current.getNextElement();					
					
					if(current.getPrevElement() != null) {
						current.getPrevElement().setNextElement(next);
					}
					
					ListElement help = next.getNextElement();
					
					next.setNextElement(current);
					current.setNextElement(help);					
					
					if(current.getNextElement() != null) {
						current.getNextElement().setPrevElement(current);
					}
					
					help = current.getPrevElement();
					
					current.setPrevElement(next);					
					next.setPrevElement(help);					
					
					isSorted = false;
					current = next;
					resetStartEnd();
				}
				current = current.getNextElement();
			}
		}
	}
	
	//Liste nach Namen der Spezien sortieren
	public void sortByName() {
		boolean isSorted = false;
		
		while(!isSorted) {
			isSorted = true;
			ListElement current = start;
			
			while(current != null && current.getNextElement() != null) {
				AnimalSpecies species1 = current.getSpecies();
				AnimalSpecies species2 = current.getNextElement().getSpecies();
				
				if(species1.getSpeciesName().compareTo(species2.getSpeciesName()) > 0 ) {
					ListElement next = current.getNextElement();					
					
					if(current.getPrevElement() != null) {
						current.getPrevElement().setNextElement(next);
					}
					
					ListElement help = next.getNextElement();
					
					next.setNextElement(current);
					current.setNextElement(help);					
					
					if(current.getNextElement() != null) {
						current.getNextElement().setPrevElement(current);
					}
					
					help = current.getPrevElement();
					
					current.setPrevElement(next);					
					next.setPrevElement(help);					
					
					isSorted = false;
					current = next;
					resetStartEnd();
				}
				current = current.getNextElement();
			}
		}
	}
	
	//Ausgabefunktion
	public void printList() {
		if(start == null) {
			System.out.println("Liste ist leer");
			return;
		}
		
		ListElement current = start;
		
		do {
			current.getSpecies().print();

			current = current.getNextElement();
		} while(current != null);
	}
	
	public ListElement getStart() {
		return start;
	}
	
	//Tier aus Liste löschen
	public void removeAnimal(Animal animal) {
		ListElement current = start;
		
		while(current != null) {
			if(current.getSpecies().exists(animal)) {
				// Lösche Tier aus der Spezies
				current.getSpecies().remove(animal);
				// Lösche das ListenElement nur, wenn die Spezies keine Tiere beinhaltet
				if(current.getSpecies().isEmpty()) {
					removeElement(current);
				}
			}
			
			current = current.getNextElement();
		}
	}
	
	//Element aus Liste löschen
	private void removeElement(ListElement element) {
		if(start == end) {
			start = null;
			end = null;
		} else if(element.getNextElement() == null) {
			element.getPrevElement().setNextElement(null);
			end = element.getPrevElement();
		} else if(element.getPrevElement() == null) {
			element.getNextElement().setPrevElement(null);
			start = element.getNextElement();
		} else {				
			element.getNextElement().setPrevElement(element.getPrevElement());
			element.getPrevElement().setNextElement(element.getNextElement());
		}
	}
	
	//Spezies aus Liste löschen
	public void removeSpecies(AnimalSpecies species) {
		ListElement current = start;
		
		while(current != null) {
			if(current.getSpecies() == species) {
				removeElement(current);
			}
			
			current = current.getNextElement();
		}
	}
	
	//start und end neu setzen
	private void resetStartEnd() {
		while(start.getPrevElement() != null) {
			start = start.getPrevElement();
		}
		
		while(end.getNextElement() != null) {
			end = end.getNextElement();
		}
	}
}
