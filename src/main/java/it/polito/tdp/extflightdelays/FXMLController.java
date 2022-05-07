/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	//TODO
    	txtResult.clear();
    	String dist = distanzaMinima.getText();
    	int distNum;
    	
    	try {
    		distNum=Integer.parseInt(dist);
    	} catch (NumberFormatException e) {
    		txtResult.setText("Inserire una distanza numerica");
    		return;
    	}
    	
    	if(distNum<0) {
    		txtResult.setText("Inserire una distanza positiva");
    		return;
    	}
    	
    	Graph<Airport, DefaultEdge> grafo = model.creaGrafo(distNum);
    	
    	int numVertici = grafo.vertexSet().size();
    	int numArchi = grafo.edgeSet().size();
    	
    	txtResult.appendText("Il numero di vertici è : "+numVertici+"\n");
    	txtResult.appendText("Il numero di archi è : "+numArchi+"\n");
    	
    	txtResult.appendText(grafo.toString());
    	/*
    	for(DefaultEdge arco : grafo.edgeSet()) {
    		txtResult.appendText(arco+ " Peso: " + grafo.getEdgeWeight(arco)+ "\n");
    	}*/
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
