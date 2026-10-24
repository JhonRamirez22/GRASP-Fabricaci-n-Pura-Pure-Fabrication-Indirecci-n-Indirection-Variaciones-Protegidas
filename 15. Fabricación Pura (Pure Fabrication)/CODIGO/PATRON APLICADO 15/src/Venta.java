import java.util.ArrayList;
import java.util.List;

// CON PATRÓN: la venta solo conoce su dominio; guardarla es tarea del RepositorioVentas
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
}