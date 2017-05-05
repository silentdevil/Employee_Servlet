package com.exist.employee;

public class IndexViewImpl implements View {

	private Screen screen;

	public IndexViewImpl(Screen screen) {
		this.screen = screen;
	}

	public String publish() {
		return screen.show();
	}
}