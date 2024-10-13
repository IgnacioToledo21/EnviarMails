package org.enviarMails;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.net.URL;
import java.util.ResourceBundle;

public class EnviarMailController implements Initializable {

    // Vinculamos los componentes de la interfaz gráfica a variables en el controlador utilizando @FXML
    @FXML
    private TextField servidorField;  // Campo para el nombre o IP del servidor SMTP

    @FXML
    private TextField puertoField;  // Campo para el puerto del servidor SMTP

    @FXML
    private CheckBox sslCheckBox;  // Checkbox para activar o desactivar la conexión SSL

    @FXML
    private TextField remitenteField;  // Campo para el email del remitente

    @FXML
    private PasswordField contrasenaField;  // Campo para la contraseña del remitente

    @FXML
    private TextField destinatarioField;  // Campo para el email del destinatario

    @FXML
    private TextField asuntoField;  // Campo para el asunto del correo

    @FXML
    private TextArea mensajeArea;  // Área de texto para el mensaje del correo

    @FXML
    private Button enviarButton;  // Botón para enviar el correo

    @FXML
    private Button vaciarButton;  // Botón para vaciar los campos del formulario

    @FXML
    private Button cerrarButton;  // Botón para cerrar la ventana

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Valores iniciales al cargar la interfaz
        puertoField.setText("465");  // Puerto predeterminado para SSL
        sslCheckBox.setSelected(true);  // Activar SSL por defecto
    }

    @FXML
    private void enviarCorreo() {
        try {
            // Se crea un objeto HtmlEmail (de commons-email) para manejar el envío del correo
            HtmlEmail email = new HtmlEmail();

            // Se establece el servidor SMTP y el puerto
            email.setHostName(servidorField.getText());
            email.setSmtpPort(Integer.parseInt(puertoField.getText()));

            // Se activa SSL si está seleccionado
            if (sslCheckBox.isSelected()) {
                email.setSSLOnConnect(true);
            }

            // Se establece la autenticación
            email.setAuthentication(remitenteField.getText(), contrasenaField.getText());

            // Se establece el remitente y el destinatario
            email.setFrom(remitenteField.getText());
            email.addTo(destinatarioField.getText());

            // Se establece el asunto y el cuerpo del mensaje
            email.setSubject(asuntoField.getText());
            email.setHtmlMsg(mensajeArea.getText());

            // Se envía el correo
            email.send();

            // Llama al metodo de éxito en MailApp
            MailApp.mostrarExitoEnvio(this);

        } catch (EmailException e) {
            // Llama al metodo de error en MailApp
            MailApp.mostrarErrorEnvio(e.getMessage());
            e.printStackTrace();  // Imprime el error en la consola
        }
    }

    @FXML
    private void vaciarCampos() {
        // Limpia todos los campos de texto y la casilla de verificación
        servidorField.clear();
        puertoField.clear();
        sslCheckBox.setSelected(false);
        remitenteField.clear();
        contrasenaField.clear();
        destinatarioField.clear();
        asuntoField.clear();
        mensajeArea.clear();
    }

    @FXML
    private void cerrarVentana() {
        // Cierra la ventana actual
        Stage stage = (Stage) cerrarButton.getScene().getWindow();
        stage.close();
    }

    // Métodos para obtener los campos (para limpiar después del envío)
    public TextField getDestinatarioField() {
        return destinatarioField;
    }

    public TextField getAsuntoField() {
        return asuntoField;
    }

    public TextArea getMensajeArea() {
        return mensajeArea;
    }
}
