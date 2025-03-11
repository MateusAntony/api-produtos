package com.example.api_produtos.service;


import com.example.api_produtos.exceptions.RecursoNaoEncontradoException;
import com.example.api_produtos.model.Produto;
import com.example.api_produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository= produtoRepository;
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com Id " + id + " não encontrado"));
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id){
        if(!produtoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Produto com Id " + id + " não encontrado");
        }

        produtoRepository.deleteById(id);
    }

}
