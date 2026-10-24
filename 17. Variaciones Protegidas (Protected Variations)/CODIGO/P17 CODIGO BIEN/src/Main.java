public class Main {
    public static void main(String[] args) {
        Caja cajaEmail = new Caja(new NotificadorEmail());
        cajaEmail.cobrar(15.50, "ana@mail.com");

        Caja cajaSms = new Caja(new NotificadorSMS());
        cajaSms.cobrar(15.50, "999888777");

        // Canal nuevo: solo se crea una clase nueva; Caja no cambia
        Caja cajaWhatsApp = new Caja(new NotificadorWhatsApp());
        cajaWhatsApp.cobrar(15.50, "999888777");
    }
}