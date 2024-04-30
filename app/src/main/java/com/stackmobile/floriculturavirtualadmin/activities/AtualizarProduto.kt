package com.stackmobile.floriculturavirtualadmin.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.stackmobile.floriculturavirtualadmin.databinding.ActivityAtualizarProdutoBinding
import com.stackmobile.floriculturavirtualadmin.datasource.DB

class AtualizarProduto : AppCompatActivity() {

    private lateinit var binding: ActivityAtualizarProdutoBinding
    private var fotoProduto: Uri? = null
    private var db = DB()

    private val selecionarFotoGaleria = registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
        if (uri != null){
            fotoProduto = uri
            binding.imgProduto.setImageURI(fotoProduto)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val codigo = intent.extras!!.getString("codigo")
       val foto = intent.extras!!.getString("foto")
       val nome = intent.extras!!.getString("nome")
       val preco = intent.extras!!.getString("preco")

        binding.btSelecionarFotoProduto.setOnClickListener {
            selecionarFotoGaleria.launch("image/*")
        }

        binding.editNomeProduto.setText(nome)
        binding.editPrecoProduto.setText(preco)
        binding.editCodigoProduto.setText(codigo)
        Glide.with(this).load(foto).into(binding.imgProduto)

        binding.btAtualizar.setOnClickListener {

            val nome = binding.editNomeProduto.text.toString()
            val preco = binding.editPrecoProduto.text.toString()
            val codigo = binding.editCodigoProduto.text.toString()

            if (nome.isEmpty() || preco.isEmpty() || codigo.isEmpty()){
                Toast.makeText(this,"Preencher todos os campos!", Toast.LENGTH_SHORT).show()
            }else if (nome.isNotEmpty() && preco.isNotEmpty() && codigo.isNotEmpty() && fotoProduto != null){
               db.atualizarProdutoComFoto(
                   fotoProduto!!,
                   nome,
                   preco,
                   codigo,
                   this
               )
            }else{
                db.atualizarProdutoSemFoto(
                    nome,
                    preco,
                    codigo,
                    this
                )
            }
        }
    }
}