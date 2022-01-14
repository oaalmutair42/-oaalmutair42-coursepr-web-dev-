package edu.tntech.csc2310;
/**
 * – thrown when scraping is successful but no data is
 * found for a given course request
 */
public class CourseNotFoundException  extends Exception
{
    public CourseNotFoundException ()
    {
        super();
    }
    public CourseNotFoundException (String str)
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