package com.connorbrezinsky.msg.gui.main;

public class Main extends Application {
	  public static void main(String[] args) {
	        Application.launch(Main.class, args);
	    }
	    
	    @Override
	    public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
	        
	        stage.setTitle("msg");
	        stage.setScene(new Scene(root, 600, 400));
	        stage.setResizable(false);
	        stage.show();
	        
	    }
	    
}