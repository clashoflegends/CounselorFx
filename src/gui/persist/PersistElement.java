/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.persist;



/**
 *
 * @author serguei
 */


public interface PersistElement {
    
   public String getName();
   public void loadStates(String args);       
   public String getState();
  
}
