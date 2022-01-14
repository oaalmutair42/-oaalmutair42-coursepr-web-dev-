package edu.tntech.csc2310;
/**
 * thrown when scraping is successful but no data is
 * found for a given catalog request
 */
public class CatalogNotFoundException extends Throwable {

        public CatalogNotFoundException ()
        {
            super();
        }


        public CatalogNotFoundException (String str)
        {
            super(str);
        }


        public String getMessage(){
            return super.getMessage();
        }


        public String toString(){
            return super.toString();
        }
    }

