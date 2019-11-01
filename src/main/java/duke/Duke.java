package duke;

import command.Command;
import javafx.scene.control.Label;
import parser.Parser;
import storage.Storage;
import task.Originator;
import task.TaskList;
import ui.Ui;
import exception.DukeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *Represents the creation of the chat-bot.
 * */
public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creation of variables such as storage,
     * task-list
     * and ui
     * which are necessary to a task manager.
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
  /**
   * Pre-loading of any existing saving,
   * otherwise start new file.
   * */
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage("C:\\NUS\\test.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showToUser("☹ OOPS!!! Problem reading file. Starting with an empty task list");
            tasks = new TaskList();
        }
    }
    /**
     * Collects the input of the user to parse to respective command list,
     * to perform the respective tasks.
     * */
    public void run() throws IOException{
        List<Originator.Memento> savedStates = new ArrayList<Originator.Memento>();
        Originator originator = new Originator();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser.parse(fullCommand, savedStates);
                Command c = Parser.parse(fullCommand, savedStates);
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                c.execute(tasks, ui, storage);
                isExit = c.isExit;

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.saveNow(storage,tasks.getTaskList());
        ui.printBye();
    }

    /**
    * Runs the text according to the directory stated.
    *  */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Gudetama: " + input;
    }


     @Override
    public void start(Stage stage) {
        //Step 1. Formatting the window to look as expected.
         scrollPane = new ScrollPane();
         dialogContainer = new VBox();
         scrollPane.setContent(dialogContainer);

         userInput = new TextField();
         sendButton = new Button("Send");

         AnchorPane mainLayout = new AnchorPane();
         mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

         scene = new Scene(mainLayout);

         stage.setScene(scene);
         stage.show();
        //...

        //Step 2. Formatting the window to look as expected

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

         sendButton.setOnMouseClicked((event) -> {
             dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
             userInput.clear();
         });

         userInput.setOnAction((event) -> {
             dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
             userInput.clear();
         });
         //Scroll down to the end every time dialogContainer's height changes.
         dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

         //Part 3. Add functionality to handle user input.
         sendButton.setOnMouseClicked((event) -> {
             handleUserInput();
         });

         userInput.setOnAction((event) -> {
             handleUserInput();
         });
     }


}