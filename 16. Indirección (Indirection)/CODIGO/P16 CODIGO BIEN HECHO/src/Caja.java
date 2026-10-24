// La caja solo conoce al Notificador, no el servicio de correo
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

    public void cobrar(double total, String email) {
        System.out.println("Cobrando $" + total);
        notificador.notificarCompra(email, total);
    }
}