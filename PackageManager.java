import java.io.*;
import java.util.Scanner;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class PackageManager{
   
   JFrame f;
   JButton jbutton;
   JTextField jtext;
   JTextArea jtextarea;
   
   //initialize found variable as not found
   static boolean found = false;
   
   PackageManager(){
	   
	   f = new JFrame();
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   jbutton = new JButton("FIND");
	   jtext = new JTextField(30);
	   jtextarea = new JTextArea(5,30);
	   jtextarea.setEditable(false);
	   
	   f.setLayout(new FlowLayout());
	   jbutton.addActionListener(i ->{
		   //clear the textarea to avoid unnecessary appending of text
		   jtextarea.setText(null);
		   
		   //fetch the file list into array
		   File[] files = new File("src/").listFiles();
		   //append the classname you enter with .java
		   String s =  jtext.getText().toString()+".java";
		   showFiles(files,s);
		   
	    });
		
		f.add(jtext);
		f.add(jbutton);
		f.add(jtextarea);
		f.setSize(400,400);
		f.setLocation(300,100);
		f.setVisible(true);
   }
   
   //return the package name.
   public void showFiles(File[] files, String className) {
	   String newpath = "";
		for (File file : files) {
			if (file.isDirectory()) {
				showFiles(file.listFiles(), className); // Calls same method again in case of directory
			} else {
				if(file.getName().equals(className)){
					//take absolutepath
					String ppath = file.getAbsolutePath();
					//remove everything before src
					int index = ppath.lastIndexOf("src\\");
					//now remove .java from end and replace \ with "."
					newpath = ppath.substring(index + 4,ppath.length()-5).replaceAll("\\\\",".");
					System.out.println("Package : "+newpath);
					//append the output
					jtextarea.append(newpath+"\n");
					//assign the found as true.
					found = true;
				}	
			}
			
		}
  }
  
  public static void main(String[] args) {
	 new PackageManager();
  }
}