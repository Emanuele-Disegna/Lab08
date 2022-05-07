package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	private Graph<Airport, DefaultEdge> grafo;
	
	public Graph<Airport, DefaultEdge> creaGrafo(int distanzaMinima) {
		grafo = new SimpleWeightedGraph<Airport, DefaultEdge>(DefaultEdge.class);
		
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		
		List<Airport> aeroporti = dao.loadAllAirports();
		Graphs.addAllVertices(grafo, aeroporti);
		
		for(Airport partenza : aeroporti) {
			for(Airport arrivo : aeroporti) {
				if(!grafo.containsEdge(arrivo, partenza)) {
					if(dao.isCollegati(partenza, arrivo)) {
						double media = dao.AverageBetweenTheseAirports(partenza, arrivo);
						if(media>distanzaMinima) {
							Graphs.addEdge(grafo, partenza, arrivo, media);
						}
					}
				}
				
			}
		}
		
		return grafo;
	}
	
}
