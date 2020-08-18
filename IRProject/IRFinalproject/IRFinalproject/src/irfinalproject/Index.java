
package irfinalproject;
import static irfinalproject.IRFinalproject.isNumeric;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class Index {
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
    HashMap<String,LinkedList<String>> docs=new HashMap<String,LinkedList<String>>();
    
    Scanner s = new Scanner(new FileReader("D:\\newdec.txt"));   

    public Index() throws FileNotFoundException {
    }
    public void getIndex() throws FileNotFoundException {
        
        int docNumber=0;int c=0;
        while(s.hasNext()){
            String Word=s.next();
c++;
            if(!isNumeric(Word)){
                
                int hash=Word.hashCode();
                String out=Freq(Word,docNumber);
                if(out.equals("no")){
                    continue;
                }
                System.out.println(out);

                if(!docs.containsKey(Word)){
            LinkedList<String> a=new LinkedList<>();
            a.add(out);
            docs.put(Word,a);
            Word=s.next();
               // System.out.println(s.next());
                }
                else{
                   if(!docs.get(Word).contains(out)){
                    docs.get(Word).add(out);
                    Word=s.next();
                    }
                }
            }
            else{
                docNumber++;
                Word=s.next();
                //System.out.println("********************");
                //System.out.println(docs);
            }
        }
        System.out.println(docs);
         addHashMap(docs);  

   System.out.println("ttt");
        
    }
    
    public void addHashMap(HashMap<String,LinkedList<String>> addword){
        File file=new File("D:\\newhashSave.txt");
        try{
            BufferedWriter b = new BufferedWriter(new FileWriter(file));
            for(String i:addword.keySet()){
                b.write(i + "," + addword.get(i));
                b.newLine();
                System.out.println(i);
            }
            b.flush();
            b.close();
        }catch(IOException e){
        System.out.println("error");} 
    }
    
    
    private String Freq(String Word,int docNumber) throws FileNotFoundException{
            Scanner ss = new Scanner(new FileReader("document.txt"));   
            int cou=1;
            String newword=ss.next();
            int f=0;
            
            while(cou<docNumber){
                if(isNumeric(newword)){
                    cou++;
                    newword=ss.next();
                }else{
                    newword=ss.next();
                }
           }
            
            while(ss.hasNext()){
                newword=ss.next();
               
                if(isNumeric(newword) && f==0){
                    return "no";
                }
                if(isNumeric(newword) && f!=0){
                    return docNumber+"->"+f;
                }
                if(newword.equals(Word)){
                    f++;newword=ss.next();
                }
                else{
                    newword=ss.next();
                }
                
            }
        return "1";
    }
}


