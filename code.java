package com.example.chatapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHandler {




    public enum UserValidation{
        Valid,Invalid
    }

    FirebaseHandler(){
        // empty constructor
    }


    public static UserValidation userRegisteredCheck(final String userUID){

        final boolean[] check = {false};
        readData(new FirebaseCallback() {
            @Override
            public void OnCall(ArrayList<String> userUIDs) {

                for(String it : userUIDs){
                    if(it.equals(userUID)){
                      check[0] = true;
                    }
                }
            }


        });
        if(check[0]){
            return UserValidation.Valid;
        }else{
            return UserValidation.Invalid;
        }
    }

    private static void readData(final FirebaseCallback firebaseCallback){
        final ArrayList<String> userUID = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshotEx : snapshot.getChildren()){
                    userUID.add(snapshotEx.getKey());
                    firebaseCallback.OnCall(userUID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public interface FirebaseCallback{
        void OnCall(ArrayList<String> userUIDs);

    }




}
