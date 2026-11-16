import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class JavaFXApp extends Application {
    private static final Font BOLD = new Font("System Bold", 18.0f);
    private static final String PLUS = "+";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";

    protected final EventHandler<MouseEvent> mouseHandler = mouseEvent -> compute(((Button) mouseEvent.getSource()).getText());
    protected final EventHandler<KeyEvent> keyHandler = keyEvent -> compute(((Button) keyEvent.getSource()).getText());
    protected Pane rootPane;
    protected TextField txtResult;
    protected TextField txtNumber1;
    protected TextField txtNumber2;

    private int getNumberFromTextField(TextField textField) {
        return Integer.parseInt(textField.getText());
    }

    protected int computeAdd(int number1, int number2) {
        return number1 + number2;
    }

    protected int computeMultiply(int number1, int number2) {
        return number1 * number2;
    }

    protected int computeDivide(int number1, int number2) {
        return number1 / number2;
    }

    private void compute(String operator) {
        int result;
        int number1 = getNumberFromTextField(txtNumber1);
        int number2 = getNumberFromTextField(txtNumber2);

        switch(operator) {
            case PLUS -> result = computeAdd(number1, number2);
            case MULTIPLY -> result = computeMultiply(number1, number2);
            case DIVIDE -> result = computeDivide(number1, number2);
            default -> result = 0;
        }

        this.txtResult.setText(String.valueOf(result));
    }
    
    private void addLabel(String text, int width, int height, int x, int y) {
        Label label = new Label(text);
        label.setFocusTraversable(false);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font("System Bold", 18.0));
        this.rootPane.getChildren().add(label);
    }
    
    private TextField addTextField(String prompt, boolean isFocusTraversable, int width, int x, int y) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setFocusTraversable(isFocusTraversable);
        textField.setPrefWidth(width);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setFont(new Font("System Bold", 18.0));
        this.rootPane.getChildren().add(textField);

        return textField;
    }

    private void addButton(String label, int width, int x, int y) {
        Button button = new Button(label);
        button.setFocusTraversable(true);
        button.setPrefWidth(width);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setFont(BOLD);
        button.addEventHandler(MOUSE_CLICKED, this.mouseHandler);
        button.addEventHandler(KEY_PRESSED, this.keyHandler);
        this.rootPane.getChildren().add(button);
    }

    @Override
    public void start(Stage primaryStage) {
        this.rootPane = new Pane();
        this.rootPane.setMinSize(500,205);
        addLabel("Getal 1", 161, 71, 10, 0);
        this.txtNumber1 = addTextField("Voer hier het eerste getal in...", true, 310, 181, 15);
        addLabel("Getal 2", 161, 71, 10, 40);
        this.txtNumber2 = addTextField("Voer hier het tweede getal in...", true, 310, 181, 55);
        addButton(PLUS, 160, 10, 105);
        addButton(MULTIPLY, 160, 170, 105);
        addButton(DIVIDE, 160, 330, 105);
        this.txtResult = addTextField("Hier wordt het resultaat getoond...", false, 480, 10, 155);
        this.txtResult.setEditable(false);
        Scene startScene = new Scene(this.rootPane);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("OPT3 - Rekenmachine");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}