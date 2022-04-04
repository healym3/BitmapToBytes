package healym3.bitmaptobytes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class MainActivity extends AppCompatActivity {
    Button button;
    ImageView ivSource, ivCompressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        ivCompressed = findViewById(R.id.ivCompressed);
        ivSource = findViewById(R.id.ivSource);
        try {
            givenFile_whenEncrypt_thenSuccess();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DataInputStream dataInputStream = new DataInputStream(getAssets().open("test2.bmp"));
                    byte[] image = new byte[60];
                    dataInputStream.readFully(image, 0, 54);
                    StringBuilder sb = new StringBuilder();
                    for (byte b: image
                         ) {
                        sb.append(String.valueOf(b) + ", ");
                        //
                    }
                    Log.d("imageBytes", "onClick: image bytes " + sb.toString());
                    dataInputStream.close();

                    //InputStream inputStream = getAssets().open("test.bmp");
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    ivSource.setImageBitmap(bitmap);
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
//                    byte[] byteArray = stream.toByteArray();
//                    Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
//                    ivCompressed.setImageBitmap(compressedBitmap);
//                    Toast.makeText(getApplicationContext(),
//                            "ByteArray created..",
//                            Toast.LENGTH_SHORT).show();
//                    StringBuilder stringBuilder = new StringBuilder();
//                    for (byte b: byteArray
//                         ) {
//                        stringBuilder.append(String.valueOf(b) + ", ");
//                        //
//                    }
//
//                    Log.d("byteArray", "onClick: " + stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void givenFile_whenEncrypt_thenSuccess()
            throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException {

        SecretKey key = AES.generateKey(128);
        String algorithm = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec = AES.generateIv();
        AssetManager assetManager = getAssets();
        AssetFileDescriptor fileDescriptor = assetManager.openFd("test2.bmp");
        FileInputStream stream = fileDescriptor.createInputStream();
        //File inputFile = new DataInputStream(getAssets().open("test2.bmp"));
        File encryptedFile = new File("classpath:baeldung.encrypted");
        //File decryptedFile = new File("document.decrypted");
        AES.encryptFile(algorithm, key, ivParameterSpec, stream, encryptedFile);


    }

    private File createFileFromInputStream(InputStream inputStream) {

        try{
            File f = new File("newFilePath");
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;

            while((length=inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }

            outputStream.close();
            inputStream.close();

            return f;
        }catch (IOException e) {
            //Logging exception
        }

        return null;
    }
}