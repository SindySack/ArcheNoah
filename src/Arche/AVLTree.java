package Arche;

public class AVLTree {
	private TreeElement root = null;
	
	public void addSpecies(AnimalSpecies species) {
		root = TreeElement.addElement(root, species);
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void print() {
		TreeElement.printPreOrder(root);
		System.out.println("");
	}
	
	//Das Tier mit der größten Ausscheidung aus dem Baum löschen
	public Animal removeAnimalWithMaxPooAmount() {
		Animal result;
		AnimalSpecies species = TreeElement.getSpeciesWithMaxPooAmount(root);
		result = species.getMale();
		if(result != null)
		{
			root = TreeElement.removeAnimal(root, result);
			return result;
		}
		result = species.getFemale();
		if(result != null)
		{
			root = TreeElement.removeAnimal(root,  result);
		}
		return result;
	}
	
	//Das Tier mit der geringsten Ausscheidung aus dem Baum löschen
	public Animal removeAnimalWithMinPooAmount() {
		Animal result;
		AnimalSpecies species = TreeElement.getSpeciesWithMinPooAmount(root);
		result = species.getMale();
		if(result != null)
		{
			root = TreeElement.removeAnimal(root, result);
			return result;
		}
		result = species.getFemale();
		if(result != null)
		{
			root = TreeElement.removeAnimal(root,  result);
		}
		return result;
	}

}
