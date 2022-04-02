package healym3.bitmaptobytes;

import androidx.appcompat.app.AppCompatActivity;

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
import java.io.IOException;
import java.io.InputStream;
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream inputStream = getAssets().open("image.bmp");
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    ivSource.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
                    byte[] byteArray = stream.toByteArray();
                    Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                    ivCompressed.setImageBitmap(compressedBitmap);
                    Toast.makeText(getApplicationContext(),
                            "ByteArray created..",
                            Toast.LENGTH_SHORT).show();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (byte b: byteArray
                         ) {
                        stringBuilder.append(String.valueOf(b) + ", ");
                        //
                    }

                    Log.d("byteArray", "onClick: " + stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}