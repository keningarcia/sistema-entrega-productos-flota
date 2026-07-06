package com.keningarcia.sistema_entrega_productos_flota.service;

import com.keningarcia.sistema_entrega_productos_flota.dto.PedidoDTO;
import com.keningarcia.sistema_entrega_productos_flota.dto.PedidoRequestDTO;
import com.keningarcia.sistema_entrega_productos_flota.entity.Cliente;
import com.keningarcia.sistema_entrega_productos_flota.entity.Pedido;
import com.keningarcia.sistema_entrega_productos_flota.entity.Ruta;
import com.keningarcia.sistema_entrega_productos_flota.repository.ClienteRepository;
import com.keningarcia.sistema_entrega_productos_flota.repository.PedidoRepository;
import com.keningarcia.sistema_entrega_productos_flota.repository.RutaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final RutaRepository rutaRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, RutaRepository rutaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.rutaRepository = rutaRepository;
    }

    public PedidoDTO create(PedidoRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Ruta ruta = rutaRepository.findById(request.getRutaId())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        Pedido pedido = new Pedido();
        pedido.setCodigo(request.getCodigo());
        pedido.setDescripcion(request.getDescripcion());
        pedido.setCantidad(request.getCantidad());
        pedido.setEstado("PENDIENTE");
        pedido.setCliente(cliente);
        pedido.setRuta(ruta);

        return toDTO(pedidoRepository.save(pedido));
    }

    public List<PedidoDTO> findByRuta(Long rutaId) {
        return pedidoRepository.findByRutaId(rutaId).stream().map(this::toDTO).toList();
    }

    public PedidoDTO actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!List.of("ENTREGADO", "CANCELADO").contains(nuevoEstado)) {
            throw new RuntimeException("Estado no valido. Use: ENTREGADO o CANCELADO");
        }

        pedido.setEstado(nuevoEstado);
        return toDTO(pedidoRepository.save(pedido));
    }

    private PedidoDTO toDTO(Pedido pedido) {
        return new PedidoDTO(
                pedido.getId(), pedido.getCodigo(), pedido.getDescripcion(),
                pedido.getCantidad(), pedido.getEstado(),
                pedido.getCliente().getId(), pedido.getCliente().getNombre(),
                pedido.getRuta().getId()
        );
    }
}
