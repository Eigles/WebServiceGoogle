/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservicegoogle;

/**
 *
 * @author zemouri.cdi01
 */
public class ObjectBook extends Object{
        private String titre;
        private String auteur;
        private String auteurNom;
        private String auteurPrenom;
        private String datePublication;
        private String resume;
        private String isbn13;
        private String langue;
        private Integer nbPage;
        private String type;
        private String image;
        private String maisonEdition;
    public ObjectBook()
    {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
        setAuteurDetails();
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public Integer getNbPage() {
        return nbPage;
    }

    public void setNbPage(Integer nbPage) {
        this.nbPage = nbPage;
    }

    public String getType() {
        return type;
    }

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public void setMaisonEdition(String maisonEdition) {
        this.maisonEdition = maisonEdition;
    }

    public void setType(String type) {
        this.type = type;
        translatefr(type);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    private void setAuteurDetails()
    {
        auteurPrenom = "";
        String[] details;
        details = auteur.split(" ");
        for(int i=0;i<details.length-1;i++)
        {
            auteurPrenom = auteurPrenom.concat(details[i].toString());
        }
        auteurNom = details[details.length-1];
        System.out.println(auteurPrenom);
        System.out.println(auteurNom);
    }

    public String getAuteurNom() {
        return auteurNom;
    }

    public void setAuteurNom(String auteurNom) {
        this.auteurNom = auteurNom;
    }

    public String getAuteurPrenom() {
        return auteurPrenom;
    }

    public void setAuteurPrenom(String auteurPrenom) {
        this.auteurPrenom = auteurPrenom;
    }
    private void translatefr(String type)
    {
        if(type.equalsIgnoreCase("BOOK"))
        {
            setType("Livres");
        }
        if(type.equalsIgnoreCase("BOOKS"))
        {
            setType("Livres");
        }
    }
}
