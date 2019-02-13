package sample.com.phoneformatter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText phone = findViewById(R.id.phone);
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        System.out.println(">>>>>>" + Locale.getDefault().getCountry());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
//        } else {
//            phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
//        }

        Button validate1 = findViewById(R.id.validate1);
        validate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = phone.getText().toString();
                boolean isValidNumber = false;
                try {
                    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
                    Phonenumber.PhoneNumber number = phoneNumberUtil.parse(phoneNumber, Locale.getDefault().getCountry());
                    isValidNumber = phoneNumberUtil.isPossibleNumber(number);
                } catch (NumberParseException e) {
                    System.err.println("NumberParseException was thrown: " + e.toString());
                }

                if (isValidNumber) {
                    Toast.makeText(MainActivity.this, "Valid Number", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "InValid Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button validate2 = findViewById(R.id.validate2);
        validate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = phone.getText().toString();
                boolean isValidNumber = false;
                try {
                    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
                    Phonenumber.PhoneNumber number = phoneNumberUtil.parse(phoneNumber, Locale.getDefault().getCountry());
                    PhoneNumberUtil.ValidationResult validationResult = phoneNumberUtil.isPossibleNumberWithReason(number);
                    isValidNumber = validationResult == PhoneNumberUtil.ValidationResult.IS_POSSIBLE;
                } catch (NumberParseException e) {
                    System.err.println("NumberParseException was thrown: " + e.toString());
                }

                if (isValidNumber) {
                    Toast.makeText(MainActivity.this, "Valid Number", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "InValid Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
