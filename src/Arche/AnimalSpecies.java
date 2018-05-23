package Arche;

public class AnimalSpecies {
	private Animal male = null;
	private Animal female = null;
	
	public AnimalSpecies(Animal animal) {
		add(animal);
	}
	
	//Falls ein Männchen oder Weibchen existiert, geb es aus
	public boolean exists(Animal animal) {
		return male == animal || female == animal;
	}
	
	//Überprüfung ob ein Tier keinen Partner hat
	public Animal getLonely() {
		if (male != null && female == null) {
			return male;
		}
		if (male == null && female != null) {
			return female;
		}
		return null;
	}
	
	//Tier aus der Spezies löschen
	public void remove(Animal animal) {
		if(male == animal) {
			male = null;
		}
		if(female == animal) {
			female = null;
		}
	}
	
	//Tiere in der Spezies austauschen
	public void changeAnimal(AnimalSpecies newAnimals) {
		this.male = newAnimals.male;
		this.female = newAnimals.female;
	}
	
	//Tier zur Spezies hinzufügen
	public boolean add(Animal animal) {
		// Prüfe, ob Weibchen existiert, wenn nicht füge es hinzu
		if(animal.getGender() == 'w' && female == null) {
			female = animal;
			return true;
		}
		
		// Prüfe ob Männchen existiert, wenn nicht füge es hinzu		
		if(animal.getGender() == 'm' && male == null) {
			male = animal;
			return true;
		}
		
		// Eine Tierart mit dem Geschlecht existiert bereits		
		return false;
	}
	
	//Ausgabefunktion
	public void print() {
		if(isEmpty()) {
			System.out.println("No Animals found.");
		}
		
		String maleName = male == null ? "-" : male.getName();
		String femaleName = female == null ? "-" : female.getName();
		
		System.out.println(getSpeciesName() + " [m: " + maleName + ", f: " + femaleName + "] " + 
						"food: " + getFeedAmount() + ", poo: " + getPooAmount());
	}
	
	public Animal getMale() {
		return male;
	}
	
	public Animal getFemale() {
		return female;
	}
	
	//schauen, ob die Spezies leer ist
	public boolean isEmpty() {
		return male == null && female == null;
	}
	
	public String getSpeciesName() {
		if(isEmpty()) {
			return null;
		}
		
		return male == null ? female.getAnimalSpecies() : male.getAnimalSpecies();
	}
	
	public int getFeedAmount() {
		if(isEmpty()) {
			return -1;
		}
		
		return male == null ? female.getFeedAmount() : male.getFeedAmount();
	}
	
	public int getPooAmount() {
		if(isEmpty()) {
			return -1;
		}
		
		return male == null ? female.getPooAmount() : male.getPooAmount();
	}
}
