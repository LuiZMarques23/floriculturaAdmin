package com.stackmobile.floriculturavirtualadmin.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stackmobile.floriculturavirtualadmin.activities.AtualizarStatusEntrega
import com.stackmobile.floriculturavirtualadmin.databinding.PedidoItemBinding
import com.stackmobile.floriculturavirtualadmin.model.Pedido

class PedidoAdapter(private val context: Context, private val listaPedidos: MutableList<Pedido>):
    RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val itemLista = PedidoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PedidoViewHolder(itemLista)
    }

    override fun getItemCount() = listaPedidos.size


    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        holder.txtEndereco.text = listaPedidos[position].endereco
        holder.txtCelular.text = listaPedidos[position].celular
        holder.txtProduto.text = listaPedidos[position].produto
        holder.txtPreco.text = listaPedidos[position].preco
        holder.txtTamanhoCalcado.text = listaPedidos[position].tamanho_calcado
        holder.txtStatusEntrega.text = listaPedidos[position].status_entrega
        holder.txtStatusPagamento.text = listaPedidos[position].status_pagamento

        holder.txtStatusEntrega.setOnClickListener {
            val intent = Intent(context,AtualizarStatusEntrega::class.java)
            intent.putExtra("pedidoID",listaPedidos[position].pedidoID)
            intent.putExtra("usuarioID",listaPedidos[position].usuarioID)
            context.startActivity(intent)
        }

        if (holder.txtStatusEntrega.text.equals("Status de Entrega: Em andamento")){
            holder.txtStatusEntrega.setTextColor(Color.parseColor("#FF0000"))
        }else if (holder.txtStatusEntrega.text.equals("Status de Entrega: Em Trânsito")){
            holder.txtStatusEntrega.setTextColor(Color.parseColor("#FF9800"))
        }else if (holder.txtStatusEntrega.text.equals("Status de Entrega: Entregue")){
            holder.txtStatusEntrega.setTextColor(Color.parseColor("#8BC34A"))
        }else if (holder.txtStatusEntrega.text.equals("Status de Entrega: Em Preparção")){
            holder.txtStatusEntrega.setTextColor(Color.parseColor("#FFFF00"))
        }else if(holder.txtStatusEntrega.text.equals("Status de Entrega: Retirar Pedido loja")){
                holder.txtStatusEntrega.setTextColor(Color.parseColor("#FFFF00"))
        }

    }

    inner class PedidoViewHolder(binding: PedidoItemBinding): RecyclerView.ViewHolder(binding.root){
        val txtEndereco = binding.txtEndereco
        val txtCelular = binding.txtCelular
        val txtProduto = binding.txtProduto
        val txtPreco = binding.txtPreco
        val txtTamanhoCalcado = binding.txtTamanhoCalcado
        val txtStatusEntrega = binding.txtStatusEntrega
        val txtStatusPagamento = binding.txtStatusDePagamento
    }
}