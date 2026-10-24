public class Main {
    public static void main(String[] args) {
        Caja caja = new Caja();
        caja.cobrar(15.50, "EMAIL", "ana@mail.com");
        caja.cobrar(15.50, "SMS", "999888777");

        // Canal nuevo: sin modificar Notificador no funciona
        try {
            caja.cobrar(15.50, "WHATSAPP", "999888777");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}