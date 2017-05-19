package StudentMessageSystem;


/*
 * 学生信息类
 */

public class Student {
	private String id;
	private String name;
	private String sex;
	private int age;
	private int chinese;
	private int math;
	private int english;
	private int rank;

	
	public Student(String id, String name, String sex, int age, int chinese, int math, int english,
			int rank) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
		this.rank = rank;
	}
	
	public Student(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getChinese() {
		return chinese;
	}
	public void setChinese(int chinese) {
		this.chinese = chinese;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
}
