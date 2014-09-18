import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileClass {
 
 public FileClass(){
  
 }
 
 public String inputName(String name){
  
  try{
   String fileName=name+".txt";
    BufferedReader reader = new BufferedReader( new FileReader(fileName) );
    String line = reader.readLine();
    
        reader.close();
    return line;
  
 }catch(Exception e){}
  
  return null;
  
 }
 
 public String[] inputAmounts(String name){
  
  String[] amountList= new String[20];
  int counter=0;
  
  try{
   String fileName=name+".txt";
    BufferedReader reader = new BufferedReader( new FileReader(fileName) );
    String line = reader.readLine();
    line=reader.readLine();
 
    while(!line.equals("INGREDIENTS")) {
        amountList[counter]=line;
        line = reader.readLine();
        counter++;
    }
    
    reader.close();
    return amountList;
    
  }catch(Exception e){}
  
  return null;
 }
 
 public String[] inputIngredients(String name){
  
  String[] ingredientList= new String[20];
  int counter=0;
  
  try{
   String fileName=name+".txt";
    BufferedReader reader = new BufferedReader( new FileReader(fileName) );
    String line = reader.readLine();
    while(!line.equals("INGREDIENTS")){
       line = reader.readLine();
    }
             line = reader.readLine();
    while(!line.equals("INSTRUCTIONS")) {
        ingredientList[counter]=line;
     line = reader.readLine();
        counter++;
    }
    
    reader.close();
    return ingredientList;
    
  }catch(Exception e){}
  
  return null;
 }
 
 public String inputInstructions(String name){
  
  String instructionsList;
  int counter=0;
  
  try{
   String fileName=name+".txt";
    BufferedReader reader = new BufferedReader( new FileReader(fileName) );
    String line = reader.readLine();
    while(!line.equals("INSTRUCTIONS")){
       line = reader.readLine();
    }
    
    line = reader.readLine();
 /*
    while(line!=null) {
        instructionList[counter]=line;
        line = reader.readLine();
        counter++;
    }
  */
    instructionsList = line;
    reader.close();
    return instructionsList;
    
  }catch(Exception e){}
  
  return null;
 }
 
 public ArrayList<String> inputFilenames(){
  
  //change
  ArrayList<String >ingredientList= new ArrayList<String>();
  //int counter=0;
  
  try{
   String fileName="filenames.txt";
    BufferedReader reader = new BufferedReader( new FileReader(fileName) );
    String line = reader.readLine();

    while(line!=null) {
       // ingredientList[counter]=line;
     ingredientList.add(line);
        line = reader.readLine();
       // counter++;
    }
    
    reader.close();
    
    return ingredientList;
    
  }catch(Exception e){}
  
  return null;
 }
  

 public void outputInstructions(){
  
 }
 
 
 public void addString(String filename, String str){
  
  String[] savedStrings = new String[50];
  int k=0;
  
  try{
  BufferedReader reader = new BufferedReader( new FileReader(filename + ".txt") );
  
  String line = reader.readLine();
  
  while(line!=null){
  savedStrings[k] = line;
   line=reader.readLine();
  k++;
  }
  
  }catch(Exception e){};
  
  k=0;
 
  try {
        PrintWriter out = new PrintWriter(new FileWriter(filename+".txt"));
         
     while(savedStrings[k]!=null){
      out.println(savedStrings[k]);
      k++;
     }

        out.println(str);

        out.close();
     }
        catch(IOException e1) {
          System.out.println("Error during reading/writing");
     }

  }
 
 public boolean isStringInFile(String str){
  String[] mealNames= new String[500];
  int counter=0;
  
  try{
   String fileName="filenames"+".txt";
    BufferedReader reader = new BufferedReader( new FileReader(fileName) );
    String line = reader.readLine();
    while(line!=null){
       line = reader.readLine();
       mealNames[counter]=line;
       counter++;
    }
    
    for(int i=0; i<mealNames.length; i++){
     if(mealNames!=null){
      if(mealNames[i].equals(str)){
       return true;
      }
     }
    }

    reader.close();
    
  }catch(Exception e){}
  
  return false;  
 }
 
 public void deleteFileName(String deleteName){
  
  String[] savedStrings = new String[50];
  int k=0;
  
  try{
  BufferedReader reader = new BufferedReader( new FileReader("filenames" + ".txt") );
  
  String line = reader.readLine();
  
  while(line!=null){
  savedStrings[k] = line;
   line=reader.readLine();
  k++;
  }
  
  }catch(Exception e){};
  
  k=0;
  
  int z=0;
  
  while(savedStrings[z]!=null){
   if(savedStrings[z].equals(deleteName)){
    
    k--; 
   }
   else{
    savedStrings[k]=savedStrings[z];

   }
   k++;
   z++;
  }
  
  savedStrings[k]=null;
  
  k=0;
  
  try {
        PrintWriter out = new PrintWriter(new FileWriter("filenames"+".txt"));
         
     while(savedStrings[k]!=null){
      out.println(savedStrings[k]);
      k++;
     }

        out.close();
     }
        catch(IOException e1) {
          System.out.println("Error during reading/writing");
     }
  
 }
 


public void closeInputFile(String filename){
 filename=filename+".txt";
 try{
  BufferedReader reader = new BufferedReader( new FileReader(filename) );
  
  reader.close();
 }catch(Exception e){}
 

}


public void saveOutputFile(String filename, String[] amounts, String[] ingredients, String instructions){
 
 try{
 PrintWriter out = new PrintWriter(new FileWriter(filename+".txt"));
 
 out.println(filename);
 
 int i=0;
 
 while(amounts[i]!=null){
    out.println(amounts[i]);
    i++;
 }
 i=0;
 
 out.println("INGREDIENTS");
 
    while(ingredients[i]!=null){
       out.println(ingredients[i]);
       i++;
       }
    
    out.println("INSTRUCTIONS");
    
  //  i=0;
 //   while(instructions[i]!=null){
     out.println(instructions);
  //   i++;
  //  }
 
 out.close();
 }
    catch(IOException e1) {
        System.out.println("Error during reading/writing");
    }
}

}
 
 

 
