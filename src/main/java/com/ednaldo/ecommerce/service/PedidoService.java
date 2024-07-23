package com.ednaldo.ecommerce.service;

import com.ednaldo.ecommerce.domain.dto.InfoItemPedidoDTO;
import com.ednaldo.ecommerce.domain.dto.InfoPedidoDTO;
import com.ednaldo.ecommerce.domain.dto.ItemPedidoDTO;
import com.ednaldo.ecommerce.domain.dto.PedidoDTO;
import com.ednaldo.ecommerce.domain.entity.Cliente;
import com.ednaldo.ecommerce.domain.entity.ItemPedido;
import com.ednaldo.ecommerce.domain.entity.Pedido;
import com.ednaldo.ecommerce.domain.entity.Produto;
import com.ednaldo.ecommerce.domain.repository.ClienteRepository;
import com.ednaldo.ecommerce.domain.repository.ItemPedidoRepository;
import com.ednaldo.ecommerce.domain.repository.PedidoRepository;
import com.ednaldo.ecommerce.domain.repository.ProdutoRepository;
import com.ednaldo.ecommerce.exception.ObjetoNotFoundException;
import com.ednaldo.ecommerce.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public Pedido insert(PedidoDTO pedidoDTO) {
        Long idCliente = pedidoDTO.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código do cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedido = converterItems(pedido, pedidoDTO.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedido);
        pedido.setItens(itemPedido);
        return pedido;
    }

    public List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar um pedido sem  items.");
        }
        return items.stream().map(dtoItem -> {
            Long idProduto = dtoItem.getProduto();
            Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new RegraNegocioException("Código do produto inválido: " + idProduto));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dtoItem.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }

    public InfoPedidoDTO obterPedidoCompleto(Long id) {
        return pedidoRepository.findByIdFetchItem(id)
                .map(p -> converte(p) )
                .orElseThrow(() -> new ObjetoNotFoundException("Pedido não econtrado."));
    }

    private InfoPedidoDTO converte(Pedido pedido) {
     return  InfoPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().toString())
                .nome(pedido.getCliente().getNome())
                .cpf(pedido.getCliente().getCpf())
                .total(pedido.getTotal())
                .infoItemPedidosDTO(converter(pedido.getItens()))
                .build();

    }

    public List<InfoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(itemPedido -> InfoItemPedidoDTO.builder()
                .descrição(itemPedido.getProduto().getDescricao())
                .precoUnitario(itemPedido.getProduto().getPreco())
                .quantidade(itemPedido.getQuantidade())
                .build()
        ).collect(Collectors.toList());

    }
}