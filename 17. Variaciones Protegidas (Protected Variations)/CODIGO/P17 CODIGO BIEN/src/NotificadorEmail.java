public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
    }

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[Correo] a " + destino + ": " + mensaje);
    }
}