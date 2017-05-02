package Model;

public class BookType {

	private int id;               //���
	private String bookTypeName;	  //ͼ������
	private String bookTypeDesc;  //ͼ����������
	
	public BookType(){
		super();
	}
	
	
	
	public BookType(int id, String bookTypeName, String bookTypeDesc) {
		super();
		this.id = id;
		this.bookTypeName = bookTypeName;
		this.bookTypeDesc = bookTypeDesc;
	}



	public BookType(String bookType, String bookTypeDesc) {
		super();
		this.bookTypeName = bookType;
		this.bookTypeDesc = bookTypeDesc;
	}
	
	
	@Override
	public String toString() {
		return bookTypeName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBooktype() {
		return bookTypeName;
	}
	public void setBooktype(String booktype) {
		this.bookTypeName = booktype;
	}
	public String getBooktypedesc() {
		return bookTypeDesc;
	}
	public void setBooktypedesc(String booktypedesc) {
		this.bookTypeDesc = booktypedesc;
	}
}
