
package irfinalproject;
import java.io.*;
import java.util.*;

public class tfidfAndidf {
 public int numberOfDoc=8770;

    
public HashMap<String,LinkedList<String>> readHashFile(){ // method return hashmap // read index file 
    HashMap<String, LinkedList<String>> hashmap = new HashMap<>();
    File indexfile = new File("newhashSave.txt");
    
    try{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(indexfile))); //read from file
        String line;
        while((line = buffer.readLine()) != null){ // read line by line from index 
            LinkedList<String> list =new LinkedList<>(); // 
            String s="";
            String[] array = line.split("[,]"); // spilt each word with its linkedlist
            String word = array[0].replaceAll(" ", ""); // p = words
            for(int i=1;i<array.length;i++){
                s=array[i].replaceAll("[^a-zA-Z0-9-->\\s+]", "");  // each docid -> tf
                list.add(s);
                
            }
            hashmap.put(word,list); // add word and its linkedlist to the map
            //hashmapfree.put(word,list);
        }
       
        buffer.close();
    }catch(IOException e){}
    
    return hashmap;
}


public HashMap<String,String> calculateIdf(HashMap<String,LinkedList<String>> hashmap){
    HashMap<String,String> EN= new HashMap<String,String>();
    
    for(Map.Entry<String, LinkedList<String>> entry : hashmap.entrySet()) {
       
        double idf=((Math.log(numberOfDoc/entry.getValue().size()))/Math.log(2.0))+1; // get size of linkedlist as df
      //  LinkedList<String> val=entry.getValue();
      entry.getValue().add("idf->"+idf); // add idf to the linkedlist
      
       String key= entry.getKey();
        EN.put(key, "idf->"+idf);
       
    }
    return EN;
}
    
public  HashMap<String,String> calculateIdfTf(HashMap<String,LinkedList<String>> hashmap){ 
        HashMap<String,String> EN= new HashMap<String,String>();

    
    for(Map.Entry<String, LinkedList<String>> entry : hashmap.entrySet()) {
        
        String idf= entry.getValue().get(entry.getValue().size()-1); // read idf from linkedlist idf-> value         
        String arr[]=idf.split("->");  // split idf -> value by ->
        double Idf=Double.parseDouble(arr[1]); // mostIdf = values of idf
       
        for(int i=0;i<entry.getValue().size()-1;i++){
            String v=entry.getValue().get(i); // read from linkedlist // docid -> tf
            String ar[]=v.split("->"); // ar= docid
            
            double Tf=Double.parseDouble(ar[1]); //mosttf=tf from linkedlist
            double IdfTf=Idf*Tf;      
            String r=String.valueOf(IdfTf); 
              entry.getValue().set(i,ar[0]+"->"+r); 
            String n= entry.getValue().set(i,ar[0]+"->"+r); 
           // System.out.println(n);
             String key= entry.getKey();
              EN.put(key, "idftf->"+IdfTf);

        }
        
    }
    return EN;
}
    
public void Start(ArrayList<String> Stop){ // start program
    HashMap<String,LinkedList<String>> hashmap=readHashFile();
    
    HashMap<String,String>ttt=  calculateIdf(hashmap);

  // System.out.println(ttt);
    
        HashMap<String,String>tt= calculateIdfTf(hashmap);

  // System.out.println(tt);
   
     File file=new File("hashtfidf.txt");
      try{
            BufferedWriter b = new BufferedWriter(new FileWriter(file));
             for(Map.Entry<String, LinkedList<String>> entry : hashmap.entrySet()) {
                 LinkedList<String> link=entry.getValue();
                 link.remove(entry.getValue().size()-1); //to write tfidf for each word
                b.write(entry.getKey() + "," + link);
                b.newLine();
            }
            b.flush();
            b.close();
        }catch(IOException e){} 
      File file1=new File("idfdoc.txt");
      try{
            BufferedWriter b2 = new BufferedWriter(new FileWriter(file1));
             for(Map.Entry<String, LinkedList<String>> entry2 : hashmap.entrySet()) {
                 LinkedList<String> link1=entry2.getValue();
                 
                // link.remove(entry.getValue().size()-1); //to write tfidf for each word
                b2.write(entry2.getKey() + "," + link1.get( link1.size()-1));
                b2.newLine();
            }
            b2.flush();
            b2.close();
        }catch(IOException e){} 
      
      
      
      
}

}



  

