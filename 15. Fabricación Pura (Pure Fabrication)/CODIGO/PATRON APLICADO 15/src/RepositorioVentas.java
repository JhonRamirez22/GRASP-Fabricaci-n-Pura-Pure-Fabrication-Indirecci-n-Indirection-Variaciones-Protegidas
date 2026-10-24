import java.util.ArrayList;
import java.util.List;

// FABRICACIÓN PURA: no es un concepto del supermercado, pero se encarga de guardar ventas
public class RepositorioVentas {
    private List<Venta> ventas;

    public RepositorioVentas() {
        this.ventas = new ArrayList<>();
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVenta(List<Venta> venta) {
        this.ventas = venta;
    }

    public void guardar(Venta venta) {
        ventas.add(venta);
    }

    public int cantidad() {
        return ventas.size();
    }
}