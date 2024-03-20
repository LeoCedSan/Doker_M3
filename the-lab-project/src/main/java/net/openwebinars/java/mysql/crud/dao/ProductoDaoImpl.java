package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Producto;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl implements ProductoDao {

    private static ProductoDaoImpl instance;

    static {
        instance = new ProductoDaoImpl();
    }

    private ProductoDaoImpl() {}

    public static ProductoDaoImpl getInstance() {
        return instance;
    }

    @Override
    public int add(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
        int result;

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, producto.getNombre());
            pstm.setString(2, producto.getDescripcion());
            pstm.setDouble(3, producto.getPrecio());
            pstm.setInt(4, producto.getStock());

            result = pstm.executeUpdate();
        }

        return result;
    }

    @Override
    public Producto getById(int id) throws SQLException {
        Producto producto = null;

        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);

            try(ResultSet rs = pstm.executeQuery()) {

                while(rs.next()) {
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setStock(rs.getInt("stock"));
                }
            }
        }

        return producto;
    }

    @Override
    public List<Producto> getAll() throws SQLException {
        String sql = "SELECT * FROM producto";
        List<Producto> productos = new ArrayList<>();

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery()) {

            while(rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

                productos.add(producto);
            }
        }

        return productos;
    }

    @Override
    public int update(Producto producto) throws SQLException {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id_producto = ?";
        int result;

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, producto.getNombre());
            pstm.setString(2, producto.getDescripcion());
            pstm.setDouble(3, producto.getPrecio());
            pstm.setInt(4, producto.getStock());
            pstm.setInt(5, producto.getIdProducto());

            result = pstm.executeUpdate();
        }

        return result;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        }
    }
}
