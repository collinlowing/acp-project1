package application;

class App {
	public static void main(String[] args) {

		CameraDB db = new CameraDB();
		db.storeCameras();

		System.out.println("All Cameras:");
		db.processQuery("SELECT * FROM Camera");

		System.out.println("\nAll Fuji Cameras:");
		db.processQuery("SELECT * FROM Camera WHERE brand = 'Fuji'");

		System.out.println("\nAll cameras with more than 24 mega pixels:");
		db.processQuery("SELECT * FROM Camera WHERE megaPx > 24");
	}
}