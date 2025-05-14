package com.upn.deam_firsts_project.activities;

        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Spinner;
        import android.widget.Toast;

        import androidx.annotation.Nullable;
        import androidx.core.content.FileProvider;

        import com.google.android.gms.auth.api.signin.internal.Storage;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.connection.util.StringListReader;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;
        import com.google.firebase.storage.UploadTask;
        import com.upn.deam_firsts_project.R;
        import com.upn.deam_firsts_project.entities.Contact;

        import java.io.File;
        import java.util.UUID;

        import retrofit2.http.Url;

public class FormContactActivity extends Activity {

            EditText etName, etPhone, etAddress;
            Spinner spGender;
            ImageView ivProfileImage;
            Button btnSave, btnaddfoto;

            ImageView avatarimage;

            Uri avatarurl;


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
                btnaddfoto = findViewById(R.id.btnAddPhoto);


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
                            saveContact();
//                            Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Error al guardar contacto", Toast.LENGTH_SHORT).show();
                        }
                    });
                });

                setupView();
            }

            protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == 1000 && resultCode == RESULT_OK) {

                    processPhotoFromCamera(data);

                }
            }

            public void saveContact(){
//                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//                StorageReference avatarRef = storageRef.child("avatars");
//
//                avatarRef.putFile(avatarurl)
//                        .addOnSuccessListener(taskSnapshot -> {
//                            avatarRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                                String avatarUrl = uri.toString();
//                            });
//                        });

                Toast.makeText(this, "Se guardó correctamene", Toast.LENGTH_SHORT).show();

            }

            private void openCamera() {

                if(checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File file = createImageFile();
                    avatarurl = FileProvider.getUriForFile(this, "com.upn.deam_firsts_project", file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, avatarurl);

                    startActivityForResult(intent, 1000);

                } else{
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 100);
                }
            }

            private void processPhotoFromCamera(Intent data){

                avatarimage.setImageURI(avatarurl);

            }

            private File createImageFile() {
                String fileName = "avatar_" + System.currentTimeMillis();
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                return new File(storageDir, fileName + ".png");
            }

            private void setupView() {
                btnaddfoto = findViewById(R.id.btnAddPhoto);
                ivProfileImage = findViewById(R.id.ivProfileImage);
                avatarimage = findViewById(R.id.ivProfileImage); // Initialize avatarimage here

                btnaddfoto.setOnClickListener(v -> {
                    // Open the camera
                    openCamera();
                });
            }



        }