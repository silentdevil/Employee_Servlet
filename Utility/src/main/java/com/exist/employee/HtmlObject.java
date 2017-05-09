package com.exist.employee;

public class HtmlObject {
	
	private String head;
	private String headOutput;

	private String title;
	private String titleOutput;

	private String body = "";
	private String bodyOutput;

	private String stringOutput;

	public String getHead() {
		return head;
	}

	public HtmlObject setHead(String head) {
		this.head = head;
		//headOutput = "" + head + ;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public HtmlObject setTitle(String title) {
		this.title = title;
		titleOutput = "<head>\n\t<title>" + title + "</title>\n</head>\n";
		return this;
	}

	public HtmlObject addBody(String input){
		body += input;
		bodyOutput = "<body>\n\t" + body + "</body>";
		return this;
	}

	public String getStringOutput(){
		stringOutput = "<html>\n" + titleOutput + bodyOutput + "</html>";
		return stringOutput;
	}

	public static void main(String[] args) {
		HtmlObject html = new HtmlObject();
		Table table = new Table();

		for(int i=1; i < 3; i++) {
			table.addRow();
			for(int j=1;j<3;j++) {
				table.addColumn((i * j) + "");
			}
		}

		html.setTitle("hello World")
			.addBody(table.getStringOutput());

		System.out.println(html.getStringOutput());
	}
}