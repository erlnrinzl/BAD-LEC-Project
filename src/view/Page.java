package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

abstract public class Page {

	
	public Page() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene render() {
		this.init();
		Pane layout = this.layout();
		this.setAction();
		
		return AppShell.render(layout);
	}
	
	abstract public void init();
	abstract public Pane layout();
	abstract public void setAction();
}
