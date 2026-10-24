// CON PATRÓN (Variación Protegida): punto de variación estable; cada canal implementa este contrato
public interface Notificador {
    void enviar(String destino, String mensaje);
}