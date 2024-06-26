package com.jonnycruz.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonnycruz.apirest.apirest.Entities.Producto;
import com.jonnycruz.apirest.apirest.Repositories.IProductoRepository;

// Todas las direcciones para consumir con un cliente 
@RestController
@RequestMapping("/productos") // A donde va llevar
public class ProductoController {

    @Autowired // Va al repositorio correspondiente que vamos a usar e inyecta el repositorio
    private IProductoRepository iProductoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return iProductoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id) {
        return iProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con id: " + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return iProductoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        Producto producto = iProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con id: " + id));
        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        return iProductoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Producto producto = iProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el id: " + id));
        iProductoRepository.delete(producto);
        return "El producto con id: " + id + " fue eliminado correctamente";
    }

}
