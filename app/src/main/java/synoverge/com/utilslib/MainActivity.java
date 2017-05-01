package synoverge.com.utilslib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnChkEmpty, btnSnackbar, btnToast, btnChkNet;
    private EditText edtTextTest;
    private RelativeLayout rlLyt;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
    }

    private void initControls() {
        btnChkEmpty = (Button) findViewById(R.id.btnChkEmpty);
        btnSnackbar = (Button) findViewById(R.id.btnSnackbar);
        btnToast = (Button) findViewById(R.id.btnToast);
        btnChkNet = (Button) findViewById(R.id.btnChkInternet);

        btnChkEmpty.setOnClickListener(this);
        btnSnackbar.setOnClickListener(this);
        btnToast.setOnClickListener(this);
        btnChkNet.setOnClickListener(this);

        edtTextTest = (EditText) findViewById(R.id.edtTextTest);
        rlLyt = (RelativeLayout) findViewById(R.id.activity_main);
        mContext = MainActivity.this;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnChkEmpty) {
            if (isEdtTextEmpty(edtTextTest))
                showSnack(rlLyt, "Please enter some text !");
            else
                showSnack(rlLyt, "Hii, " + edtTextTest.getText().toString() + " !");
        } else if (view.getId() == R.id.btnSnackbar) {
            showSnack(rlLyt, "Hii, Good Afternoon !");
        } else if (view.getId() == R.id.btnToast) {
            showToast(mContext, "Hii, Good Evening !");
        } else if (view.getId() == R.id.btnChkInternet) {
            if (chkInternetConnection(mContext))
                showToast(mContext, "Yeah, Internet is ON !!");
            else
                showToast(mContext, "Oops, Internet is OFF !!");
        }
    }

    public static boolean isEdtTextEmpty(EditText editText) {
        if (editText.getText().toString().trim().equalsIgnoreCase("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean chkInternetConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()) {

            return true;
        } else {
            return false;
        }
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showSnack(View thisActivity, String text) {
        Snackbar.make(thisActivity, text, Snackbar.LENGTH_LONG).show();
    }
}
