/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Statistique {
    
   //Attribus
    private String typeGraph;
    private String variableEtudie;

    //Constructeur
    public Statistique(String typeGraph, String variableEtudie) {
        this.typeGraph = typeGraph;
        this.variableEtudie = variableEtudie;
    }

    //Getters & Setters
    public String getTypeGraph() {
        return typeGraph;
    }

    public void setTypeGraph(String typeGraph) {
        this.typeGraph = typeGraph;
    }

    public String getVariableEtudie() {
        return variableEtudie;
    }

    public void setVariableEtudie(String variableEtudie) {
        this.variableEtudie = variableEtudie;
    }

   //Equals

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Statistique other = (Statistique) obj;
        if (!Objects.equals(this.typeGraph, other.typeGraph)) {
            return false;
        }
        if (!Objects.equals(this.variableEtudie, other.variableEtudie)) {
            return false;
        }
        return true;
    }

    //Affichage
    @Override
    public String toString() {
        return "Statistique{" + "typeGraph=" + typeGraph + ", variableEtudie=" + variableEtudie + '}';
    }
    

}
