/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservicegoogle;

import java.io.IOException;

/**
 *
 * @author zemouri.cdi01
 */
public class WebServiceGoogle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {    
        GoogleWebService google = new GoogleWebService(null, null);
        google.getResult("2754004858");
    }
}
    