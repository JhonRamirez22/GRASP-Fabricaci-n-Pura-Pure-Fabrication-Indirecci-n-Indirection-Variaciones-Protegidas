// Canal nuevo: es una clase nueva; Caja y la interfaz no cambian
public class NotificadorWhatsApp implements Notificador {

    public NotificadorWhatsApp() {
    }

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[WhatsApp] a " + destino + ": " + mensaje);
    }
}