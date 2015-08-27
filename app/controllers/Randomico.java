package controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import models.QuestAlterAux;
public class Randomico {
	
	public static void main(String[] args) {
		int numero;  
        int[] num = new int[6];  
        Random r = new Random();  
  
        for(int i=0; i<num.length; i++){  
             numero = r.nextInt(100) + 1;  
             for(int j=0; j<num.length; j++){  
                   if(numero == num[j] && j != i){  
                         numero = r.nextInt(100) + 1;  
                   }else{  
                        num[i] = numero;  
                   }  
             }  
        }  
        //Apresentar na tela o resultado  
        for(int i=0; i<num.length; i++){  
             System.out.print(num[i]+"  ");  
           
        } 
         
        
        System.out.println();
        QuestAlterAux q =new QuestAlterAux();
        q.setEnunciado("Quanto � 1+1 :");
        QuestAlterAux q1 =new QuestAlterAux();
        q1.setEnunciado("teste 1 :");
        QuestAlterAux q2 =new QuestAlterAux();
        q2.setEnunciado("teste 2 :");
        QuestAlterAux q3 =new QuestAlterAux();
        q3.setEnunciado("teste 3 :");
        QuestAlterAux q4 =new QuestAlterAux();
        q4.setEnunciado("teste 4 :"); 
        QuestAlterAux q5 =new QuestAlterAux();
        q5.setEnunciado("teste 5 :"); 
        QuestAlterAux q6 =new QuestAlterAux();
        q6.setEnunciado("teste 6 :");
       
        
        ArrayList<QuestAlterAux> lis= new ArrayList<QuestAlterAux>();
        lis.add(q);
        lis.add(q1);
        lis.add(q2);
        lis.add(q3);
        lis.add(q4);
        lis.add(q5); 
        lis.add(q6);
        
        HashMap<Integer, String>teste = new HashMap<Integer, String>();
        for(int i=0; i<lis.size()-1;i++){
        	teste.put(new Integer(num[i]), lis.get(i).getEnunciado());
        	
        }
        
        
        
        
        for (Integer key : teste.keySet()) {
            
            //Capturamos o valor a partir da chave
            String value = teste.get(key);
            System.out.println(key + " = " + value);
            
     }

        
        
	}
	

}
