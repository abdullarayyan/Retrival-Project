/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hello
 */
public class Result {
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
public static HashMap<String,LinkedList<String>> readHashFile(File indexfile){ // method return hashmap // read index file 
    HashMap<String, LinkedList<String>> hashmap = new HashMap<>();
    //File indexfile = new File("D:\\hashtfidf.txt");
    
    
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
          //  System.out.println( hashmap);
        }
       
        buffer.close();
    }catch(IOException e){}
    
    return hashmap;
}

    public static void main(String[] args) throws FileNotFoundException {
     
        
         HashMap<String,LinkedList<String>> hashtfidf=readHashFile(new File("D:\\hashtfidf.txt"));
         HashMap<String,LinkedList<String>> hashidf=readHashFile(new File("D:\\idfdoc.txt"));
       Scanner file=new Scanner(new File("D:\\newdec.txt"));
       Scanner query=new Scanner(new File("D:\\stemmQ.txt"));
        FileOutputStream out=new FileOutputStream("D:\\newcos.txt"); 
       List <List<String>>Q=new ArrayList<>();
      int cq=0;
     while(query.hasNextLine()&&cq<200){//read 200 q in arraylist Q
        
     String t=query.nextLine();
     String[] arrOfStr = t.split(" "); 
     List<String>wordq=new ArrayList<>(); 
  
     for(int i=0;i<arrOfStr.length;i++)
        wordq.add(arrOfStr[i]);
     Q.add(wordq);cq++;} 
     
     //
    
     List<List<String>>decs=new ArrayList<>();//read dec in arraylist decs
     int c=0,count=0;List<String>word=null;
      while(file.hasNext()){
       String txt=file.next();count++; 
       
       if(isNumeric(txt)){
           word=new ArrayList<>();
            decs.add(word);
          
           c++;
           
       }word.add(txt);
      }
      System.out.println(decs.size());
      //for(int i=0;i<decs.size();i++)System.out.println(decs.get(i));
      
      
      //calcolate summation tfdif in decuoment and save in arraylist sumd
       List<Double> sumd=new ArrayList<>();
      for(int i=0;i<decs.size();i++){
          double sumdec=0.0;
      for(int j=0;j<decs.get(i).size();j++){
          String s=decs.get(i).get(j);
          if(hashtfidf.get(s)!=null){
      for(int l=0;l<hashtfidf.get(s).size();l++){
      String st=hashtfidf.get(decs.get(i).get(j)).get(l);
      String arst[]=st.split("->");
      double numdec=Double.parseDouble(arst[0]);
      if(numdec==i+1){
         sumdec+=Double.parseDouble(arst[1])*Double.parseDouble(arst[1]);
      }
      }}
      }
      sumd.add(sumdec);
      }
      
     // System.out.println(sumd+"\n"+sumd.size());
      
      //calcolate summation tfdif to query and save in arraylist sumq
        List<Double> sumq=new ArrayList<>();
      for(int i=0;i<Q.size();i++){
          double sumqu=0.0;
      for(int j=0;j<Q.get(i).size();j++){
         String t=Q.get(i).get(j); 
          if(hashidf.get(t)!=null){
      String st=hashidf.get(t).element();
      String arst[]=st.split("->");
      sumqu+=Double.parseDouble(arst[1])*Double.parseDouble(arst[1]);
          }
      }
      sumq.add(sumqu);
      }
   
    //  System.out.print(Q.get(14)+"\n"+hashidf.get("docum"));
      
//  cal. summation tfidf(dec)*tfidf(q)
      
      
      List<List<Double>> sem=new ArrayList<>();//resule cosin all a and all dec
      
      for(int m=0;m<200;m++){//all query
          
      List<Double> cosa=new ArrayList<>();//result cosin one q and all dec
      
    for(int i=0;i<1000;i++){//all dec
     if(sumd.get(i)!=0)  {
     double sum=0;
    for(int j=0;j<Q.get(m).size();j++){ //word q
        
    //tfidf *idf to word if existing in dec && q
    if(hashtfidf.get(Q.get(m).get(j))!=null ){
    String s=Q.get(m).get(j);
    
    for(int l=0;l<hashtfidf.get(s).size();l++){//linkedlist ==> word from hashmap
    String t=hashtfidf.get(s).get(l);
    String ar[]=t.split("->");
    double numdec=Double.parseDouble(ar[0]);
    String id= hashidf.get(s).element();
    String aridf[]=id.split("->");
    double idf=Double.parseDouble(aridf[1]);  //tfidf q
    if(numdec==i+1){
    double tfidf=Double.parseDouble(ar[1]);//tfidf dec
    
    sum+=tfidf*idf;
    }
    }
    }
    }
    double x;
    if(sumq.get(m)!=0)
         x=sum/Math.sqrt(sumd.get(i)*sumq.get(m));
    else x=0.0;
    cosa.add(x);
   
   
   // System.out.println(sumd.get(i)+"\t"+sumq.get(3)+"\t"+Math.sqrt(sumd.get(i)*sumq.get(3))+"\t"+sum/Math.sqrt(sumd.get(i)*sumq.get(3)));
    }else cosa.add(0.0);
     
    }
  
    sem.add(cosa);//System.out.println(cosa.size());
    }
    
    for(int i=0;i<sem.size();i++){
        for(int j=0;j<sem.get(i).size();j++){
         PrintStream in=new PrintStream(out);
        in.print(sem.get(i).get(j)+" ");   
        }
        PrintStream in=new PrintStream(out);
        in.println("\n");
            System.out.println(i+"\t"+sem.get(i));}
    }
    
}
