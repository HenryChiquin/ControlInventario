package com.grupopdc.controlinventario.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grupopdc.controlinventario.Activities.CatalogoActivity;
import com.grupopdc.controlinventario.Activities.CoreActivity;
import com.grupopdc.controlinventario.AgregarEmpresaActivity;
import com.grupopdc.controlinventario.AgregarUsuarioActivity;
import com.grupopdc.controlinventario.ListaEmpresaActivity;
import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.Tools.Tools;
import com.grupopdc.controlinventario.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private LinearLayout linearUsuarios,linearConfiguracion,linearListEmpresas,linearEmpresas;
    public Tools tools;
    private CoreActivity coreActivity;

    public SlideshowFragment(){

    }
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        tools = new Tools(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearUsuarios = view.findViewById(R.id.linearUsuarios);
        linearConfiguracion = view.findViewById(R.id.linearConfiguracion);
        linearListEmpresas = view.findViewById(R.id.linearListEmpresas);
        linearEmpresas = view.findViewById(R.id.linearEmpresas);

        if(coreActivity.PREF_ID_PERFIL!=1){
            linearConfiguracion.setVisibility(View.INVISIBLE);
        }

        linearUsuarios.setOnClickListener(View->{
            event_agregarUsuario();
        });

        linearListEmpresas.setOnClickListener(View->{
            event_listaEmpresa();
        });
        linearEmpresas.setOnClickListener(View->{
            event_agregarEmpresa();
        });

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void event_agregarUsuario(){
        Intent intent = new Intent(getActivity(), AgregarUsuarioActivity.class);
        startActivity(intent);
    }

    public void event_agregarEmpresa(){
        Intent intent = new Intent(getActivity(), AgregarEmpresaActivity.class);
        startActivity(intent);
    }
    public void event_listaEmpresa(){
        Intent intent = new Intent(getActivity(), ListaEmpresaActivity.class);
        startActivity(intent);
    }
}