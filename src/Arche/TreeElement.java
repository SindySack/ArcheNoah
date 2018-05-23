package Arche;

public class TreeElement {
	private TreeElement right = null, left = null;
	private AnimalSpecies species;
	private int height = 1;
	
	public TreeElement(AnimalSpecies species) {
		this.species = species;
	}
	
	public AnimalSpecies getSpecies() {
		return species;
	}
	
	//Höhe des Baumes bestimmen
	public static int getHeight(TreeElement treeElement) {
		if(treeElement == null) {
			return 0;
		}
		return treeElement.height;
	}
	
	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}
	
	//Balancefactor berechnen
	public static int getBalance(TreeElement treeElement) {
		if(treeElement == null) {
			return 0;
		}
		
		return getHeight(treeElement.left) - getHeight(treeElement.right);
	}
	
	//Rechtsrotation im Baum
	public static TreeElement rightRotate(TreeElement treeElement) {
		TreeElement help1 = treeElement.left;
		TreeElement help2 = help1.right;
		
		help1.right = treeElement;
		treeElement.left = help2;
		
		treeElement.height = max(getHeight(treeElement.left), getHeight(treeElement.right)) + 1;
		help1.height = max(getHeight(help1.left), getHeight(help1.right)) + 1;
		
		return help1;		
	}
	
	//Linksrotation im Baum
	public static TreeElement leftRotate(TreeElement treeElement) {
		TreeElement help1 = treeElement.right;
		TreeElement help2 = help1.left;
		
		help1.left = treeElement;
		treeElement.right = help2;
		
		treeElement.height = max(getHeight(treeElement.left), getHeight(treeElement.right)) +1;
		help1.height = max(getHeight(help1.left), getHeight(help1.right)) +1;
		
		return help1;
	}
	
	//Species mit der geringsten Ausscheidung heraussuchen
	public static AnimalSpecies getSpeciesWithMinPooAmount(TreeElement treeElement) {
		//nur wenn Baum leer ist
		if(treeElement == null) {
			return null;
		}
		
		if(treeElement.left == null) {
			return treeElement.getSpecies();
		}
		return getSpeciesWithMinPooAmount(treeElement.left);
	}
	
	//Species mit höchster Ausscheidung herraussuchen
	public static AnimalSpecies getSpeciesWithMaxPooAmount(TreeElement treeElement) {
		//nur wenn Baum leer ist
		if(treeElement == null) {
			return null;
		}
		
		if(treeElement.right == null) {
			return treeElement.getSpecies();
		}
		return getSpeciesWithMaxPooAmount(treeElement.right);
	}
	
	//Element zum Baum hinzufügen
	public static TreeElement addElement(TreeElement element, AnimalSpecies species) {
		if(element == null) {
			return new TreeElement(species);
		}
		
		if(species.getPooAmount() < element.getSpecies().getPooAmount()) {
			element.left = addElement(element.left, species);
		} else {
			element.right = addElement(element.right, species);
		}
		
		element.height = 1 + max(getHeight(element.left), getHeight(element.right));
		
		int balance = getBalance(element);
		
		if(balance > 1 && species.getPooAmount() < element.left.getSpecies().getPooAmount()) {
			return rightRotate(element);
		}
		
		if(balance < -1 && species.getPooAmount() >= element.right.getSpecies().getPooAmount()) {
			return leftRotate(element);
		}
		
		if(balance > 1 && species.getPooAmount() >= element.left.getSpecies().getPooAmount()) {
			element.left = leftRotate(element.left);
			return rightRotate(element);
		}
		
		if(balance < -1 && species.getPooAmount() < element.right.getSpecies().getPooAmount()) {
			element.right = rightRotate(element.right);
			return leftRotate(element);
		}
		
		return element;		
	}
	
	//Element mit der geringsten Ausscheidung aus dem Baum heraussuchen
	private static TreeElement minPooElement(TreeElement element) {
		TreeElement current = element;
		while(current.left != null) {
			current = current.left;
		}
		
		return current;
	}
	
	//Tier aus dem Baum löschen
	public static TreeElement removeAnimal(TreeElement element, Animal animal) {
		if(element == null) {
			return null;
		}
		
		if(animal.getPooAmount() < element.getSpecies().getPooAmount()) {
			element.left = removeAnimal(element.left, animal);
		} else if(animal.getPooAmount() > element.getSpecies().getPooAmount()) {
			element.right = removeAnimal(element.right, animal);
		} else {
			// Menge an Ausscheidung ist identisch, daher muss hier das Tier gelöscht werden
			element.getSpecies().remove(animal);
			
			
			// Lösche nur, wenn die Spezies leer ist
			if(element.getSpecies().isEmpty()) {
				if (element.left == null || element.right == null) {
					// Element mit nur einem, oder keinem Kind
					TreeElement temp = null;
					if(temp == element.left) {
						temp = element.right;
					} else {
						temp = element.left;
					}
					
					if(temp == null) {
						// Kein Kind
						temp = element;
						element = null;
					} else {
						// Kopiere das eine Kind an die ehemalige Stelle
						element = temp;
					}					
				} else {
					// Element mit zwei Kindern
					// Hole das kleinste Element aus dem rechten Baum
					TreeElement temp = minPooElement(element.right);
					
					// Verschiebe die Tiere aus diesem in das aktuelle Element
					element.getSpecies().changeAnimal(temp.getSpecies());
					
					// Lösche das kleinste Element
					if(temp.getSpecies().getLonely() != null) {
						// Damit die Rekursion funktioniert, darf nur ein Tier in der Spezies enthalten sein
						temp.getSpecies().remove(temp.getSpecies().getMale());
					}
					element.right = removeAnimal(element.right, temp.getSpecies().getLonely());
				}
			}
			
			// Wenn nur ein Element enthalten war, sind wir hier fertig
			if(element == null) {
				return null;
			}
			
			// Aktualisiere die Höhe des aktuellen Elements
			element.height = max(getHeight(element.left), getHeight(element.right)) + 1;
			
			int balance = getBalance(element);
			
	        // Left Left Case
	        if (balance > 1 && getBalance(element.left) >= 0) {
	            return rightRotate(element);
	        }
	 
	        // Left Right Case
	        if (balance > 1 && getBalance(element.left) < 0) {
	        	element.left = leftRotate(element.left);
	            return rightRotate(element);
	        }
	 
	        // Right Right Case
	        if (balance < -1 && getBalance(element.right) <= 0) {
	            return leftRotate(element);
	        }
	 
	        // Right Left Case
	        if (balance < -1 && getBalance(element.right) > 0) {
	        	element.right = rightRotate(element.right);
	            return leftRotate(element);
	        }
	 
	        return element;
		}
		
		// nur temporär:
		return element;
	}
	
	public static void printPreOrder(TreeElement treeElement) {
        if (treeElement != null) {
            treeElement.getSpecies().print();
            printPreOrder(treeElement.left);
            printPreOrder(treeElement.right);
        } else {
        	System.out.println("-");
        }
    }
}
