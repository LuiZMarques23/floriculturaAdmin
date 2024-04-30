package com.stackmobile.floriculturavirtualadmin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.stackmobile.floriculturavirtualadmin.adapter.ProdutoAdapter
import com.stackmobile.floriculturavirtualadmin.databinding.FragmentProdutosBinding
import com.stackmobile.floriculturavirtualadmin.datasource.DB
import com.stackmobile.floriculturavirtualadmin.model.Produto


class ProdutosFragment : Fragment() {

    private lateinit var binding: FragmentProdutosBinding
    private lateinit var produtoAdapter: ProdutoAdapter
    private val listaProdutos: MutableList<Produto> = mutableListOf()
    private val db = DB()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProdutosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewProdutos = binding.recyclerViewProdutos
        recyclerViewProdutos.layoutManager = LinearLayoutManager(context)
        produtoAdapter = ProdutoAdapter(requireContext(),listaProdutos)
        recyclerViewProdutos.setHasFixedSize(true)
        recyclerViewProdutos.adapter = produtoAdapter
        db.getProdutos(listaProdutos,produtoAdapter)
    }

}