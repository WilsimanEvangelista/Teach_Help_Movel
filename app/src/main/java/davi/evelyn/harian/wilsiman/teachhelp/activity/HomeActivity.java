package davi.evelyn.harian.wilsiman.teachhelp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import davi.evelyn.harian.wilsiman.teachhelp.R;
import davi.evelyn.harian.wilsiman.teachhelp.fragments.HomeFragment;
import davi.evelyn.harian.wilsiman.teachhelp.fragments.InstrutoresFavoritosFragment;
import davi.evelyn.harian.wilsiman.teachhelp.fragments.PerfilAlunoFragment;
import davi.evelyn.harian.wilsiman.teachhelp.model.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bnMenu);
        final HomeViewModel vm = new ViewModelProvider(this).get(HomeViewModel.class);
        Integer menuItem = vm.getSelectedNavigationOpId();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vm.setNavigationOpSelected(item.getItemId());
                switch (item.getItemId()) {
                    case R.id.optPerfil:
                        PerfilAlunoFragment perfilAlunoFragment = new PerfilAlunoFragment();
                        setFragment(perfilAlunoFragment, R.id.frameLayout);
                        setTitle("PERFIL");
                        break;
                    case R.id.optFavoritos:
                        InstrutoresFavoritosFragment instrutoresFavoritosFragment = new InstrutoresFavoritosFragment();
                        setFragment(instrutoresFavoritosFragment, R.id.frameLayout);
                        setTitle("INSTRUTORES FAVORITOS");
                        break;
                    case R.id.optHome:
                        HomeFragment homeFragment = new HomeFragment();
                        setFragment(homeFragment, R.id.frameLayout);
                        setTitle("HOME");
                        break;
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(menuItem);


    }


    // Método para definir o fragmento na tela
    public void setFragment(Fragment fragment, int frameLayoutId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameLayoutId, fragment)
                .addToBackStack(null)
                .commit();
    }
}