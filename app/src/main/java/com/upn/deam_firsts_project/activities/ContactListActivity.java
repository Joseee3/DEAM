package com.upn.deam_firsts_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.adapters.ContactAdapter;
import com.upn.deam_firsts_project.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView rvContacts;
    private SearchView searchView;
    private ContactAdapter adapter;
    private List<Contact> contactList = new ArrayList<>();
    private boolean isLoading = false;
    private String lastLoadedKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        rvContacts = findViewById(R.id.rvContacts);
        searchView = findViewById(R.id.searchView);
        FloatingActionButton fabAddContact = findViewById(R.id.fabAddContact);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(contactList, this);
        rvContacts.setAdapter(adapter);

        loadContacts("");

        rvContacts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading && layoutManager != null &&
                        layoutManager.findLastCompletelyVisibleItemPosition() == contactList.size() - 1) {
                    loadMoreContacts();
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactList.clear();
                lastLoadedKey = null;
                loadContacts(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactList.clear();
                lastLoadedKey = null;
                loadContacts(newText);
                return false;
            }
        });

        fabAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(ContactListActivity.this, FormContactActivity.class);
            startActivity(intent);
        });
    }

    private void loadContacts(String query) {

        Log.d("ContactListActivity", "loadContacts ejecutado");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("contacts");
        ref.orderByChild("name").startAt(query).endAt(query + "\uf8ff")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        contactList.clear();
                        for (DataSnapshot contactSnapshot : snapshot.getChildren()) {
                            Contact contact = contactSnapshot.getValue(Contact.class);
                            if (contact != null) {
                                contactList.add(contact);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("ContactListActivity", "Error al cargar contactos: " + error.getMessage());
                    }
                });
    }

    private void loadMoreContacts() {
        if (lastLoadedKey == null) return;

        Log.d("ContactListActivity", "loadMoreContacts ejecutado");

        isLoading = true;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("contacts");
        ref.orderByKey().startAfter(lastLoadedKey).limitToFirst(10)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot contactSnapshot : snapshot.getChildren()) {
                            Contact contact = contactSnapshot.getValue(Contact.class);
                            if (contact != null) {
                                contactList.add(contact);
                                lastLoadedKey = contactSnapshot.getKey();
                            }
                        }
                        Log.d("ContactListActivity", "Ultimo dato: " + lastLoadedKey);
                        adapter.notifyDataSetChanged();
                        isLoading = false;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("ContactListActivity", "Error al cargar más contactos: " + error.getMessage());
                        isLoading = false;
                    }
                });
    }
}