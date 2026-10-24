// SIN PATRÓN: la caja depende de la clase concreta Notificador y de códigos de texto para el canal
public class Caja {
    private Notificador notificador;

    public Caja() {
        this.notificador = new Notificador();
    }

    public Notificador getNotificador() {
        return notificador;
    }

    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    public void cobrar(double total, String canal, String destino) {
        System.out.println("Cobrando $" + total);
        notificador.notificar(canal, destino, "Gracias por tu compra. Total: $" + total);
    }
}