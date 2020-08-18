
package recallandpre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.Global.Infinity;

public class RecallAndPre {

   
    public static void main(String[] args) throws FileNotFoundException {
       Scanner file=new Scanner(new File("D:\\resultiddec.txt"));
       Scanner mesh=new Scanner(new File("D:\\new_mshh.txt"));
       List<String>r=new ArrayList<>();
       while(file.hasNextLine()){
        String t=file.nextLine();
        
        if(t.equals(" ")){//System.out.println("t");
            continue;}
        else {//System.out.println(t);
            r.add(t);}
       }
      
       List<List<Long>>result=new ArrayList<>();
       for(int i=0;i<r.size();i++){
           
       String[] ar = r.get(i).split(" ");
       
       List<Long>res=new ArrayList<>();
            for(int j=0;j<ar.length;j++){
                if(!ar[j].equals("")){
            long rq=Long.parseLong(ar[j]);
            res.add(rq);
            }}
            result.add(res);
       }
      // System.out.print(result.get(1));
      
       List<String>M=new ArrayList<>();
      while(mesh.hasNext()){
      String m=mesh.next();
      M.add(m);
      }
      List<List<Long>> rmesh=new ArrayList<>();
      List<Long>res1=new ArrayList<>();
      for(int i=0;i<M.size()-2;i+=2){
      //System.out.println("1 "+M.get(i)+"  2  "+M.get(i+2)+"  "+M.get(i+1));
      if(M.get(i).equals(M.get(i+2))){
      long mr=Long.parseLong(M.get(i+1));
      res1.add(mr);
      }else {
        long mr=Long.parseLong(M.get(i+1));
      res1.add(mr);  
      rmesh.add(res1);
      res1=new ArrayList<>();
      }
      }//System.out.println(rmesh.get(0)+"\n"+result.get(2));
      List<Double>avgpre=new ArrayList<>();
      List<Double>avgrecall=new ArrayList<>();
     double allrelativeQ1=rmesh.get(0).size();double rela=0;double avgp=0.0,avgr=0.0;
     for(int l=0;l<rmesh.size();l++){
     for(int i=0;i<result.get(l).size();i++){
         long re=result.get(l).get(i);
      for(int j=0;j<rmesh.get(l).size();j++){
          long m=rmesh.get(l).get(j);
      if(re==m){
      rela++;
      avgp+=rela/i+1;
      avgr+=rela/allrelativeQ1;
    //  System.out.println("p : "+rela/i+1+"r : "+rela/allrelativeQ1);
      }
      }
     
     }
     if(avgp!=Infinity){
      double ap=avgp/result.get(l).size();
     avgpre.add(ap); }
    
     double ar=avgr/result.get(l).size();
    avgrecall.add(ar);
     System.out.println("avgp : "+avgp/result.get(l).size()+"\t\tavgR : "+avgr/result.get(l).size());
    }
     double sumavp=0.0;
     for(int i=0;i<avgpre.size();i++){if(avgpre.get(i)!=Infinity)sumavp+=avgpre.get(i);}
     double sumavr=0.0;
     for(int i=0;i<avgrecall.size();i++){if(avgrecall.get(i)!=Infinity)sumavr+=avgrecall.get(i);}
     System.out.println("avg pres= "+sumavp/avgpre.size()+"\t\tavg recall= "+sumavr/avgrecall.size());
    
    }
    }
    

