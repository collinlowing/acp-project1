import java.util.ArrayList;
import java.lang.reflect.*;

class CameraDB
{
  private int n = 10;
  private ArrayList<Camera> cameras = new ArrayList<Camera>();

  public CameraDB()
  {
    this(10);
  }

  public CameraDB(int n)
  {
    this.n = n;

    // Create n number of random cameras.
    for(int i = 0; i < n; i++)
    {
      Camera c = CameraFactory.createRandomCamera();
      cameras.add(c);
    }

    //Print out all cameras in ArrayList
    System.out.println("List of cameras:");
    for(int i = 0; i < cameras.size(); i++)
    {
      System.out.println(cameras.get(i).toString());
    }
  }

  public void storeCameras()
  {
    String CreateTableStatement = "";
    String InsertStatement = "";
    //use reflection to get names and data types of instance fields from Camera class
    // Write CREATE TABLE statement
    // Write INSERT statements
  }

  public void processQuery(String sqlQuery)
  {
    
  }
}