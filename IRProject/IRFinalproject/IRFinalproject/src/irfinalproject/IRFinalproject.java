
package irfinalproject;
import java.io.*;
import java.util.*;

public class IRFinalproject {
 public static boolean isNumeric(String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }
public static void main(String[] args) throws IOException{
    
    ArrayList<String> StopWords=new ArrayList <>();  // create arraylist to add all stop words to it from StopWords flle

    try{
        
    Scanner s = new Scanner(new FileReader("StopWords.txt")); // scanner for read stop words from StopWords file
    
    while(s.hasNext()){                 // while scanner still has next stop words  
        String word=s.next().toLowerCase();
        StopWords.add(word);       // add all stop words to the arraylist StopWords
    }  // finish all stop words in the file StopWords 
    
    // System.out.println(StopWords);
    
    s = new Scanner(new FileReader("document.txt"));    //Read Corpus
  
  //  File f=new File("document.txt");
  // FileWriter fr = new FileWriter(f); 
  //  BufferedWriter bf = new BufferedWriter(fr); // 
    
     PorterAlgo pt= new PorterAlgo();  // create object from class processText to process the text in the corpus docs
 int c=0;
    while(s.hasNext()){
        String word=s.next().toLowerCase();
        String wordSteming= pt.stripAffixes(word);
        //String clean=wordSteming.replaceAll("[0-9]","");
         if(isNumeric(wordSteming)){
        //  bf.newLine();
           c++;
           //continue;
       } 
       // if(word.equals("[0-9]")){
//
           // c++;
      //  }
        if(!StopWords.contains(word)){
       //   bf.write(wordSteming+" ");
        }// write in New File    
    }
    s.close();
   System.out.println(c);
}catch (FileNotFoundException ex) {
}
   Index index=new Index();
   index.getIndex(); 

     tfidfAndidf a=new tfidfAndidf();
     a.Start(StopWords);
    
}
}
