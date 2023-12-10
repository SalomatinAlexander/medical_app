package com.example.medical_application.ui.patient_pages.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.medical_application.MyApp
import com.example.medical_application.R
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.UserPresenter
import com.example.medical_application.ui.view.UserView
import moxy.MvpAppCompatFragment
import javax.inject.Inject


class AuthFragment : MvpAppCompatFragment(), UserView  {
    lateinit var mEmailEd:AppCompatEditText
    lateinit var mPassEd:AppCompatEditText
    lateinit var mLoginBtn:AppCompatButton
    lateinit var mLoginErrorTxt:TextView

    @Inject
    lateinit var mUserPresenter: UserPresenter

    @InjectPresenter
    lateinit var userPresenter: UserPresenter

    @ProvidePresenter
    fun provideUserPresenter(): UserPresenter {
        return mUserPresenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_auth, container, false)
        initView(view)
        return view
    }

    fun initView(view: View){
        view.apply {
            mEmailEd = findViewById(R.id.email_ed)
            mPassEd = findViewById(R.id.password_ed)
            mLoginBtn = findViewById(R.id.login_btn)
            mLoginErrorTxt = findViewById(R.id.login_error_txt)
        }
        mLoginErrorTxt.visibility = View.GONE

        mLoginBtn.setOnClickListener {
            if(mEmailEd.text.toString().isEmpty() || mPassEd.text.toString().isEmpty() ||
                !mEmailEd.text.isValidEmail()){
                mLoginErrorTxt.visibility = View.VISIBLE
                return@setOnClickListener
            }
            mUserPresenter.authUser(mEmailEd.text.toString(), mLoginBtn.text.toString())
            MainActivity.INSTANCE.router.replaceScreen(Screens.PatientScreen())



        }
    }

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    override fun userIsAuth() {
        mLoginErrorTxt.visibility = View.GONE
        MainActivity.INSTANCE.router.navigateTo(Screens.PatientScreen())
    }

}