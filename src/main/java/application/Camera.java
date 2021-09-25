package application;

class Camera {
	private String brand;
	private String model;
	private int megaPx;
	private int price;

	public Camera() {
		brand = null;
		model = null;
		megaPx = 0;
		price = 0;
	}

	public Camera(String brand, String model, int megaPx, int price) {
		this.brand = brand;
		this.model = model;
		this.megaPx = megaPx;
		this.price = price;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	public void setMegaPx(int megaPx) {
		this.megaPx = megaPx;
	}

	public int getMegaPx() {
		return megaPx;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		String str = "";

		str += "Brand: " + brand + "\n";
		str += "Model: " + model + "\n";
		str += "MegaPx: " + megaPx + "\n";
		str += "Price: $" + price + "\n";

		return str;
	}
}