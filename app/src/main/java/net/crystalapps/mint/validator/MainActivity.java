package net.crystalapps.mint.validator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import net.crystalapps.mint.validator.library.annotations.Order;
import net.crystalapps.mint.validator.library.annotations.checkbox.CheckedCheckBox;
import net.crystalapps.mint.validator.library.annotations.text.ConfirmPassword;
import net.crystalapps.mint.validator.library.annotations.text.MaxLength;
import net.crystalapps.mint.validator.library.annotations.text.MinLength;
import net.crystalapps.mint.validator.library.annotations.text.NotEmpty;
import net.crystalapps.mint.validator.library.annotations.text.ValidEmail;
import net.crystalapps.mint.validator.library.config.ValidationConfig;
import net.crystalapps.mint.validator.library.core.AbstractChain;
import net.crystalapps.mint.validator.library.core.FieldProvider;
import net.crystalapps.mint.validator.library.core.FieldValidator;
import net.crystalapps.mint.validator.library.core.FilterChain;
import net.crystalapps.mint.validator.library.core.ValidationError;
import net.crystalapps.mint.validator.library.core.ValidationListener;
import net.crystalapps.mint.validator.library.defaults.MintFieldProcessor;
import net.crystalapps.mint.validator.library.defaults.MintFieldProvider;
import net.crystalapps.mint.validator.library.defaults.MintFilterChain;
import net.crystalapps.mint.validator.library.models.ProcessModel;

import java.lang.reflect.Field;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ValidationListener {

    @NotEmpty({1, R.string.full_name_empty_error})
    @MinLength(length = 3, value = {2, R.string.full_name_min_chr_length})
    @MaxLength(length = 20, value = {3, R.string.full_name_max_chr_length})
    @Order(1)
    private TextView fullName;

    @NotEmpty({1, R.string.email_empty_error})
    @ValidEmail({2, R.string.email_invalid})
    @Order(2)
    private TextView email;

    @NotEmpty({1, R.string.pass_empty_error})
    @MinLength(length = 6, value = {2, R.string.pass_min_length_error})
    @Order(3)
    private TextView password;

    @ConfirmPassword(id = R.id.et_password, value = {1, R.string.confirm_pass_not_match_error})
    @Order(4)
    private TextView confirmPassword;

    @NotEmpty({1, R.string.mobile_number_empty_error})
    @Order(5)
    private TextView mobileNumber;

    @CheckedCheckBox({1, R.string.terms_error})
    @Order(6)
    private CheckBox cbTerms;

    private Button btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // attaching view to reference
        attachViews();

        // attach click listener
        btnValidate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FieldValidator.validate(this, this);
    }

    @Override
    public void validateSuccess() {
        Toast.makeText(this, "All fields has been validated", Toast.LENGTH_LONG).show();
    }

    @Override
    public void validateFailed(@NonNull ValidationError validationError, @NonNull FilterChain filterChain) {
        Toast.makeText(this, validationError.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void attachViews() {
        fullName = findViewById(R.id.et_full_name);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        confirmPassword = findViewById(R.id.et_confirm_password);
        mobileNumber = findViewById(R.id.et_mobile_number);
        cbTerms = findViewById(R.id.cb_terms);
        btnValidate = findViewById(R.id.btnValidate);
    }
}
