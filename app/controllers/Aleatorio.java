package controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import models.QuestAlterAux;
import models.Questao;
import models.QuestaoGerada;

public class Aleatorio {
	
	public static void main(String[] args) {
		QuestAlterAux q= new QuestAlterAux();
		QuestAlterAux q1= new QuestAlterAux();
		QuestAlterAux q2= new QuestAlterAux();
		QuestAlterAux q3= new QuestAlterAux();
		q.setEnunciado("Primeira");
		q1.setEnunciado("Segunda");
		q2.setEnunciado("Terceira");
		q3.setEnunciado("Quarta");
		List<QuestAlterAux> questoes = new LinkedList<QuestAlterAux>();
		questoes.add(q);
		questoes.add(q1);
		questoes.add(q2);
		questoes.add(q3);
		
		System.out.println(questoes);
		System.out.println(embaralharQuestoes(questoes));
	}

	public static Collection<QuestaoGerada> embaralharQuestoes(List<QuestAlterAux> questoes) {
		Random r = new Random();
		for (QuestAlterAux q : questoes) {
			q.setAuxiliar(r.nextInt()); 
		}
		
		Comparator<QuestAlterAux> comp = new Comparator<QuestAlterAux>() {
			@Override
			public int compare(QuestAlterAux q1, QuestAlterAux q2) {
				return q1.getAuxiliar().compareTo(q2.getAuxiliar());
			}
		};
		
		Collections.sort(questoes, comp);
		
		Collection<QuestaoGerada> questoesGeradas = new LinkedList<QuestaoGerada>();
		int i = 1;
		for (QuestAlterAux q : questoes) {
			QuestaoGerada qg = new QuestaoGerada(q);
			qg.ordem = i++;
			questoesGeradas.add(qg);
		}
		
		return questoesGeradas;
	}
}
