package edu.tntech.csc2310;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CourseCatalog {

    private ArrayList<Course> db;
    private String catalogYear;
    private String subject;

    public String getCatalogYear() {
        return catalogYear;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<Course> getCourses(){
        return db;
    }


    /**
     * It get the course using the course number and save it in array
     */
    public Course getCourse(String number){
        Course result = null;
        for (Course c: db){
            if (c.getNumber().equalsIgnoreCase(number)){
                result = c;
                break;
            }
        }
        return result;
    }

    public String toString(){
        return this.db.toString();
    }

    /**
     * Fetch courses number in given catalog and return all the courses numbers
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static ArrayList<String> getCourseNumbers(String subject, String catalogYear){

        Document doc = null;
        ArrayList<String> list = new ArrayList();
        try {
            doc = Jsoup.connect("https://ttuss1.tntech.edu/PROD/bwckctlg.p_display_courses?sel_crse_strt=1000&sel_crse_end=4999&sel_subj=&sel_levl=&sel_schd=&sel_coll=&sel_divs=&sel_dept=&sel_attr="+"&term_in="+catalogYear+"&one_subj="+subject).get();
            Elements courseTitles = doc.select(".nttitle");
            for (Element title : courseTitles) {
                String line = title.text();
                Scanner scan = new Scanner(line);
                scan.useDelimiter(" ");
                scan.next();
                String crseNum = scan.next();
                list.add(crseNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }

    /**
     * Add the courses to db array then check if course is available in cache, if available then store that to the db array
     * if not available then scrap the course and store each course to db array.
     */
    public CourseCatalog(String subject, String catalogYear, Object IOException) {

        String subj = subject.trim().toUpperCase();
        Integer trm = Integer.parseInt(catalogYear.trim());

        this.catalogYear = trm.toString();
        this.subject = subj.toUpperCase();
        try {
            Gson gson = new Gson();
            String jsonfile = subj + "_" + catalogYear.trim() + ".json";
            File file = new File("src/main/resources/" + jsonfile);
            this.db = new ArrayList();
            if (file.createNewFile()) {
                ArrayList<String> list = CourseCatalog.getCourseNumbers(this.subject, this.catalogYear);
                if (list.size() > 0) {
                    for (String s : list) {
                        Course c = new Course(this.subject, s, this.catalogYear);
                        this.db.add(c);
                    }
                    String jsonstring = gson.toJson(db);
                    FileWriter out = new FileWriter(file);
                    out.write(jsonstring);
                    out.close();
                } else {
                    this.subject = null;
                    this.catalogYear = null;
                    this.db = null;
                    throw new CatalogNotFoundException("Catalog not found");
                }
            } else {
                FileReader in = new FileReader(file);
                JsonReader jsonfiles = gson.newJsonReader(in);
                Course[] data = gson.fromJson(jsonfiles, Course[].class);
                try {
                    Collections.addAll(this.db, data);
                } catch (NullPointerException e) {
                    this.catalogYear = null;
                    this.db = null;
                    this.subject = null;
                }
            }
        } catch (IOException | CatalogNotFoundException e) {
            e.printStackTrace();
        }
    }
}
