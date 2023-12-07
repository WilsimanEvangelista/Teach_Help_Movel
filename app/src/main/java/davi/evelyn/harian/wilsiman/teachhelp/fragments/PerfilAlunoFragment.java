package davi.evelyn.harian.wilsiman.teachhelp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import davi.evelyn.harian.wilsiman.teachhelp.activity.CadastroInstrutorActivity;
import davi.evelyn.harian.wilsiman.teachhelp.activity.LoginActivity;
import davi.evelyn.harian.wilsiman.teachhelp.R;
import davi.evelyn.harian.wilsiman.teachhelp.model.PerfilViewModel;
import davi.evelyn.harian.wilsiman.teachhelp.model.UserProfile;
import davi.evelyn.harian.wilsiman.teachhelp.util.Config;

public class PerfilAlunoFragment extends Fragment {
        private PerfilViewModel perfilViewModel;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            perfilViewModel = new ViewModelProvider(requireActivity()).get(PerfilViewModel.class);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_perfil_aluno, container, false);
            return view;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            LiveData<UserProfile> userProfileLD = perfilViewModel.getUserProfile();

            userProfileLD.observe(getViewLifecycleOwner(), new Observer<UserProfile>() {
                @Override
                public void onChanged(UserProfile userProfile) {
                    TextView tvNome = view.findViewById(R.id.tvNomeAluno);
                    tvNome.setText(userProfile.nome);

                    TextView tvEndereco = view.findViewById(R.id.tvEnderecoAluno);
                    tvEndereco.setText(userProfile.endereco);

                    TextView tvDescricao = view.findViewById(R.id.tvDescAluno);
                    tvDescricao.setText(userProfile.descricao);

                    TextView tvEmail = view.findViewById(R.id.tvEmailAluno);
                    tvEmail.setText(userProfile.email);

                }
            });



            // Inicialize o botão e defina o OnClickListener aqui
            Button btnEncerrarSessao = view.findViewById(R.id.btnEncerrarSessao);

            btnEncerrarSessao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Limpe as informações de login (se necessário)
                    Config.setLogin(requireContext(), "");

                    // Redirecione o usuário para a tela de login
                    Intent intent = new Intent(requireActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });

            // Supondo que você tem um botão "Quero me tornar um instrutor" em sua activity de aluno
            Button btnTornarInstrutor = view.findViewById(R.id.btnVirarInstrutor);

            btnTornarInstrutor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireActivity(), CadastroInstrutorActivity.class);
                    startActivity(intent);
                }
            });
        }
    }