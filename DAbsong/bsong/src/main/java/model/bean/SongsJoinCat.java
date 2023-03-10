package model.bean;

public class SongsJoinCat {
	private int id;
	private String name;
	private String categories;
	private int count_view;
	private String picture;
	private int cid;
	private Cat category;
	public SongsJoinCat() {
		super();
	}
	
	public SongsJoinCat(int id, String name, String categories, int count_view, String picture, int cid, Cat category) {
		super();
		this.id = id;
		this.name = name;
		this.categories = categories;
		this.count_view = count_view;
		this.picture = picture;
		this.cid = cid;
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public Cat getCategory() {
		return category;
	}

	public void setCategory(Cat category) {
		this.category = category;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public int getCount_view() {
		return count_view;
	}
	public void setCount_view(int count_view) {
		this.count_view = count_view;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "SongsJoinCat [id=" + id + ", name=" + name + ", categories=" + categories + ", count_view=" + count_view
				+ ", picture=" + picture + ", cid=" + cid + "]";
	}
	
	
}
