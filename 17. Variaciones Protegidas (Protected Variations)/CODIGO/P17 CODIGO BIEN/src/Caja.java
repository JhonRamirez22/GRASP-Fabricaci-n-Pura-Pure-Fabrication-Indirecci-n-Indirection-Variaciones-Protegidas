// CON PATRÓN: la caja depende de la interfaz Notificador, no de un canal concreto
public class Caja {
    private Notificador notificador;

    public Caja(Notificador notificador) {
        this.notificador = notificador;
    }

    public Notificador getNotificador() {
        return notificador;
    }

    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    public void cobrar(double total, String destino) {
        System.out.println("Cobrando $" + total);
        notificador.enviar(destino, "Gracias por tu compra. Total: $" + total);
    }
}