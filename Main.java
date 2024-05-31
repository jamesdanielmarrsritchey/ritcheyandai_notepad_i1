/*
To compile and run this JavaFX application on a Debian-based system, you need to follow these steps:

1. Install OpenJDK 11 or higher and JavaFX. You can do this using the following commands:
   sudo apt update
   sudo apt install openjdk-17-jdk
   sudo apt install openjfx

2. Save this file with a .java extension, for example, `Main.java`.

3. Compile the Java file using the Java compiler (`javac`). You need to specify the path to the JavaFX SDK using the `--module-path` option, and you need to specify the modules your program uses with the `--add-modules` option. Run the following command:
   javac --module-path /usr/share/openjfx/lib --add-modules javafx.controls Main.java
   Replace `/usr/share/openjfx/lib` with the path to your `lib` directory in the JavaFX SDK, if it's different.

4. Run the program using the Java launcher, again specifying the module path and the modules:
   java --module-path /usr/share/openjfx/lib --add-modules javafx.controls Main
   Replace `/usr/share/openjfx/lib` with the path to your `lib` directory in the JavaFX SDK, if it's different.
*/

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Text");
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        MenuItem closeMenuItem = new MenuItem("Close Application");
        closeMenuItem.setOnAction(e -> Platform.exit());

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(saveMenuItem, closeMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        VBox vbox = new VBox(menuBar, textArea);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        vbox.setFillWidth(true);

        Scene scene = new Scene(vbox, 200, 100);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}