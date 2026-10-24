// CON PATRÓN (Indirección): intermediario que crea y oculta el servicio de correo
public class Notificador {
    private ServicioCorreo correo;

    public Notificador() {
        this.correo = new ServicioCorreo("smtp.supermercado.com", 587);
    }

    public ServicioCorreo getCorreo() {
        return correo;
    }

    public void setCorreo(ServicioCorreo correo) {
        this.correo = correo;
    }

    public void notificarCompra(String email, double total) {
        correo.enviar(email, "Gracias por tu compra. Total: $" + total);
    }
}