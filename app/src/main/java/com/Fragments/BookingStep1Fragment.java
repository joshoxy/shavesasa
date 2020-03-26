package com.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Adapter.MyBarberShopAdapter;
import com.Interface.AllBarberShopLoadListener;
import com.Interface.IBranchLoadListener;
import com.Model.Barbershop;
import com.example.shavesasa.Common.SpacesItemDecoration;
import com.example.shavesasa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class BookingStep1Fragment extends Fragment implements AllBarberShopLoadListener, IBranchLoadListener {

    CollectionReference allBarberRef;
    CollectionReference branchRef;

    AllBarberShopLoadListener allBarberShopLoadListener;
    IBranchLoadListener iBranchLoadListener;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.recycler_salon)
    RecyclerView recycler_salon;

    Unbinder unbinder;

    AlertDialog dialog;

    static BookingStep1Fragment instance;
    public static BookingStep1Fragment getInstance() {
        if (instance == null)
            instance = new BookingStep1Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allBarberRef = FirebaseFirestore.getInstance().collection("Barbers");
        allBarberShopLoadListener = this;
        iBranchLoadListener = this;
        dialog = new SpotsDialog.Builder().setContext(getActivity()).build();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

         View itemView = inflater.inflate(R.layout.fragment_booking_step_one,container,false);
         unbinder = ButterKnife.bind(this,itemView);

         initView();
         loadAllBarber();
         return itemView;
    }

    private void initView() {
        recycler_salon.setHasFixedSize(true);
        recycler_salon.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_salon.addItemDecoration(new SpacesItemDecoration(4));
    }

    private void loadAllBarber() {
        allBarberRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List<String> list = new ArrayList<>();
                            list.add("Select Region");
                            for (QueryDocumentSnapshot documentSnapshot:task.getResult())
                                list.add(documentSnapshot.getId());
                            allBarberShopLoadListener.onAllBarberShopLoadSuccess(list);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                allBarberShopLoadListener.onAllBarberShopLoadFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onAllBarberShopLoadSuccess(List<String> areaNameList) {
        spinner.setItems(areaNameList);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (position > 0){
                    loadBranch(item.toString());
                }else
                    recycler_salon.setVisibility(View.GONE);
            }
        });

    }

    private void loadBranch(String cityName) {
        dialog.show();

        branchRef = FirebaseFirestore.getInstance()
                .collection("Barbers")
                .document(cityName)
                .collection("Branch");

        branchRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<Barbershop> list = new ArrayList<>();
              if (task.isSuccessful()){
                  for (QueryDocumentSnapshot documentSnapshot:task.getResult())
                      list.add(documentSnapshot.toObject(Barbershop.class));
                  iBranchLoadListener.onBranchShopLoadSuccess(list);

              }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iBranchLoadListener.onBranchLoadFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onAllBarberShopLoadFailed(String message) {

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBranchShopLoadSuccess(List<Barbershop> barbershopList) {
        MyBarberShopAdapter adapter = new MyBarberShopAdapter(getActivity(), barbershopList);
        recycler_salon.setAdapter(adapter);
        dialog.dismiss();
        recycler_salon.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBranchLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();

    }
}
