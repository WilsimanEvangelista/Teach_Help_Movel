package davi.evelyn.harian.wilsiman.teachhelp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastroInstrutorViewModel extends AndroidViewModel {

    public CadastroInstrutorViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<Boolean> cadastroInstrutor(String descricao, String curriculo, String materia) {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            @Override

            public void run(){

                InstrutorRepository instrutorRepository= new InstrutorRepository(getApplication());
                boolean b = instrutorRepository.addInstrutor(descricao,  materia, curriculo);

                result.postValue(b);
            }
        });

        return result;
    }
}

