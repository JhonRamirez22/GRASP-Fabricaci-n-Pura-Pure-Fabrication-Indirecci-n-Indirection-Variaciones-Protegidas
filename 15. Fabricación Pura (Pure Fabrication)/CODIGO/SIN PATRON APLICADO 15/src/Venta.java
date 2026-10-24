import java.util.ArrayList;
import java.util.List;

// SIN PATRÓN: la venta calcula su total y además sabe guardarse (persistencia dentro del dominio)
public class Venta {
    private String id;
    private List<Producto> productos;

    public Venta(String id) {
        this.id = id;
        this.productos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    // Responsabilidad de persistencia que no le corresponde a la venta
    public void guardar() {
        System.out.println("INSERT INTO ventas VALUES ('" + id + "', " + calcularTotal() + ")");
    }
}