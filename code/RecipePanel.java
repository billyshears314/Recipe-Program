import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class RecipePanel extends JFrame implements ActionListener, KeyListener{

  private static final long serialVersionUID = 1L;
  
  static JButton addMeal = new JButton("Add Meal");
  static JButton modifyMeal = new JButton("Meal List");
  static JButton confirmName = new JButton("Confirm");
  static JButton confirmIngredient = new JButton("Add Ingredient");
  static JTextField ingredientTextField = new JTextField(10);
  static JTextField nameTextField= new JTextField(10);
  static JLabel name=new JLabel("Search by Name:");
  static JLabel ingredients= new JLabel("Search by Ingredients:");
  static JTextArea ingredientTextArea = new JTextArea();
  static JTextArea mealTextArea= new JTextArea();
  static JButton clear = new JButton("Clear");
  static JScrollPane ingredientsScrollPane=new JScrollPane(ingredientTextArea);
  static JScrollPane mealScrollPane=new JScrollPane(mealTextArea);
  Search search;
  AddMeal addmeal = new AddMeal();
  Meal meal = new Meal();
  MealList modifyMealClass;
  final static int BACKSPACE = 8;
  ViewPanel vp;
  
 Meal[] m = new Meal[20];
    ArrayList<String> filename = new ArrayList<String>();
 
  
  String[] ingredientList = new String[20];
  String newline="\n";
  String[] mealNames;
  String[] empty = new String[20];

  int lastFileClicked=-1;
  int click=0;
       
   RecipePanel(){
        
           addMeal.addActionListener(this);
           modifyMeal.addActionListener(this);
        confirmIngredient.addActionListener(this);
        clear.addActionListener(this);
        ingredientTextField.addKeyListener(this);
        nameTextField.addKeyListener(this);
        
    
         modifyMealClass = new MealList(m, addmeal);
        
        
        setFonts();
        
        getAllInformation();
        
        search.initialize(); 
        
        mealTextArea.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent evt) {
               tAreaMousePressed(evt);
            }
         });
       
   }
   
  
   private void tAreaMousePressed(MouseEvent evt) {
      try {
         int offset = mealTextArea.viewToModel(evt.getPoint());
         Rectangle rect = mealTextArea.modelToView(offset);
 
         int startRow = mealTextArea.viewToModel(new Point(0, rect.y));
         int endRow = mealTextArea.viewToModel(new Point(mealTextArea.getWidth(), rect.y));

         if(!mealTextArea.getText().equals("")){
         getFileFromPosition(startRow, endRow);
         }
         
         if(click==2){
         vp = new ViewPanel(m[getMeal(mealNames[lastFileClicked])]);
         vp.createAndShowGUI();
         }
         
         mealTextArea.select(startRow, endRow);
      } catch (BadLocationException e) {
         e.printStackTrace();
      }
   }
       
       public void keyPressed(KeyEvent e) {
        boolean entered=false;
        
        getAllInformation();
        
        if(ingredientTextField.getText().equals("")&&nameTextField.getText().equals("")){
        }
        else{
        
        
           if(e.getKeyCode() == KeyEvent.VK_ENTER){
            
         mealTextArea.setText("");
         String text = ingredientTextField.getText();
         text=text.toLowerCase();
         
         //may try  capitalizing first letter if you're up to it
         
         ingredientTextArea.append(text + newline);
         addIngredientToList(text);
         ingredientTextField.setText("");
         findAppropriateMeals();
         
         entered=true;
            
           }
           else{
           if(!ingredientTextField.getText().equals("")){
            nameTextField.setText("");
            //mealTextArea.setText("");
           }
           else{
           
            if(nameTextField.getText().equals("")){
             if((e.getKeyCode() >= KeyEvent.VK_A)||(e.getKeyCode() <= KeyEvent.VK_Z)){
              if(e.getKeyCode()!=BACKSPACE){
               findSearchedMeals(nameTextField.getText()+(char)(e.getKeyCode()+32));
              }
             }
            }
            else{
           if(entered==false){
           if((e.getKeyCode() >= KeyEvent.VK_A)||(e.getKeyCode() <= KeyEvent.VK_Z)){
            if(e.getKeyCode()==BACKSPACE){
             String newWord = nameTextField.getText();
             if(newWord.length()>0){
             newWord=newWord.substring(0,newWord.length()-1);
             }
             findSearchedMeals(newWord);
            }
            else{
            if((e.getKeyCode()>=65)&&(e.getKeyCode()<=90))
            findSearchedMeals(nameTextField.getText()+(char)(e.getKeyCode()+32));
            else
            findSearchedMeals(nameTextField.getText());
            }
           }
           }
           }
           }
           }
        }
       }
         
       
       public void actionPerformed(ActionEvent evt){
        
        if(evt.getSource() == addMeal){
           addmeal.setMeal(meal);
           addmeal.createAndShowGUI();
           addmeal.initialize();
        }
        
        if(evt.getSource() == modifyMeal){
         modifyMealClass.reset();
         modifyMealClass.createAndShowGUI();
        }
        
        if(evt.getSource() == confirmIngredient){
         
         getAllInformation();
         
         if(ingredientTextField.getText().equals("")){
          
         }
         else
         {
         mealTextArea.setText("");
         String text = ingredientTextField.getText();
         text=text.toLowerCase();
         
         //may try  capitalizing first letter if you're up to it
         
         ingredientTextArea.append(text + newline);
         addIngredientToList(text);
         ingredientTextField.setText("");
         findAppropriateMeals();
         }
         
        }
        
        if(evt.getSource() == clear){
         
         ingredientTextArea.setText("");
            mealTextArea.setText("");
            
            Arrays.fill(ingredientList, null);
        }
       }  
        public void findAppropriateMeals(){
           String text;  
        
        search.setKitchenItems(ingredientList);
        
         mealNames = search.searchByIngredients(ingredientList);
         
         int j=0;
         
         while(mealNames[j]!=null){
          mealTextArea.append(mealNames[j] + newline);
          j++;
         }
        
        /*
        search.createBestCandidateList();
        mealNames= search.getBestCandidateListNames();
  
        
        int j=0;
        while(search.getSpecificBestCandidate(j)!=null){
        text = search.getSpecificBestCandidate(j).getName();

        //int missingItems =search.getSpecificBestCandidate(j).numberOfIngredientsMissing(ingredientList);
        //mealTextArea.append(missingItems+ " " + text + newline);
        mealTextArea.append(text + newline);
        j++;
        
        */
        //}
        
       }
        
        
      public void findSearchedMeals(String s){
       
       String text;
       
       mealTextArea.setText("");
       
         mealNames= search.searchByName(s);

         int j=0;
         while(mealNames[j]!=null){
         text = mealNames[j];
         mealTextArea.append(text + newline);
         j++;
            }
      }

       
            void createAndShowGUI() {
          //Create and set up the window.
          JFrame frame = new JFrame("Main Menu");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          //Set up the content pane.
          addComponentsToPane(frame.getContentPane());

          //Display the window.
          frame.pack();
          frame.setResizable(false);
          frame.setLocation(250,75);
          frame.setVisible(true);
      }

 

