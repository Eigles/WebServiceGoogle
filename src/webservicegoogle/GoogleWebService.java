/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicegoogle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import shifu.core.tools.json.JSONException;
import shifu.core.tools.json.JSONObject;

/**
 *
 * @author zemouri.cdi01
 */
public class GoogleWebService {

    protected URLConnection urlConnection;
    protected InputStream httpStream;
    protected String login, password;
    protected String url,isbnRecuperer=null;
    protected ObjectBook objectBook;
    protected JSONObject object;

    public GoogleWebService(String login, String password) throws IOException {
        this.login = login;
        this.password = password;
        //ConfigurationProxy.setDefault(new ConfigurationProxy(this.login, this.password));
    }

    private void searchGoogle(String isbn) {
        try {
            url = "https://www.googleapis.com/books/v1/volumes?country=FR&q=isbn:".concat(isbnRecuperer);
            URL fileURL = new URL(url);
            urlConnection = fileURL.openConnection(); // open URL (HTTP query)
            httpStream = urlConnection.getInputStream();// Open data stream
        } catch (Exception ae) {
            System.out.println("impossible d'executer la requete");
            ae.printStackTrace();
        }
    }

    public static String inputStreamToString(InputStream is, Charset charset) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            builder.append(line);
        }
        return builder.toString();
    }

    public ObjectBook getResult(String isbn) throws IOException {
            isbnRecuperer  = isbn.replaceAll("-", "").replaceAll(" ", "").toString();
            if(isbnRecuperer.length() ==10 || isbnRecuperer.length() == 13)
            {
                searchGoogle(isbn);
                objectBook = new ObjectBook();
                JSONObject object = new JSONObject(inputStreamToString(httpStream, Charset.forName("utf-8")));
                System.out.println(object);
                JSONObject object0 = new JSONObject();
                if (object.getInt("totalItems") != 0) {
                    try {
                        object0 = object.getJSONArray("items").getJSONObject(0);
                        object0 = object0.getJSONObject("volumeInfo");
                    } catch (JSONException e) {
                        return null;
                    }
                    try {
                        if (!object0.getString("title").toString().isEmpty() && object0.getString("title") != null) {
                            objectBook.setTitre(object0.getString("title").toString());
                        } else {
                            objectBook.setTitre(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setTitre(" ");
                    }
                    try {
                        if (!object0.getJSONArray("authors").getString(0).toString().isEmpty() && object0.getJSONArray("authors") != null) {
                            objectBook.setAuteur(object0.getJSONArray("authors").getString(0).toString());
                        } else {
                            objectBook.setAuteur(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setAuteur(" ");
                    }
                    try {
                        if (!object0.getString("description").toString().isEmpty() && object0.getString("description") != null) {
                            objectBook.setResume(object0.getString("description").toString());
                        } else {
                            objectBook.setResume(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setResume(" ");
                    }
                    try {
                        if (!object0.getString("publishedDate").toString().isEmpty() && object0.getString("publishedDate") != null) {
                            objectBook.setDatePublication(object0.getString("publishedDate").toString());
                        } else {
                            objectBook.setTitre(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setTitre(" ");
                    }
                    try {
                        if (!object0.getString("publisher").toString().isEmpty() && object0.getString("publisher") != null) {
                            objectBook.setMaisonEdition(object0.getString("publisher").toString());
                        } else {
                            objectBook.setMaisonEdition(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setMaisonEdition(" ");
                    }
                    try {
                        if (!object0.getJSONArray("industryIdentifiers").getJSONObject(1).getString("identifier").toString().isEmpty() && object0.getJSONArray("industryIdentifiers") != null) {
                            objectBook.setIsbn13(object0.getJSONArray("industryIdentifiers").getJSONObject(1).getString("identifier").toString());
                        } else {
                            objectBook.setIsbn13(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setIsbn13(" ");
                    }
                    try {
                        if (!object0.getString("language").toString().isEmpty() && object0.getString("language") != null) {
                            objectBook.setLangue(object0.getString("language").toString());
                        } else {
                            objectBook.setLangue(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setLangue(" ");
                    }
                    try {
                        if ((int) (object0.getInt("pageCount")) != 0) {
                            objectBook.setNbPage((int) object0.getInt("pageCount"));
                        } else {
                            objectBook.setNbPage(0);
                        }
                    } catch (JSONException e) {
                        objectBook.setNbPage(0);
                    }
                    try {
                        if (!object0.getString("printType").toString().isEmpty() && object0.getString("printType") != null) {
                            objectBook.setType(object0.getString("printType").toString());
                        } else {
                            objectBook.setType(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setType(" ");
                    }
                    try {
                        if (!object0.getString("thumbnail").toString().isEmpty() && object0.getString("thumbnail") != null) {
                            objectBook.setImage(object0.getString("thumbnail").toString());
                        } else {
                            objectBook.setImage(" ");
                        }
                    } catch (JSONException e) {
                        objectBook.setImage(" ");
                    }
                    httpStream.close();
                    return objectBook;
                } else {
                    System.out.println("existe pas");
                    httpStream.close();
                    return null;
                }
            }
            else
            {
                return null;
            }
    }
}
