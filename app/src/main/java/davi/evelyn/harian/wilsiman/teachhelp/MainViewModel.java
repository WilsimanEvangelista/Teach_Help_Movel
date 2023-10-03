package davi.evelyn.harian.wilsiman.teachhelp;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private ArrayList<Instrutor> instrutores = new ArrayList<>();

    public ArrayList<Instrutor> getInstrutores() {
        Instrutor i1 = new Instrutor();
        instrutores.add(i1);
        return instrutores;
    }

}
