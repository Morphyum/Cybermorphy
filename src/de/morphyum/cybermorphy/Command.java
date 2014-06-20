package de.morphyum.cybermorphy;

public class Command {
	private String head;
	private String body;
	
	public Command(String head, String body){
		this.head = head;
		this.body = body;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
