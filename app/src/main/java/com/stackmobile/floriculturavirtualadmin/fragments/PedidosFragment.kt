package com.stackmobile.floriculturavirtualadmin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.stackmobile.floriculturavirtualadmin.adapter.PedidoAdapter
import com.stackmobile.floriculturavirtualadmin.databinding.FragmentPedidosBinding
import com.stackmobile.floriculturavirtualadmin.datasource.DB
import com.stackmobile.floriculturavirtualadmin.model.Pedido


class PedidosFragment : Fragment() {

    private lateinit var binding: FragmentPedidosBinding
    private lateinit var pedidoAdapter: PedidoAdapter
    private val listaPedidos: MutableList<Pedido> = mutableListOf()
    private val db = DB()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPedidosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewPedidos = binding.recyclerViewPedidos
        recyclerViewPedidos.layoutManager = LinearLayoutManager(context)
        pedidoAdapter = PedidoAdapter(requireContext(),listaPedidos)
        recyclerViewPedidos.setHasFixedSize(true)
        recyclerViewPedidos.adapter = pedidoAdapter
        db.getPedidos(listaPedidos,pedidoAdapter)

    }

}