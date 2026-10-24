public class Main {
    public static void main(String[] args) {
        Producto leche = new Producto("Leche", 1.20);

        Venta venta = new Venta("V-001");
        venta.agregarProducto(leche);

        venta.guardar();
    }
}