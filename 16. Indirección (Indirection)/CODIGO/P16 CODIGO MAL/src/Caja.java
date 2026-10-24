// SIN PATRÓN: la caja crea y configura el servicio de correo (conoce el host y el puerto)
public class Caja {

    public Caja() {
    }

    public void cobrar(double total, String email) {
        System.out.println("Cobrando $" + total);
        ServicioCorreo correo = new ServicioCorreo("smtp.supermercado.com", 587);
        correo.enviar(email, "Gracias por tu compra. Total: $" + total);
    }
}