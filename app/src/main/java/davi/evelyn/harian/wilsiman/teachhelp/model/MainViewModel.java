package davi.evelyn.harian.wilsiman.teachhelp.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import davi.evelyn.harian.wilsiman.teachhelp.instrutor.Instrutor;

public class MainViewModel extends ViewModel {
    private ArrayList<Instrutor> instrutores = new ArrayList<>();

    public ArrayList<Instrutor> getInstrutores() {
        Instrutor i1 = new Instrutor();
        instrutores.add(i1);
        return instrutores;
    }

}
