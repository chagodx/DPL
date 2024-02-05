import java.util.ArrayList;
import java.util.List;

// Clases principales para representar entidades del sistema

/** Representa un producto en el sistema de gestión de ventas. */
class Producto {
    private final int id;
    private final String nombre;
    private final double precio;

    /** Crea un nuevo Producto con el ID, nombre y precio especificados.
     * @param id El ID del producto.
     * @param nombre El nombre del producto.
     * @param precio El precio del producto.
     */
    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y setters

    /** Obtiene el ID del producto.
     * @return El ID del producto.
     */
    public int getId() {
        return id;
    }

    /** Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /** Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }
}

/** Representa un cliente en el sistema de gestión de ventas. */
class Cliente {
    private final int id;
    private final String nombre;
    private final String direccion;

    /** Crea un nuevo Cliente con el ID, nombre y dirección especificados.
     * @param id El ID del cliente.
     * @param nombre El nombre del cliente.
     * @param direccion La dirección del cliente.
     */
    public Cliente(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y setters

    /** Obtiene el ID del cliente.
     * @return El ID del cliente.
     */
    public int getId() {
        return id;
    }

    /** Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /** Obtiene la dirección del cliente.
     * @return La dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }
}

/** Representa una venta en el sistema de gestión de ventas. */
class Venta {
    private final int id;
    private final Cliente cliente;
    private final List<Producto> productos;
    private double total;

    /** Crea una nueva Venta con el ID y cliente especificados.
     * @param id El ID de la venta.
     * @param cliente El cliente asociado a la venta.
     */
    public Venta(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    /** Agrega un producto a la venta y actualiza el total.
     * @param producto El producto a agregar a la venta.
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        total += producto.getPrecio();
    }

    /** Obtiene el ID de la venta.
     * @return El ID de la venta.
     */
    public int getId() {
        return id;
    }

    /** Obtiene el cliente asociado a la venta.
     * @return El cliente asociado a la venta.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /** Obtiene el total de la venta.
     * @return El total de la venta.
     */
    public double getTotal() {
        return total;
    }
}

/** Clase principal de la aplicación de gestión de ventas. */
public class SistemaVentas {
    private final List<Cliente> clientes;
    private final List<Producto> inventario;
    private final List<Venta> ventas;
    private int proximoIdVenta;

    /** Crea un nuevo SistemaVentas inicializando las listas de clientes, inventario y ventas.
     * El contador de ID de venta se inicializa en 1.
     */
    public SistemaVentas() {
        this.clientes = new ArrayList<>();
        this.inventario = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.proximoIdVenta = 1;
    }

    /** Agrega un cliente a la lista de clientes del sistema.
     * @param cliente El cliente a agregar.
     */
    public void agregarCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
        }
    }

    /** Agrega un producto al inventario del sistema.
     * @param producto El producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        if (producto != null) {
            inventario.add(producto);
        }
    }

    /** Realiza una venta con el cliente y los productos especificados.
     * @param idCliente El ID del cliente.
     * @param idsProductos La lista de IDs de los productos a vender.
     */
    public void realizarVenta(int idCliente, List<Integer> idsProductos) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Venta venta = new Venta(proximoIdVenta++, cliente);

        for (int idProducto : idsProductos) {
            Producto producto = buscarProductoPorId(idProducto);
            if (producto != null) {
                venta.agregarProducto(producto);
            } else {
                System.out.println("Producto con ID " + idProducto + " no encontrado.");
            }
        }

        ventas.add(venta);
        System.out.println("Venta realizada con éxito. Total: $" + venta.getTotal());
    }

    /** Busca un cliente por su ID en la lista de clientes.
     * @param idCliente El ID del cliente a buscar.
     * @return El cliente encontrado o null si no se encuentra.
     */
    private Cliente buscarClientePorId(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    /** Busca un producto por su ID en el inventario.
     * @param idProducto El ID del producto a buscar.
     * @return El producto encontrado o null si no se encuentra.
     */
    private Producto buscarProductoPorId(int idProducto) {
        for (Producto producto : inventario) {
            if (producto.getId() == idProducto) {
                return producto;
            }
        }
        return null;
    }

    // Otros métodos relacionados con la gestión del sistema
}
