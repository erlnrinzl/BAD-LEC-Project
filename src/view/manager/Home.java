package view.manager;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import view.Page;

public class Home extends Page {
	private VBox vLayout;
	private Label dummyMsg;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		vLayout = new VBox();
		
	}

	@Override
	public Pane layout() {
		// TODO Auto-generated method stub
		dummyMsg = new Label("you are on manager home page!");
		vLayout.getChildren().add(dummyMsg);
		
		return vLayout;
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		
	}

}
