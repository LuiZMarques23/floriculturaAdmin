package com.stackmobile.floriculturavirtualadmin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            if (isChecked){
                status_entrega = "Status de Entrega: Em Trânsito"
            }
        }

        binding.btStatusEntrega2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                status_entrega = "Status de Entrega: Entregue"
            }
        }

        binding.btAtualizarStatusEntrega.setOnClickListener {

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