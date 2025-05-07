package com.upn.deam_firsts_project.activities;

        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Spinner;
        import android.widget.Toast;

        import androidx.annotation.Nullable;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.upn.deam_firsts_project.R;
        import com.upn.deam_firsts_project.entities.Contact;

        import java.util.UUID;

        public class FormContactActivity extends Activity {

            EditText etName, etPhone, etAddress;
            Spinner spGender;
            ImageView ivProfileImage;
            Button btnSave;

            @Override
            protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_form_contact);

                etName = findViewById(R.id.etName);
                etPhone = findViewById(R.id.etPhone);
                etAddress = findViewById(R.id.etAddress);
                spGender = findViewById(R.id.spGender);
                ivProfileImage = findViewById(R.id.ivProfileImage);
                btnSave = findViewById(R.id.btnSave);

                btnSave.setOnClickListener(v -> {
                    String name = etName.getText().toString();
                    String phone = etPhone.getText().toString();
                    String address = etAddress.getText().toString();
                    String gender = spGender.getSelectedItem().toString();

                    if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                        Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String id = UUID.randomUUID().toString();
                    Contact contact = new Contact(id, name, phone, gender, address, ""); // Puedes agregar lógica para subir imágenes

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("contacts");
                    ref.child(id).setValue(contact).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Error al guardar contacto", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            }
        }