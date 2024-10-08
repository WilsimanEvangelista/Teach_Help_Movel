package davi.evelyn.harian.wilsiman.teachhelp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import davi.evelyn.harian.wilsiman.teachhelp.R;
import kotlinx.coroutines.CoroutineScope;

/**
 * ViewModel referente a HomeActivity
 */
public class HomeViewModel extends AndroidViewModel {

    LiveData<PagingData<Instrutor>> instrutorLd;
    Integer selectedNavigationOpId = R.id.optHome;

    public HomeViewModel(@NonNull Application application) {
        super(application);


    }
    public LiveData<PagingData<Instrutor>> getIntrutoresLD(String materia) {
        InstrutorRepository instrutorRepository = new InstrutorRepository(getApplication());
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Instrutor> pager = new Pager(new PagingConfig(10), () -> new InstrutorPagingSource(instrutorRepository, materia));
        instrutorLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
        return instrutorLd;
    }

    // Método para definir a opção de navegação selecionada
    public void setNavigationOpSelected(int navigationOpId) {
        selectedNavigationOpId = navigationOpId;
    }

    // Método para obter a opção de navegação selecionada
    public Integer getSelectedNavigationOpId() {

        return selectedNavigationOpId;
    }


}