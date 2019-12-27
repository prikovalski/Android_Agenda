package com.example.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        final EditText campo_Nome = findViewById(R.id.activity_formulario_aluno_nome);
        final EditText campo_Telefone = findViewById(R.id.activity_formulario_aluno_telefone);
        final EditText campo_Email = findViewById(R.id.activity_formulario_aluno_email);

        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = campo_Nome.getText().toString();
                String telefone = campo_Telefone.getText().toString();
                String email = campo_Email.getText().toString();

                Aluno aluno = new Aluno(nome,telefone,email);

                Toast.makeText(FormularioAlunoActivity.this
                        ,aluno.getNome() + " - " +
                                aluno.getTelefone() + " - " +
                                aluno.getEmail()
                        ,Toast.LENGTH_SHORT
                        ).show();
            }
        });
    }
}
