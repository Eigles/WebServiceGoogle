/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservicegoogle;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 *
 * @author zemouri.cdi01
 */
public class ConfigurationProxy extends Authenticator{
      private String username,password;
 
   public ConfigurationProxy(String username,String password) {
      this.username = username;
      this.password = password;
      System.setProperty("https.proxyHost", "10.0.0.254");	//l'adresse du proxy
      System.setProperty("https.proxyPort", "3128");	//le port du proxy
   }
 
   protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(username,password.toCharArray());
   }
}