/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idf;

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
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author hello
 */
public class resultid {

    /**
     * @param args the command line arguments
     */
  

    public static void main(String[] args) throws FileNotFoundException {
       FileOutputStream out=new FileOutputStream("D:\\resultiddec.txt"); 
        Scanner res=new Scanner(new File("D:\\newid.txt"));
        Scanner resd=new Scanner(new File("D:\\newcos.txt"));
        List<Long> ids=new ArrayList<>();int c=0;
        while(res.hasNextLong()){
        long s=res.nextLong();
        c++;
        ids.add(s);
        
        }System.out.println(ids.size());
        List<List<Double>>cos=new ArrayList<>();
        while(resd.hasNextLine()){
            String r=resd.nextLine();
            if(r.equals(""))
        continue;c++;
          String arst[]=r.split(" ");
          List<Double> d=new ArrayList<>();
          for(int i=0;i<arst.length;i++){
          double rd=Double.parseDouble(arst[i]);
          d.add(rd);
          }
          cos.add(d);
        }
        System.out.println(cos.size());
        for(int i=0;i<cos.size();i++){
        for(int l=0;l<cos.get(i).size();l++){
            if(cos.get(i).get(l)>0.05){
        System.out.print(ids.get(l)+"\t");
            PrintStream in=new PrintStream(out);
      in.print(ids.get(l)+" ");}
        }System.out.println();
         PrintStream in=new PrintStream(out);
        in.println();
        }
    }}
