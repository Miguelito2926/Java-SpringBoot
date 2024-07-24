package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.domain.dto.InfoPedidoDTO;
import com.ednaldo.ecommerce.domain.dto.PedidoDTO;
import com.ednaldo.ecommerce.domain.dto.UpdateStatusPedidoDTO;
import com.ednaldo.ecommerce.domain.entity.Pedido;
import com.ednaldo.ecommerce.domain.enums.StatusPedido;
import com.ednaldo.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(value = "api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Long insertPedido(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.insert(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    private InfoPedidoDTO getById(@PathVariable Long id) {
       return pedidoService.obterPedidoCompleto(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public  void updateStatus(@RequestBody UpdateStatusPedidoDTO statusPedidoDTO, @PathVariable Long id) {
        String novoStatus = statusPedidoDTO.getNovoStatus().toUpperCase();
        pedidoService.updateStatus(id, StatusPedido.valueOf(novoStatus));
    }
}
