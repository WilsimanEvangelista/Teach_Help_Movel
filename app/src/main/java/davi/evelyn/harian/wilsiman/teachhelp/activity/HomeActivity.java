package davi.evelyn.harian.wilsiman.teachhelp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import davi.evelyn.harian.wilsiman.teachhelp.R;
import davi.evelyn.harian.wilsiman.teachhelp.fragments.HomeFragment;
import davi.evelyn.harian.wilsiman.teachhelp.fragments.InstrutoresFavoritosFragment;
import davi.evelyn.harian.wilsiman.teachhelp.fragments.PerfilAlunoFragment;
import davi.evelyn.harian.wilsiman.teachhelp.model.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    static int RESULT_REQUEST_PERMISSION = 2;

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
    protected void onResume(){
        super.onResume();
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        checkForPermissions(permissions);
    }
    private void checkForPermissions(List<String> permissions) {
        List<String> permissionsNotGranted = new ArrayList<>();

        for(String permission : permissions){
            if( !hasPermission(permission)){
                permissionsNotGranted.add(permission);
            }
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(permissionsNotGranted.size() > 0){
                requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]),RESULT_REQUEST_PERMISSION);
            }
            else{
                HomeViewModel vm = new ViewModelProvider(this).get(HomeViewModel.class);
                int navigationOpSelected = vm.getSelectedNavigationOpId();
                bottomNavigationView.setSelectedItemId(navigationOpSelected);
            }
        }
    }
    private boolean hasPermission(String permission) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ActivityCompat.checkSelfPermission(HomeActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final List<String> permissionsRejected = new ArrayList<>();

        if(requestCode == RESULT_REQUEST_PERMISSION) {
            for(String permission : permissions) {
                if(!hasPermission(permission)){
                    permissionsRejected.add(permission);
                }
            }
        }

        if(permissionsRejected.size() > 0) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(shouldShowRequestPermissionRationale(permissionsRejected.get(0))){
                    new AlertDialog.Builder(HomeActivity.this).setMessage("Para usar essa app é preciso conceder essas permissões").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]),RESULT_REQUEST_PERMISSION);
                        }
                    }).create().show();
                }
            }

        }
        else{
            HomeViewModel vm = new ViewModelProvider(this).get(HomeViewModel.class);
            int navigationOpSelected = vm.getSelectedNavigationOpId();
            bottomNavigationView.setSelectedItemId(navigationOpSelected);
        }
    }
}