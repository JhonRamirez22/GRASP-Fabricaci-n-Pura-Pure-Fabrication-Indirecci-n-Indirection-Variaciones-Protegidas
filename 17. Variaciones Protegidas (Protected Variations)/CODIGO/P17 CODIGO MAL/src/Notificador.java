// SIN PATRÓN: un solo método decide el canal con if/else; cada canal nuevo obliga a modificar la clase
public class Notificador {

    public Notificador() {
    }

    public void notificar(String canal, String destino, String mensaje) {
        if (canal.equals("EMAIL")) {
            System.out.println("[Correo] a " + destino + ": " + mensaje);
        } else if (canal.equals("SMS")) {
            System.out.println("[SMS] a " + destino + ": " + mensaje);
        } else {
            throw new IllegalArgumentException("Canal no soportado: " + canal);
        }
    }
}