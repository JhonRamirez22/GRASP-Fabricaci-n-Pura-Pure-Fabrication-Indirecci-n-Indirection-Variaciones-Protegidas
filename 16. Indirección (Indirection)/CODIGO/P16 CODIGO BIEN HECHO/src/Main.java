public class Main {
    public static void main(String[] args) {
        Caja caja = new Caja(new Notificador());
        caja.cobrar(15.50, "ana@mail.com");
    }
}