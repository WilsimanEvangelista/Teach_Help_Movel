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

public class InstrutoresFavoritosViewModel extends AndroidViewModel {
    LiveData<PagingData<Instrutor>> instrutorLd;
    Integer selectedNavigationOpId = R.id.optHome;

    public InstrutoresFavoritosViewModel(@NonNull Application application) {
        super(application);

        InstrutorRepository instrutorRepository = new InstrutorRepository(getApplication());
        InstrutorPagingSource galleryPagingSource = new InstrutorPagingSource(instrutorRepository);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Instrutor> pager = new Pager(new PagingConfig(10), () -> new InstrutorPagingSource(instrutorRepository));
        instrutorLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }
    public LiveData<PagingData<Instrutor>> getPage2Lv() {
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
