package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller implements Initializable {
    @FXML
    private Label lab1;

    @FXML
    private Button btn;
/*
	@FXML
	public void setLabel(Event e) {
		/*Time asd = new Time(3,20);
		lab1.setText(asd.getMinute() + ":" + asd.getHour());*/
	/*}*/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lab1.setText("3:20");
		
	}


}