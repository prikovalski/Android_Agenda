package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";
    private EditText campo_nome;
    private EditText campo_telefone;
    private EditText campo_email;
    private Aluno aluno;

    final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        inicilizacaoDosCampos();
        configuraBotaoSalvar();

        carregaAluno();
    }

    private void carregaAluno() {
        final Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campo_nome.setText(aluno.getNome());
        campo_telefone.setText(aluno.getTelefone());
        campo_email.setText(aluno.getEmail());
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preencheAluno();
        if(aluno.temIdValido()){
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void inicilizacaoDosCampos() {
        campo_nome = findViewById(R.id.activity_formulario_aluno_nome);
        campo_telefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campo_email = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campo_nome.getText().toString();
        String telefone = campo_telefone.getText().toString();
        String email = campo_email.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}
