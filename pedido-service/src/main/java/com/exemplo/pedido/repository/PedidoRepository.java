package com.exemplo.pedido.repository;

import com.exemplo.pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
