
package com.vcapplication.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.vcapplication.MainActivity
import com.vcapplication.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var loginView : Group;
    private lateinit var otpView : Group;
    private lateinit var etEmailId : EditText;
    private lateinit var etOtp : EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginView = findViewById(R.id.login_view);
        otpView = findViewById(R.id.otp_view);
        etEmailId = findViewById(R.id.et_email);
        etOtp = findViewById(R.id.et_otp);

        var loginButton : Button = findViewById(R.id.button_login);
        var otpButton : Button = findViewById(R.id.button_send);

        loginButton.setOnClickListener(this)
        otpButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
//        TODO("Not yet implemented")
        val id = p0!!.id
        when(id){
            R.id.button_login -> {
                if(validateEmailId(etEmailId.text.toString())) {
                    loginView.visibility = GONE;
                    otpView.visibility = VISIBLE;
                }
            }
            R.id.button_send -> {
                if(validateOtp(etOtp.text.toString())) {
                    val intent : Intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent);
                    loginView.visibility = VISIBLE;
                    otpView.visibility = GONE;
                }
            }
            else -> loginView.visibility =  VISIBLE;
        }
    }


     fun validateEmailId(emailId : String) :  Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            Toast.makeText(
                baseContext,
                 resources.getString(R.string.login_email_address_missing),
                Toast.LENGTH_SHORT
            ).show()
            return false;
        } else {
           return true;
        }
    }

    fun validateOtp (otp : String) : Boolean {
        if(otp.isEmpty() || otp.length != 4)
            return false;
        else return  true;
    }

}