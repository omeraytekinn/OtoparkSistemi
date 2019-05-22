package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller implements Initializable {
    @FXML
    private Pane enterOp;

    @FXML
    private Button exitBtn;
    @FXML
    private Button ent;

    @FXML
    private Pane backPane;
    
    @FXML
    private Pane infoPane;

    @FXML
    private Pane subsOp;

    @FXML
    private TextField exitTbox;
    
    @FXML
    private TextField enterTbox;

    @FXML
    private TextField subsTbox;
    @FXML
    private TextField cap;
    @FXML
    private TextField fee;

    @FXML
    private Text curVeh;

    @FXML
    private Pane subsPane;

    @FXML
    private Text timeLab;
    @FXML
    private Text hFee;
    @FXML
    private Text capT;

    @FXML
    private Pane exitPane;

    @FXML
    private Label enterMsg;

    @FXML
    private Pane enterPane;

    @FXML
    private Pane infOp;

    @FXML
    private Pane entPane;

    @FXML
    private Text dateLab;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label exitMsg;

    @FXML
    private Label subsMsg;

    @FXML
    private Text incDaily;

    @FXML
    private Text mainText;
    @FXML
    private Text hoFee;
    
    @FXML
    private Pane topPane;
    
    @FXML
    private Label enterr;

    @FXML
    private Autopark autopark;
    
    @FXML
    private CheckBox specialCheck;
    
    @FXML
    private DatePicker datePicker1;
    
    @FXML
    private DatePicker datePicker2;

    @FXML
    private TextArea infoText;
    
    @FXML
    private void goEnter(Event e) {
		enterPane.toFront();
    	mainText.setOpacity(0);
    	backPane.setOpacity(1.0);
    }

    @FXML
    public void goExit(Event e) {
		exitPane.toFront();
    	mainText.setOpacity(0);
    	backPane.setOpacity(1.0);
    }
    
    @FXML
    public void goSubs(Event e) {
		subsPane.toFront();
    	mainText.setOpacity(0);
    	backPane.setOpacity(1.0);
    }
    
    @FXML
    public void goInfo(Event e) {
		infoPane.toFront();
    	mainText.setOpacity(0);
    	infoText.setText(autopark.toString());
    	backPane.setOpacity(1.0);
    }
    
    
    @FXML
    public void goBack(Event e) {
    	gridPane.toFront();
    	mainText.setOpacity(1.0);
    	backPane.setOpacity(0);
		exitMsg.setOpacity(0);
		enterMsg.setOpacity(0);
		subsMsg.setOpacity(0);
		enterr.setOpacity(0);
		enterTbox.setText("");
		exitTbox.setText("");
		subsTbox.setText("");
    	datePicker1.setValue(LocalDate.now());
    	datePicker2.setValue(LocalDate.now());
    	hoFee.toFront();
    }
    
    @FXML
    public void goEnt(Event e) {
    	try {
        	hFee.setText(""+Double.parseDouble(fee.getText()));
        	capT.setText(""+Integer.parseInt(cap.getText()));
    	} catch (Exception e1) {
    		enterr.setOpacity(1.0);
    		fee.setText("");
    		cap.setText("");
		}
		autopark = new Autopark(Double.parseDouble(fee.getText()), Integer.parseInt(cap.getText()));
    	entPane.toBack();
    }
    
    public void printMsg(String msg, boolean err, int labelID) {
    	if(labelID == 1) { // Entering message
    		if(err)
    			enterMsg.setStyle("-fx-text-fill: #f00");
    		else
    			enterMsg.setStyle("-fx-text-fill: #0f0");
    		enterMsg.setText(msg);
    		enterMsg.setOpacity(1);
    	} else if(labelID == 2) { // Exiting message
    		if(err)
    			exitMsg.setStyle("-fx-text-fill: #f00");
    		else
    			exitMsg.setStyle("-fx-text-fill: #0f0");
    		exitMsg.setText(msg);
    		exitMsg.setOpacity(1);
    	} else if(labelID == 4) { // Subscribing message
    		if(err)
    			subsMsg.setStyle("-fx-text-fill: #f00");
    		else
    			subsMsg.setStyle("-fx-text-fill: #0f0");
    		subsMsg.setText(msg);
    		subsMsg.setOpacity(1);
    	}
    }

    @FXML
    public void vehicleEnter(Event e) {
    	if( enterTbox.getText().equals("") )
    		printMsg("Error: Empty Field!", true, 1);
    	else {
    		boolean res = autopark.vehicleEnters(enterTbox.getText(), Time.getCurTime(), specialCheck.isSelected());
    		if(res)
    			printMsg("Success: Vehicle Entered!", false, 1);
    		else
    			printMsg("Error: Vehicle Already Entered!", true, 1);
    	}
    	curVeh.setText(""+ autopark.getCurrentVehicle());
    }

    @FXML
    public void vehicleExit(Event e) {
    	if( exitTbox.getText().equals("") )
    		printMsg("Error: Empty Field!", true, 2);
    	else {
    		boolean res = autopark.vehicleExits(exitTbox.getText(), Time.getCurTime());
    		if(res)
    			printMsg("Success: Vehicle Exited!", false, 2);
    		else
    			printMsg("Error: Vehicle Was Not Here!", true, 2);
    			
    	}
    	incDaily.setText(""+autopark.getIncomeDaily());
    	curVeh.setText(""+ autopark.getCurrentVehicle());
    }
    
    @FXML
    public void addVehicle(Event e) {
    	if( subsTbox.getText().equals("") || datePicker1.getValue() == null || datePicker2.getValue() == null )
    		printMsg("Error: Empty Field!", true, 4);
    	else {
    		LocalDate date1 = datePicker1.getValue();
    		LocalDate date2 = datePicker2.getValue();
    		Date begin = new Date(date1.getDayOfMonth(), date1.getMonthValue(), date1.getYear());
    		Date end = new Date(date2.getDayOfMonth(), date2.getMonthValue(), date2.getYear());
    		Subscription sub = new Subscription(begin, end, subsTbox.getText());
    		if(begin.isBeforeThan(Date.getToday()) || end.isBeforeThan(begin))
    			printMsg("Error: Check The Dates!", true, 4);
    		else {
        		boolean res = autopark.addVehicle(sub.getVehicle());
        		if(res)
        			printMsg("Success: Vehicle Subscribed!", false, 4);
        		else
        			printMsg("Error: Vehicle Not Subscribed!", true, 4);
    		}
    			
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		timeLab.setText(Time.getCurTime().toString());
		dateLab.setText(Date.getToday().toString());
		incDaily.setText("0 $");
		curVeh.setText("0");
    	mainText.setOpacity(1.0);
    	backPane.setOpacity(0);
    	enterMsg.setOpacity(0);
    	exitMsg.setOpacity(0);
    	subsMsg.setOpacity(0);
    	datePicker1.setValue(LocalDate.now());
    	datePicker2.setValue(LocalDate.now());
	}


}