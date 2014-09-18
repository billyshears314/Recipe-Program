import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AddMeal extends JFrame implements ActionListener, KeyListener {
 
 private static final long serialVersionUID = 1L;
 
 Component c;
 
   static JFrame frame = new JFrame("Add Recipe");
   static JButton close = new JButton("Close");
   static JButton confirmIngredient = new JButton("Add Ingredient");
   static JTextField ingredientTextField = new JTextField(12);
   static JTextField nameTextField = new JTextField(12);
   static JTextField amountTextField = new JTextField(12);
   static JLabel nameLabel = new JLabel("Name: ");
   static JLabel ingredient= new JLabel("Ingredient: ");
   static JLabel amount = new JLabel("Amount: ");
   static JTextArea ingredientTextArea = new JTextArea();
   static JTextArea instructionsTextArea= new JTextArea();
   static JButton clear = new JButton("Clear");
   static JScrollPane ingredientsScrollPane=new JScrollPane(ingredientTextArea);
   static JScrollPane instructionsScrollPane=new JScrollPane(instructionsTextArea);
   
   static JButton save = new JButton("Save");
   
   String newline = "\n";
   
   String[] ingredientList= new String[20];
   String[] amountList = new String[20];
   String instructions;
   Meal m = new Meal();
   
   private String name;
   
   Boolean hasPassed=false;

      public AddMeal(){   
       
       confirmIngredient.addActionListener(this);
       clear.addActionListener(this);
       save.addActionListener(this);
       close.addActionListener(this);
       
       ingredientTextField.addKeyListener(this);
       
      }

      public void setMeal(Meal m){
       this.m=m;
       this.name=m.getName();
    this.amountList=m.getAmounts();
    this.ingredientList=m.getIngredients();
    this.instructions=m.getInstructions();
    
      }
       
      public void initialize(){
       

        
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setWrapStyleWord(true); 
        
        setFonts();
        
        nameTextField.setText(m.getName()); 
    
       }
      

      public static void addComponentsToPane(Container pane) {

     final int leftBorder = 50;
     final int rightBorder = 50;
     final int bottomBorder = 20;
     final int topBorder = 20;
     
     final int row1row2Border = 20;
     final int row2row3Border = 10;
     
     final int col1col2Border = 50;
     final int col2col3Border = 25;
          
     pane.setBackground(new Color(255,255,102));
    
   pane.setLayout(new GridBagLayout());
   GridBagConstraints c = new GridBagConstraints();
   
   double leftx = 0.4;
   int sizex = 70;
   
   c.weightx = 0.5;

   
   //c.fill = GridBagConstraints.HORIZONTAL;
   c.gridx = 2;
   c.gridy = 0;
   c.insets = new Insets(topBorder,10,10,rightBorder);
   pane.add(close, c);
   
   
   c.gridx = 2;
   c.gridy = 3;
   c.insets = new Insets(row1row2Border,col2col3Border,row2row3Border,rightBorder);
   pane.add(confirmIngredient, c);
   
   //row 1
   //c.fill = GridBagConstraints.HORIZONTAL;

   
   //row 6
   c.ipadx = sizex;
   c.gridx = 0;
   c.gridy = 5;
   c.ipady=0;
   c.gridwidth = 1;
   c.insets = new Insets(10,leftBorder,bottomBorder,10);
   pane.add(clear, c);
   
   c.gridx = 2;
   c.gridy = 5;
   c.ipady=0;
   c.gridwidth = 1;
   c.insets = new Insets(10,10,bottomBorder,rightBorder);
   pane.add(save, c);
      
   
 c.fill = GridBagConstraints.HORIZONTAL;
   
   c.ipadx = sizex;
   c.gridx = 0;
   c.gridy = 1;
   c.insets = new Insets(10,leftBorder,row1row2Border,col1col2Border);
   pane.add(nameLabel, c);
   
   c.ipadx = 0;
   c.gridx = 1;
   c.gridy = 1;
   c.insets = new Insets(10,col1col2Border,row1row2Border,col2col3Border);
   pane.add(nameTextField, c);
   
   //row 2
   c.ipadx = sizex;
   c.gridx = 0;
   c.gridy = 2;
   c.insets = new Insets(row1row2Border,leftBorder,row2row3Border,col1col2Border);
   pane.add(amount, c);
   
   
   c.gridx = 1;
   c.gridy = 2;
   c.insets = new Insets(row1row2Border,col1col2Border,row2row3Border,col2col3Border);
   pane.add(amountTextField, c);
     
   //row 3
   c.ipadx = sizex;
   c.gridx = 0;
   c.gridy = 3;
   c.insets = new Insets(row1row2Border,leftBorder,row2row3Border,col1col2Border);
   pane.add(ingredient, c);
   
   c.gridx = 1;
   c.gridy = 3;
   c.insets = new Insets(row1row2Border,col1col2Border,row2row3Border,col2col3Border);
   pane.add(ingredientTextField, c);
   

   
   //row 5
   c.ipadx = sizex;
   c.gridx = 0;
   c.gridy = 4;
   c.ipady = 150;
   c.insets = new Insets(10,leftBorder,10,10);
   ingredientTextArea.setAutoscrolls(true);
   pane.add(ingredientsScrollPane, c);

   
   c.gridx = 1;
   c.gridy = 4;
   c.gridwidth = 2;
   c.insets = new Insets(10,10,10,rightBorder);
   instructionsTextArea.setAutoscrolls(true);
   pane.add(instructionsScrollPane, c);
      
      }
      
      public void actionPerformed(ActionEvent evt){
        
       if(evt.getSource() == close){

        clear();
        frame.setVisible(false); //you can't see me!
        frame.dispose();
        
        
       }
       
       if(evt.getSource() == confirmIngredient){
        
        String textAmount = amountTextField.getText();
        textAmount = textAmount.toLowerCase();
        
        String text = ingredientTextField.getText();
        text=text.toLowerCase();
        
        ingredientTextArea.append(textAmount + "  " + text + newline);
        
        
        addIngredientToList(text);
        addAmountToList(textAmount);
        
        ingredientTextField.setText("");
        amountTextField.setText("");
        
       }
       
       if(evt.getSource() == clear){  
        
            clear();
       }
       
       if(evt.getSource() == save){
      
        
        String textInstructions = instructionsTextArea.getText();
       // textInstructions=textInstructions.toLowerCase();
        
       // String[] lineInstructions = chopTextInstructions(textInstructions);
        
        String text = nameTextField.getText();
        
        FileClass f = new FileClass();
        
        f.saveOutputFile(text, amountList, ingredientList, textInstructions);
        if(f.isStringInFile(text)==false){
         f.addString("filenames", text);
        }
        
       //close window
        clear();
        frame.setVisible(false); //you can't see me!
        frame.dispose();

       }
      }

/*static*/ void createAndShowGUI() {
    //Create and set up the window.
    //JFrame frame = new JFrame("Add Recipe");
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

public void addAmountToList(String amount){
 int k=0;
 while(amountList[k]!=null){
  k++;
 }
 amountList[k]=amount;
 
}


public void setFonts(){
 
 //Comic Sans MS
 
   nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   close.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   ingredient.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   amount.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   ingredientTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   confirmIngredient.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   nameTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   amountTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   ingredientTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   instructionsTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   clear.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   save.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
 
}


public void keyPressed(KeyEvent e) {
 
 if(e.getKeyCode()==KeyEvent.VK_ENTER){
  

   String textAmount = amountTextField.getText();
   textAmount = textAmount.toLowerCase();
   
   String text = ingredientTextField.getText();
   text=text.toLowerCase();
   
   ingredientTextArea.append(textAmount + "  " + text+ newline);
   
   
   addIngredientToList(text);
   
   addAmountToList(textAmount);
   
   ingredientTextField.setText("");
   
   amountTextField.setText("");
   
 }
 
}

public void keyReleased(KeyEvent e) {
 
}

public void keyTyped(KeyEvent e) {
 
}

public void clear(){
 
   //nameTextField.setText("");
   //amountTextField.setText("");
   //ingredientTextField.setText("");
   ingredientTextArea.setText("");
   //instructionsTextArea.setText("");
   Arrays.fill(ingredientList, null);
   Arrays.fill(amountList, null);
}

public void addStuff(){

int j=0;
  while(m.getIngredient(j)!=null){
ingredientTextArea.append(m.getAmount(j) + " " + m.getIngredient(j)+newline);
j++;

}

if(m.getInstructions()!=null){
 instructionsTextArea.append(m.getInstructions());
}

}


/*
public String[] chopTextInstructions(String s){
 String[] a = new String[20];
 
 int i=0;
 
 int counter =0;
 
 while(s.charAt(i)!=null){
  i
  if(i%50==0){
   counter++;
  }
  a[counter]
 }
 
 
}
*/

}
