package pojo;

public class ProductDetailsPojo {

	private String productName;
	private String description;
	private String unitPrice;
	private String quantity;
	private String size;
	private String color;
	private String totalPrice;
	private String shippingPrice;
	private String finalPrice;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(String totalShipping) {
		this.shippingPrice = totalShipping;
	}
	public String getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(String totalPriceWithShipping) {
		this.finalPrice = totalPriceWithShipping;
	}	
}