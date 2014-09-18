import java.awt.*;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPanel extends JFrame implements ActionListener{
 
 private static final long serialVersionUID = 1L;
 
    JFrame frame = new JFrame("Main Menu");
 private static JButton close = new JButton("Close");
 private static JLabel[] comboLabel = new JLabel[20];
 private static JLabel[] instructionLabel = new JLabel[40];
 private static JLabel nameLabel;
 private static JTextArea instructionsTextArea = new JTextArea();
 private static JScrollPane instructionsScrollPane=new JScrollPane(instructionsTextArea);
  private String name;
 private static String[] ingredients = new String[20];
 private String[] amounts = new String[20];
 private static String instructions;
 
 private static String[] combination = new String[20];
 

 public ViewPanel(Meal m){
  
  getAllInformation(m);
  
    this.name=m.getName();
    this.amounts=m.getAmounts();
    this.ingredients=m.getIngredients();
    this.instructions=m.getInstructions();

    close.addActionListener(this);
    
    initialize();
    
 }
 
 /*
    public void setMeal(Meal m){
    // this.m=m;
     
     getAllInformation(m);
     this.name=m.getName();
    this.amounts=m.getAmounts();
    this.ingredients=m.getIngredients();
    this.instructions=m.getInstructions();
    
    }
    */
 
 public void initialize(){
  
  
     instructionsTextArea.setLineWrap(true);
   instructionsTextArea.setWrapStyleWord(true);
   
   instructionsTextArea.setText(instructions);
   
   instructionsTextArea.setAutoscrolls(true);
   
   instructionsTextArea.setSize(200,400);
  
  nameLabel = new JLabel(name);
  
  combineIngredientsAndAmounts(amounts, ingredients);
  
 }
 
 
 public void combineIngredientsAndAmounts(String[] amounts, String[] ingredients){
  
  for(int i=0; i<amounts.length; i++){
   combination[i]=amounts[i]+" " + ingredients[i];
  }
 }
 

 public static void addComponentsToPane(Container pane) {

     final int leftBorder = 50;
     final int rightBorder = 50;
     final int bottomBorder = 10;
     final int topBorder = 10;
          
     pane.setBackground(new Color(255,255,102)); 
    
   pane.setLayout(new GridBagLayout());
   GridBagConstraints c = new GridBagConstraints();
   
   c.weightx = 0.5;
   
   c.gridwidth = 3;
   
   c.ipady=0;
   c.ipadx=0;
   c.gridy=2+calculateHalf(calculateIngredientsLength());
   c.gridx=0;
   c.insets = new Insets(5, 2 , bottomBorder, 5);
   pane.add(close, c);
   
   c.gridy=0;
   c.gridx = 1;
   c.insets = new Insets(topBorder, 10, 20, 10);
   pane.add(nameLabel, c);
   
   
   for(int i=0; i<calculateIngredientsLength(); i++){

    comboLabel[i] = new JLabel(combination[i]);
    
    if(i<calculateHalf(calculateIngredientsLength())){
       c.fill = GridBagConstraints.HORIZONTAL;
    c.gridy=i+1;
    c.gridx=0;
    c.gridwidth=2;
    c.insets = new Insets(5, leftBorder, 5, 35);
    pane.add(comboLabel[i],c);
   }
    else{
    c.gridy=i+1-calculateHalf(calculateIngredientsLength());
    c.gridx=2;
    c.gridwidth=2;
    c.insets = new Insets(5, 35, 5, rightBorder);
    pane.add(comboLabel[i],c);
    }
    
   }

   c.gridwidth =3;
   c.fill = GridBagConstraints.HORIZONTAL;
   c.ipady = 150;
   c.ipadx = 150;
   c.gridy=1+calculateHalf(calculateIngredientsLength());
   c.gridx=1;
   c.insets = new Insets(20, 10, bottomBorder, 10);
   pane.add(instructionsScrollPane, c);
   
   setFonts();
 
 }
 
    
    void createAndShowGUI() {
     //Create and set up the window.
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     //Set up the content pane.
     addComponentsToPane(frame.getContentPane());

     //Display the window.
     frame.pack();
     frame.setResizable(false);
     frame.setLocation(250,75);
     frame.setVisible(true);
 }
    
    public static int calculateHalf(int n){
     if(n%2==0)
      return n/2;
     else
       return ((n-1)/2)+1;
     
    }
    
    public static int calculateIngredientsLength(){
     int i=0;
     
     while(ingredients[i]!=null){
      i++;
     }
     return i;
    }
    
    public static void setFonts(){
   nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
   int k=0;
   
   while(comboLabel[k]!=null){
    comboLabel[k].setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
    k++;
   }
   
   instructionsTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   
   close.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
   
    }

 public void actionPerformed(ActionEvent evt) {
  
  if(evt.getSource() == close){
      frame.setVisible(false); 
    frame.dispose();
  }
  
 }
 
 public void getAllInformation(Meal m){
  FileClass f = new FileClass();
  
      m.setName(f.inputName(m.getName()));
      
      m.deleteIngredients();
      m.deleteAmounts();
      

       for(int j=0; j<f.inputAmounts(m.getName()).length; j++){
        m.addAmount(f.inputAmounts(m.getName())[j]);
     
       }
       
       for(int j=0; j<f.inputIngredients(m.getName()).length; j++){
        m.addIngredient(f.inputIngredients(m.getName())[j]);
        }
       
        m.setInstruction(f.inputInstructions(m.getName()));    
 
 }
 
}
