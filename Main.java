// Change to App.java and App class name later

class Main {
  public static void main(String[] args) {
    Camera cam = new Camera("Brand", "Model", 12, 100);
    System.out.println(cam);

    Camera cam1 = CameraFactory.createRandomCamera();
    System.out.println(cam1);

    CameraDB db = new CameraDB();
    db.storeCameras();

    System.out.println("All Cameras:");
    db.processQuery("SELECT * FROM Cameras");

    System.out.println("All Fuji Cameras:");
    db.processQuery("SELECT * FROM Cameras WHERE brand = 'Fuji'");

    System.out.println("All cameras with more than 24 mega pixels:");
    db.processQuery("SELECT * FROM Cameras WHERE megaPx > 24");
  }
}