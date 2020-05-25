package com.example.user.nestedrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.user.nestedrecycler.Adapters.VerticalRecyclerAdapter;
import com.example.user.nestedrecycler.Models.HorizontalModel;
import com.example.user.nestedrecycler.Models.VerticalModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView verticalRecyclerView;
    ArrayList<VerticalModel> arrayListVetical;
    VerticalRecyclerAdapter adapter;

    HashMap<String,Object> hm=new HashMap();
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db.collection("Manmaani")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println( document.getId() + " => " + document.getData().get("name")+count++);
                                System.out.println( document.getId() + " => " + document.getData().get("description"));
//                                System.out.println( document.getId() + " => " + document.getData().get("nick_name"));
                                System.out.println(document.getId().getClass()+ " => " +document.getData().getClass());
//                                String[] arr={(String)document.getData().get("first_name"),
//                                        (String)document.getData().get("last_name"),
//                                        (String)document.getData().get("nick_name")};
                                String[] arr={(String)document.getData().get("name"),
                                        (String)document.getData().get("description")};
                                hm.put(document.getId(), arr);
                                a=(String)document.getData().get("description");
                                System.out.println(a+"lllllllllllllllllllllll");

                            }
                        } else {
                            Log.w( "Log","Error getting documents.", task.getException());
                        }
                    }
                });

        arrayListVetical=new ArrayList<>();
        verticalRecyclerView=findViewById(R.id.verticalRecyclerView);
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter=new VerticalRecyclerAdapter(this,arrayListVetical);

        verticalRecyclerView.setAdapter(adapter);

        setData();
    }

    private void setData() {
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        System.out.println(hm.toString());
        for (Map.Entry mapElement : hm.entrySet()) {

            String key = (String)mapElement.getKey();

            String[] value =(String[]) mapElement.getValue();

            System.out.println( key+" : "+value[0]+" "+value[1]+" "+"nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
            VerticalModel verticalModel =new VerticalModel();
            verticalModel.setTitle(key);
            ArrayList<HorizontalModel> arrayList =new ArrayList<>();
            for(int j=0;j<3;j++){
                HorizontalModel horizontalModel=new HorizontalModel();
                horizontalModel.setDescription(value[0]);
                horizontalModel.setName(value[1]);
                arrayList.add(horizontalModel);
            }
            verticalModel.setArrayList(arrayList);
            arrayListVetical.add(verticalModel);
        }
        adapter.notifyDataSetChanged();

//        for(int i=0;i<3;i++){
//            VerticalModel verticalModel =new VerticalModel();
//            verticalModel.setTitle("Title "+i);
//            ArrayList<HorizontalModel> arrayList =new ArrayList<>();
//            for(int j=0;j<5;j++){
//                HorizontalModel horizontalModel=new HorizontalModel();
//                horizontalModel.setDescription("Description "+j);
//                horizontalModel.setName("Name "+j);
//                arrayList.add(horizontalModel);
//            }
//            verticalModel.setArrayList(arrayList);
//            arrayListVetical.add(verticalModel);
//        }
//        adapter.notifyDataSetChanged();
    }

}
