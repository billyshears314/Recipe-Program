import java.util.ArrayList;
import java.util.StringTokenizer;


public class Search {
 
 private String[] kitchenItems = new String[20];
  
  Meal[] m = new Meal[20];
  Meal[] bestCandidate = new Meal[20];
  
     ArrayList<String> filename = new ArrayList<String>();
  
  public Search(Meal[] m){
   this.m=m;
  }
  
  public void initialize(){

    createBestCandidateList();

  }
  
public void createBestCandidateList(){
 int num=0;
   for(int i=0; i<m.length; i++){

     if((m[i].numberOfIngredientsMissing(kitchenItems)<=2)&&m[i].getName()!=null){
      bestCandidate[num]=m[i];
      num++;
     }

    }
   
 sortBestCandidateList();
}

public static int calculateNumberOfCandidates(Meal[] bestCandidate){
 
 int numberOfCandidates=0;
 
 for(int i=0; i<bestCandidate.length; i++){
  if(bestCandidate[i]!=null){
   numberOfCandidates++;
  }
 }
 return numberOfCandidates;
 
}


public Meal[] sortBestCandidateList(){

int counter=0;
Meal temp;

if(calculateNumberOfCandidates(bestCandidate)==0){
 return null;
}
else{
while(counter!=calculateNumberOfCandidates(bestCandidate)-1){
    counter=0;
    for(int i=0; i<calculateNumberOfCandidates(bestCandidate)-1;i++){

     if(bestCandidate[i].numberOfIngredientsMissing(kitchenItems)>
     bestCandidate[i+1].numberOfIngredientsMissing(kitchenItems)){
      
      temp=bestCandidate[i];
      bestCandidate[i]=bestCandidate[i+1];
      bestCandidate[i+1]=temp;  
     }
     else{
      counter++;
     }
    }
    
}

}

return bestCandidate;

}

public Meal[] getBestCandidateList(){
 return bestCandidate;
}

public String[] getBestCandidateListNames(){
 String[] a = new String[20];
 
 int k=0;
 
 while(getSpecificBestCandidate(k)!=null){
  a[k]=getSpecificBestCandidate(k).getName();
  k++;
 }
 return a;
}

public Meal getSpecificBestCandidate(int i){
 return bestCandidate[i];
}

public void setKitchenItems(String[] kitchenItems){
 this.kitchenItems=kitchenItems;
}

public String[] getMealNames(){
   String[] a = new String[20];
 
 int k=0;
 
 while(m[k].getName()!=null){
  a[k]=m[k].getName();
  k++;
 }
 return a;
}


public String[] searchByName(String str){
 String[] mealNames=getMealNames();
 String[] acceptableList = new String[100];
 int counter = 0;
 int wordStart=0;
 int j=0;
 int wordNumber=0;
 boolean noMoreWords = false;
 boolean foundWord = false;
 
 if(str.equals("")){
  
 }
 else{
 
 for(int i=0; i<getLengthOfMealNames(); i++){
  mealNames[i]=mealNames[i].toLowerCase();

  while(noMoreWords!=true){
   
   for(int k=0; k<str.length(); k++){
   
    if(calculateBoundaryLength(mealNames[i],wordNumber)>=str.length()){

      if(mealNames[i].charAt(wordStart+k)==str.charAt(k)){
     counter++;
      }
    
    }
    
   }
   
   if(counter==str.length()){
     foundWord=true; 
   }
     
   counter=0;
   
   wordStart=findNextWordStart(wordStart, mealNames[i]);
     if(wordStart==-1){
      noMoreWords = true;
    }
     wordNumber++;
   }
  wordNumber=0;
  wordStart=0;
  noMoreWords = false;
  
  if(foundWord==true){
   acceptableList[j]=mealNames[i];
   j++;
  }
  
  foundWord=false;
  }
 
 }
 return acceptableList;
 }

public int calculateBoundaryLength(String a, int n){
 
 int length=0;

 String[] word = new String[10];
 
 int counter=0;
 
 StringTokenizer st = new StringTokenizer(a); 
 while(st.hasMoreTokens()) { 
 word[counter] = st.nextToken();
 counter++;
 } 
 
 for(int i=n; i<counter; i++){
 length=word[i].length()+length;
 }
 length=length+(counter-n)-1;

 return length;
}


public int findNextWordStart(int n, String s){
 
 if(n<s.length()){
 while(s.charAt(n)!=' '){
  n++;
  if(n>=s.length()){
   return -1;
  }
 }
}
 
 return n+1;
}
 
public int getLengthOfMealNames(){
 int i=0;
 while(m[i].getName()!=null){
  i++;
 }
 return i;
}


public String[] searchByIngredients(String[] kitchenItems){
 String[] acceptableMeals = new String[100];
 
 int i=0;
 int k=0;
 
 while(m[i].getName()!=null){
  System.out.println("a");
  if(m[i].allIngredientsInMeal(kitchenItems)){
   System.out.println("yes");
   acceptableMeals[k]=m[i].getName();
   k++;
  }
  i++;
 }
 
 return acceptableMeals;
 
}

}


