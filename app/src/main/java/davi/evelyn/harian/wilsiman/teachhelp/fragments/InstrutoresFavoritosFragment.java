package davi.evelyn.harian.wilsiman.teachhelp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import davi.evelyn.harian.wilsiman.teachhelp.InstrutorComparator;
import davi.evelyn.harian.wilsiman.teachhelp.ItemInstrutoresAdapter;
import davi.evelyn.harian.wilsiman.teachhelp.R;
import davi.evelyn.harian.wilsiman.teachhelp.model.HomeViewModel;
import davi.evelyn.harian.wilsiman.teachhelp.model.Instrutor;
import davi.evelyn.harian.wilsiman.teachhelp.model.InstrutoresFavoritosViewModel;
import davi.evelyn.harian.wilsiman.teachhelp.util.Util;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InstrutoresFavoritosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstrutoresFavoritosFragment extends Fragment {
    private InstrutoresFavoritosViewModel instrutoresFavoritosViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InstrutoresFavoritosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InstrutoresFavoritosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InstrutoresFavoritosFragment newInstance(String param1, String param2) {
        InstrutoresFavoritosFragment fragment = new InstrutoresFavoritosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instrutores_favoritos, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InstrutoresFavoritosViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        ItemInstrutoresAdapter itemInstrutoresAdapter = new ItemInstrutoresAdapter(new InstrutorComparator());
        LiveData<PagingData<Instrutor>> liveData = InstrutoresFavoritosViewModel.getPage2LV();
        liveData.observe(getViewLifecycleOwner(), new Observer<PagingData<Instrutor>>() {
            @Override
            public void onChanged(PagingData<Instrutor> objectPagingData) {
                ItemInstrutoresAdapter.submitData(getViewLifecycleOwner().getLifecycle(),objectPagingData);
            }
        });
        RecyclerView rvGallery = (RecyclerView) view.findViewById(R.id.rvMenu);
        rvGallery.setAdapter(itemInstrutoresAdapterAdapter);
        float w = getResources().getDimension(R.dimen.im_width);
        int numberOfColumns = Util.calculateNoOfColumns(getContext(), w);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        rvGallery.setLayoutManager(gridLayoutManager);,

    }
}