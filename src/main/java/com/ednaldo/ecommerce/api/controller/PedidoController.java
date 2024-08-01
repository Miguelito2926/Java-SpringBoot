package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.domain.dto.InfoPedidoDTO;
import com.ednaldo.ecommerce.domain.dto.PedidoDTO;
import com.ednaldo.ecommerce.domain.dto.UpdateStatusPedidoDTO;
import com.ednaldo.ecommerce.domain.entity.Pedido;
import com.ednaldo.ecommerce.domain.enums.StatusPedido;
import com.ednaldo.ecommerce.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/pedidos")
@Api("Api Pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Criar um novo pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido criado com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public Long insertPedido(@RequestBody @ApiParam("Dados do novo pedido") @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.insert(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um pedido por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pedido encontrado."),
            @ApiResponse(code = 404, message = "Pedido não encontrado para o ID informado.")
    })
    public InfoPedidoDTO getById(@ApiParam("ID do pedido") @PathVariable Long id) {
        return pedidoService.obterPedidoCompleto(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Atualizar o status de um pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Status do pedido atualizado com sucesso."),
            @ApiResponse(code = 404, message = "Pedido não encontrado para o ID informado."),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public void updateStatus(@RequestBody @ApiParam("Novo status do pedido") UpdateStatusPedidoDTO statusPedidoDTO,
                             @ApiParam("ID do pedido") @PathVariable Long id) {
        String novoStatus = statusPedidoDTO.getNovoStatus().toUpperCase();
        pedidoService.updateStatus(id, StatusPedido.valueOf(novoStatus));
    }
}
