import java.util.Random;

class CameraFactory
{
  public static int megaPxMin = 10;
  public static int megaPxMax = 50;
  public static int priceMin = 90;
  public static int priceMax = 6000;

  public static String[] brands = {"Nikon", "Canon", "Sony", "Fuji", "Olympus"};

  public static String[] models = {"DSLR", "Mirrorless", "RangeFinder", "PointAndShoot"};

  public static Camera createRandomCamera()
  {
    Camera cam;

    Random rand = new Random();

    String brand = brands[rand.nextInt(5)];

    String model = models[rand.nextInt(4)];

    int megaPx = (int) Math.floor(Math.random() * (megaPxMax - megaPxMin + 1) + megaPxMin);

    int price = (int) Math.floor(Math.random() * (priceMax - priceMin + 1) + priceMin);
    
    cam = new Camera(brand, model, megaPx, price);

    return cam;
  }
}