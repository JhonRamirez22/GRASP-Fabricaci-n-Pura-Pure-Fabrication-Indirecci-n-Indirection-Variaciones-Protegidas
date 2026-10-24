public class NotificadorSMS implements Notificador {

    public NotificadorSMS() {
    }

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[SMS] a " + destino + ": " + mensaje);
    }
}