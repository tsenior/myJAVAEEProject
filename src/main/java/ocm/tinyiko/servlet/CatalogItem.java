package ocm.tinyiko.servlet;

public class CatalogItem {
	
	private String name;
	private String manufacture;
	private String sku;

	public CatalogItem(String name, String manufacture, String sku) {
		super();
		this.name = name;
		this.manufacture = manufacture;
		this.sku = sku;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}

}