public void addIngredientToList(String item){
 int k=0;
 while(ingredientList[k]!=null){
  k++;
 }
 ingredientList[k]=item;
}


@Override
public void keyReleased(KeyEvent arg0) {
 // TODO Auto-generated method stub
 
}


@Override
public void keyTyped(KeyEvent arg0) {
 // TODO Auto-generated method stub
 
}


public String getFileFromPosition(int start, int end){
 

 int[] startPosition = new int[400];
 startPosition[0]=0;
 
 int k=1;
 while(mealNames[k]!=null){
 startPosition[k]=mealNames[k-1].length()+startPosition[k-1];
 k++;
 
}
 int i=0;
 
 while(mealNames[i]!=null){
  
  if(isBetween(start,end,startPosition[i])){
   if(lastFileClicked==i){
      click=2;
   }
   else{
      lastFileClicked=i;
      click=1;
   }
   return mealNames[i];
   
  }
  i++;
  
 }
 return "Ericka";

}

public boolean isBetween(int a, int b, int c){
 c=c+1;
   if((c>=a)&&(c<=b)){
    return true;
   }
   return false;
}


public static void addComponentsToPane(Container pane) {

    final int leftBorder = 50;
    final int rightBorder = 50;
    final int bottomBorder = 20;
    final int topBorder = 20;
    
    final int row1row2Border = 20;
    final int row2row3Border = 10;
    
    final int col1col2Border = 25;
    final int col2col3Border = 25;
         
    pane.setBackground(new Color(255,255,102)); 
   
  pane.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  
  c.weightx = 0.5;
  
  //row 1
  c.fill = GridBagConstraints.HORIZONTAL;
  c.gridx = 0;
  c.gridy = 0;
  c.insets = new Insets(topBorder,leftBorder,row1row2Border,col1col2Border);
  pane.add(addMeal, c);
  
  c.gridx = 2;
  c.gridy = 0;
  c.insets = new Insets(topBorder,col2col3Border,row1row2Border,rightBorder);
  pane.add(modifyMeal, c);
  
  //row 2
  c.gridx = 0;
  c.gridy = 1;
  c.insets = new Insets(row1row2Border,leftBorder,row2row3Border,col1col2Border);
  pane.add(name, c);
  
  c.gridx = 1;
  c.gridy = 1;
  c.insets = new Insets(row1row2Border,col1col2Border,row2row3Border,col2col3Border);
  pane.add(nameTextField, c);
  
  //row 3
  c.gridx = 0;
  c.gridy = 2;
  c.insets = new Insets(row2row3Border,leftBorder,10,col1col2Border);
  pane.add(ingredients, c);
  
  c.gridx = 1;
  c.gridy = 2;
  c.insets = new Insets(row2row3Border,col1col2Border,10, col2col3Border);
  pane.add(ingredientTextField, c);
  
  c.gridx = 2;
  c.gridy = 2;
  c.insets = new Insets(row2row3Border,col2col3Border,10,rightBorder);
  pane.add(confirmIngredient, c);
  
  
  //row 4
  c.gridx = 0;
  c.gridy = 3;
  c.ipady = 150;
  c.insets = new Insets(10,leftBorder,10,10);
  pane.add(ingredientsScrollPane, c);
  ingredientTextArea.setAutoscrolls(true);
  
  c.gridx = 1;
  c.gridy = 3;
  c.gridwidth = 2;
  c.insets = new Insets(10,10,10,rightBorder);
  pane.add(mealScrollPane, c);
  
  c.gridx = 0;
  c.gridy = 4;
  c.ipady=0;
  c.gridwidth = 1;
  c.insets = new Insets(10,leftBorder,bottomBorder,10);
  pane.add(clear, c);
     }

public void setFonts(){
 
   name.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   ingredients.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   addMeal.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   modifyMeal.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   confirmIngredient.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   clear.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   nameTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   ingredientTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   ingredientTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   mealTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
  // 51,0,51 purple

}

public void getAllInformation(){
 
 //initialize
        
  for(int i=0; i<m.length; i++){
   m[i] = new Meal();
  }
 
  FileClass f = new FileClass();
 
    filename=f.inputFilenames();
    
    for(int k=0; k<filename.size(); k++){
     m[k].setName(f.inputName(filename.get(k)));
     
      for(int j=0; j<f.inputAmounts(filename.get(k)).length; j++){
       m[k].addAmount(f.inputAmounts(filename.get(k))[j]);
    
      }
      
      for(int j=0; j<f.inputIngredients(filename.get(k)).length; j++){
       m[k].addIngredient(f.inputIngredients(filename.get(k))[j]);
       }
      
       m[k].setInstruction(f.inputInstructions(filename.get(k)));    
     
}

    search = new Search(m);

}

public int getMeal(String filename){
 
 int i=0;
 
 while(m[i].getName()!=null){
  if(filename.toLowerCase().equals(m[i].getName().toLowerCase())){
   return i;
  }
  i++;
 }
 
 return -1;
}

}

  