package com.oguzhann.criptoappt2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFrag extends Fragment {

    TextView cekad,cekemail,cekuserhint;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_person, container, false);

        cekad = view.findViewById(R.id.txtisimcekme);
        cekemail = view.findViewById(R.id.txtemailcekme);
        cekuserhint=view.findViewById(R.id.txtuser);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("kullanıcılar");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            ref.child(uid).child("").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String username = snapshot.getValue(String.class);
                    cekad.setText(uid);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Hata durumunda yapılacak işlemler buraya yazılabilir
                }
            });
            String email = currentUser.getEmail();
            cekemail.setText(email);

        }

       return view;
    }
}