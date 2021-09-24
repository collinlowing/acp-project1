// Change to App.java and App class name later

class Main {
  public static void main(String[] args) {
    Camera cam = new Camera("Brand", "Model", 12, 100);
    System.out.println(cam);

    Camera cam1 = CameraFactory.createRandomCamera();
    System.out.println(cam1);
  }
}