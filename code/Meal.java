
public class Meal {

 private String[] ingredientList= new String[20];
 private String[] amountList = new String[20];
 private String instructionsList;
 
 private String name;
 
public Meal(){
}

public boolean isIngredientInMeal(String ingredient){
 
 for(int i=0; i<ingredientList.length; i++){
  
  if(ingredient!=null){
    
    if(ingredient.equals(ingredientList[i])){
   return true;
    }
  }
  
 }
 return false;
}

public void addIngredient(String ingredient){
 
 boolean done = false;
 
 for(int i=0; i<ingredientList.length; i++){
  if(done==false){
  if(ingredientList[i]==null){
   done=true;
   ingredientList[i]=ingredient;
   
  }
  }
 }
}

public void addAmount(String amount){
 
 boolean done = false;
 
 for(int i=0; i<amountList.length; i++){
  if(done==false){
  if(amountList[i]==null){
   done=true;
   amountList[i]=amount;
  }
  }
 }
}

public void setInstruction(String instruction){
 
 instructionsList = instruction;
 
 /*
 for(int i=0; i<instructionsList.length; i++){
  if(instructionsList[i]==null){
   instructionsList[i]=instruction;
   break;
  }
 }
 */
}

public int numberOfIngredientsMissing(String[] ingredients){
 int a = calculateNumberOfIngredients()-ingredientsInMealAvailable(ingredients);
 return calculateNumberOfIngredients()-ingredientsInMealAvailable(ingredients);
}

public double percentageOfIngredients(String[] ingredients){
 
 return 1-(((double)calculateNumberOfIngredients()-(double)ingredientsInMealAvailable(ingredients))/(double)calculateNumberOfIngredients());
}

public int calculateNumberOfIngredients(){
 
 
 int numberOfIngredients=0;
 
 for(int i=0; i<ingredientList.length; i++){
  if(ingredientList[i]!=null){
   numberOfIngredients++;
  }
 }
 return numberOfIngredients;
}

public int ingredientsInMealAvailable(String[] ingredients){
 int ingredientsInMealAvailable=0;
 
 for(int i=0; i<ingredients.length; i++){
  
  if(isIngredientInMeal(ingredients[i])){
   ingredientsInMealAvailable++;
  }
 }
 return ingredientsInMealAvailable;
}

public String[] listMissingIngredients(String[] ingredients){
 
 String[] missingIngredients = new String[20];
 
 int j=0;
 
 for(int i=0; i<calculateNumberOfIngredients(); i++){
  if(!isIngredientAvailable(ingredientList[i], ingredients)){
   missingIngredients[j]=ingredientList[i];
   j++;
  }
 }
 
 return missingIngredients;
}

public boolean isIngredientAvailable(String ingredientInMeal, String[] ingredients){
 for(int i=0; i<ingredients.length; i++){
  if(ingredientInMeal.equals(ingredients[i])){
   return true;
  }
 }
 return false;
}

public void deleteIngredients(){
 for(int i=0; i<ingredientList.length; i++){
  ingredientList[i]=null;
 }
}

public void deleteAmounts(){
 for(int i=0; i<ingredientList.length; i++){
  ingredientList[i]=null;
 }
}

public String getIngredient(int n){
 
 return ingredientList[n];
}

public String[] getIngredients(){
 return ingredientList;
}

public String getInstructions(){
   return instructionsList;
}


public String[] getAmounts(){
 return amountList;
}


public String getAmount(int n){
 
 return amountList[n];
}


public void setName(String s){
 name=s;
}

public String getName(){
 return name;
}

public boolean allIngredientsInMeal(String[] kitchenItems){
 int i=0;
 
 while(kitchenItems[i]!=null){
  if(!isIngredientInMeal(kitchenItems[i])){
   return false;
  }
  i++;
 }
 
 return true;
}

}
