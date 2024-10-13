package org.enviarMails;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MailApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/emailicon.png"))); // Asegúrate de que la ruta sea correcta

        primaryStage.setTitle("Enviar Mails");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MailView.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Metodo para mostrar una alerta de éxito.
     *
     * @param controller El controlador de la ventana, para poder acceder a los campos.
     */
    public static void mostrarExitoEnvio(EnviarMailController controller) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje enviado");
        alert.setHeaderText(null);
        alert.setContentText("Mensaje enviado con éxito a: " + controller.getDestinatarioField().getText());
        alert.showAndWait();

        // Limpiar los campos después de un envío exitoso
        controller.getDestinatarioField().clear();
        controller.getAsuntoField().clear();
        controller.getMensajeArea().clear();
    }

    /**
     * Metodo para mostrar una alerta de error.
     *
     * @param errorMessage El mensaje de error a mostrar.
     */
    public static void mostrarErrorEnvio(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al enviar correo");
        alert.setHeaderText(null);
        alert.setContentText("No se pudo enviar el email. " + errorMessage);
        alert.showAndWait();
    }
}
