package com.stackmobile.floriculturavirtualadmin.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.stackmobile.floriculturavirtualadmin.databinding.ActivityAtualizarStatusEntregaBinding
import com.stackmobile.floriculturavirtualadmin.datasource.DB

class AtualizarStatusEntrega : AppCompatActivity() {

    private lateinit var binding: ActivityAtualizarStatusEntregaBinding
    private var status_entrega = "Status de Entrega: Em andamento"
    private var db = DB()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarStatusEntregaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pedidoID = intent.extras?.getString("pedidoID").toString()
        val usuarioID = intent.extras?.getString("usuarioID").toString()

        binding.btStatusEntrega1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                status_entrega = "Status de Entrega: Em Trânsito"
            }
        }

        binding.btStatusEntrega2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                status_entrega = "Status de Entrega: Entregue"
            }
        }

        binding.btStatusEmPreparcao.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                status_entrega = "Status de Entrega: Em Preparação"
            }

        }

        binding.btStatusDisponivel.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                status_entrega = "Status de Entrega: Pedido Disponivel Retirada Loja "
            }
        }

            binding.btAtualizarStatusEntrega.setOnClickListener {

                Toast.makeText(this,"Sucesso atualizar status!",Toast.LENGTH_SHORT).show()

                db.atualizarStatusPedidoUsuario(
                    status_entrega,
                    usuarioID,
                    pedidoID
                )
                db.atualizarStatusPedidoAdmin(
                    status_entrega,
                    pedidoID,
                    this
                )

            }
        }
    }