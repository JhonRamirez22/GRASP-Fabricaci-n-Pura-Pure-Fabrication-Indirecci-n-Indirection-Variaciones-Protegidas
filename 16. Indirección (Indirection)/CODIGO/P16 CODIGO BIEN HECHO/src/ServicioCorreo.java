public class ServicioCorreo {
    private String host;
    private int puerto;

    public ServicioCorreo(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void enviar(String destino, String mensaje) {
        System.out.println("[SMTP " + host + ":" + puerto + "] a " + destino + ": " + mensaje);
    }
}