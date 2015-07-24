package entity;

/**
 * project:com.example.android_day0701_venn_homework.entity
 * user:VennUser
 * date:2015/7/1
 */
public class Message {

	private String person;
	private String number;
	private String content;
	private long time;

	public Message() {
	}

	public Message(String content, String person, String number, long time) {
		this.content = content;
		this.person = person;
		this.number = number;
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String toString() {
		return "Message{" +
				"content='" + content + '\'' +
				", person='" + person + '\'' +
				", number='" + number + '\'' +
				", time=" + time +
				'}';
	}
}
