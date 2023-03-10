package model.bean;

public class Songs {
	private int id;
	private String name;
	private String preview_text;
	private String detail_text;
	private String date_create;
	private Cat category;
	private String picture;
	private int counter;
	private int cat_id;
	public Songs() {
		super();
	}
	public Songs(int id, String name, String preview_text, String detail_text, String date_create,Cat category, String picture,
			int counter, int cat_id) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.category = category;
		this.picture = picture;
		this.counter = counter;
		this.cat_id = cat_id;
	}
	public Cat getCategory() {
		return category;
	}
	public void setCategory(Cat category) {
		this.category = category;
	}
	public int getId() {
		return id;
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
	public String getPreview_text() {
		return preview_text;
	}
	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}
	public String getDetail_text() {
		return detail_text;
	}
	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}
	public String getDate_create() {
		return date_create;
	}
	public void setDate_create(String date_create) {
		this.date_create = date_create;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	@Override
	public String toString() {
		return "Songs [id=" + id + ", name=" + name + ", preview_text=" + preview_text + ", detail_text=" + detail_text
				+ ", date_create=" + date_create + ", picture=" + picture + ", counter=" + counter + ", cat_id="
				+ cat_id + "]";
	}
	
}
