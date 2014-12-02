package com.mac.se3a04.taxime;

public class DrawerItem {

	private String itemName;
	private int imgResID;
	
	public DrawerItem(String itemName, int imgResID) {
		this.itemName = itemName;
		this.imgResID = imgResID;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getImgResID() {
		return imgResID;
	}
	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}
	
	
	
}
