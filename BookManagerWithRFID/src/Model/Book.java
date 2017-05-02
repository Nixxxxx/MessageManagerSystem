package Model;

import java.util.Date;

public class Book {
	
	private String id;
	private String bookName;
	private String press;
	private String sex;
	private String author;
	private float price;
	private String bookDesc;
	private int bookTypeId;
	private int conditions;
	private String borrower;
	private Date date;
	
	public Book(){
		super();
	}
	
	
	public Book(String id) {
		super();
		this.id = id;
	}

	


	public Book(String id, String borrower, Date date) {
		super();
		this.id = id;
		this.borrower = borrower;
		this.date = date;
	}


	public Book(String bookName, String author, int bookTypeId) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.bookTypeId = bookTypeId;
	}



	public Book(String id,String bookName, String press, String sex, String author, float price, String bookDesc,
			int bookTypeId) {
		super();
		this.id=id;
		this.bookName = bookName;
		this.press = press;
		this.sex = sex;
		this.author = author;
		this.price = price;
		this.bookDesc = bookDesc;
		this.bookTypeId = bookTypeId;
	}






	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(int bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	public int getCondition() {
		return conditions;
	}
	public void setCondition(int condition) {
		this.conditions = condition;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	
	
}
