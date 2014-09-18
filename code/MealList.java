import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;


public class MealList extends JFrame implements ActionListener{

 private static final long serialVersionUID = 1L;
 
    static JFrame frame = new JFrame("Meal List");
 static JTextArea mealTextArea = new JTextArea(); 
      static JScrollPane mealScrollPane=new JScrollPane(mealTextArea);
      static JButton delete = new JButton("Delete");
      static JButton close = new JButton ("Close");
      static JButton modify = new JButton ("Modify");
      ViewPanel vp;
      AddMeal addMeal;
      static ArrayList<String> filenames = new ArrayList<String>();
   
   static String newline="\n";
   
   static int click=0;
   static int lastFileClicked=-1;
   
   Meal[] m = new Meal[100];
   
   public MealList(Meal[] m, AddMeal addMeal){
    
    this.m=m;
    this.addMeal=addMeal;
    
    delete.addActionListener(this);
    close.addActionListener(this);
    modify.addActionListener(this);
    
    
      // a = new AddMeal(m[getMeal(filenames.get(lastFileClicked))]);
    
    FileClass f = new FileClass();
    filenames=f.inputFilenames();
    
    setFonts();
    
       mealTextArea.addMouseListener(new MouseAdapter() {
           @Override
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
           
           getFileFromPosition(startRow, endRow);
           
           if(click==2){
             vp = new ViewPanel(m[getMeal(filenames.get(lastFileClicked))]);
            // vp.setMeal(m[getMeal(filenames.get(lastFileClicked))]);
             vp.createAndShowGUI();
           }
           
           mealTextArea.select(startRow, endRow);
        } catch (BadLocationException e) {
           e.printStackTrace();
        }
     }
   
   

    public static void addComponentsToPane(Container pane) {
     
     putFilesInTextArea(); 
        
      pane.setBackground(new Color(255,255,102)); 
      
     pane.setLayout(new GridBagLayout());
     GridBagConstraints c = new GridBagConstraints();
        
      c.weightx = 0.5;
     c.fill = GridBagConstraints.HORIZONTAL;
      //close button
      c.gridx = 1;
      c.gridy = 0;
      c.gridwidth=1;
      c.ipady=0;
      c.insets = new Insets(10,10,10,10);
      pane.add(close, c);
      
      //menu list
      c.gridx = 0;
      c.gridy = 1;
      c.gridwidth = 2;
      c.ipady = 280;
      c.insets = new Insets(10,10,10,10);
      pane.add(mealScrollPane, c);
      
      //delete button
      c.gridx = 0;
      c.gridwidth = 1;
      c.gridy = 2;
      c.ipady = 0;
      c.insets = new Insets(10,10,10,10);
      pane.add(modify, c);
      
      //modify button
      c.gridx = 1;
      c.gridwidth = 1;
      c.gridy = 2;
      c.ipady = 0;
      c.insets = new Insets(10,10,10,10);
      pane.add(delete, c);
    }    
 
 /*static*/ void createAndShowGUI() {
     //Create and set up the window.
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     //Set up the content pane.
     addComponentsToPane(frame.getContentPane());

     //Display the window.
     frame.pack();
     frame.setResizable(false);
     frame.setLocation(375,75);
     frame.setSize(270,450);
     frame.setVisible(true);

 }


 public void actionPerformed(ActionEvent evt) {
  
     if(evt.getSource() == delete){
      
      if(lastFileClicked!=-1){
      
     int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this recipe?");
     
     if (confirmed == JOptionPane.YES_OPTION){
     
    boolean success = (new File(filenames.get(lastFileClicked)+".txt")).delete();
    /*
    if (!success) {
     System.out.println("deletion failed");
    }
    else{
     System.out.println("deletion success");
    }
    */
      FileClass f = new FileClass();
      f.deleteFileName(filenames.get(lastFileClicked));
      
  reset();
  
     }
  
     }
      
      
   }
     
     if(evt.getSource() == modify){
      if(lastFileClicked!=-1){
        modify();
      }
     }
     
     if(evt.getSource() == close){
      close();
     }
 
  
 }
 
 public String getFileFromPosition(int start, int end){

  int[] startPosition = new int[400];
  startPosition[0]=0;
  

  for(int k=1; k<filenames.size(); k++){
   
  startPosition[k]=filenames.get(k-1).length()+startPosition[k-1];
  
 }
  
   
   for(int i=0; i<filenames.size(); i++){
  
       end--;
    
   if(isBetween(start,end,startPosition[i])){
    if(lastFileClicked==i){
       click=2;
    }
    else{
       lastFileClicked=i;
       click=1;
    }
    return filenames.get(i);
    
   }
   
   start--;
   
  }
  
  return "Ericka";

}

 
 public boolean isBetween(int a, int b, int c){
 
    if((c>=a)&&(c<=b)){
     return true;
    }
    return false;
 }
 
 public static void putFilesInTextArea(){
    int k=0;

    mealTextArea.setText("");
    
    Collections.sort(filenames, String.CASE_INSENSITIVE_ORDER);
    
     for(int i=0; i<filenames.size();i++){
     
     mealTextArea.append(filenames.get(k)+newline);
     k++;
    }
 }
 
 
 public void setFonts(){
  
    close.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
    delete.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
    mealTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
    modify.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
    // 51,0,51 purple

 }
 
 public void modify(){
 
        addMeal.setMeal(m[getMeal(filenames.get(lastFileClicked))]);
        addMeal.createAndShowGUI();
        addMeal.initialize();
        addMeal.addStuff();
        
        
 }
 
 public void close(){
  frame.setVisible(false);
  frame.dispose();
  
  mealTextArea.setText("");

 }
 
 public int getMeal(String filename){
  for(int i=0; i<m.length; i++){
   if(filename.equals(m[i].getName())){
    return i;
   }
  }
  return -1;
 }
 
 public void reset(){
       mealTextArea.setText("");  
       FileClass f = new FileClass();
       filenames=f.inputFilenames();
       putFilesInTextArea();
 }
 
}
