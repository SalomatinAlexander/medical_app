package com.example.medical_application.ui.patient_pages.auth

import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.medical_application.MyApp
import com.example.medical_application.R
import com.example.medical_application.core.Constants
import com.example.medical_application.data.models.UserData
import com.example.medical_application.data.repositories.UserRepository
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens
import com.example.medical_application.ui.presenter.UserPresenter
import com.example.medical_application.ui.view.UserView
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.app
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject
import kotlin.random.Random


class AuthFragment constructor(role: String) : MvpAppCompatFragment(), UserView  {
    val userRole : String = role
    lateinit var mEmailEd:AppCompatEditText
    lateinit var mPassEd:AppCompatEditText
    lateinit var mLoginBtn:AppCompatButton
    lateinit var mLoginErrorTxt:TextView
    lateinit var mNameEd: AppCompatEditText
    lateinit var mSonameEd: AppCompatEditText
    var  currentUser:UserData? = null
    val userRepository = UserRepository()

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_auth, container, false)
        initView(view)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initView(view: View){
        view.apply {
            mEmailEd = findViewById(R.id.email_ed)
            mPassEd = findViewById(R.id.password_ed)
            mLoginBtn = findViewById(R.id.login_btn)
            mLoginErrorTxt = findViewById(R.id.login_error_txt)
            mNameEd = findViewById(R.id.name_ed)
            mSonameEd = findViewById(R.id.soname_ed)
        }
        mLoginErrorTxt.visibility = View.GONE

        mLoginBtn.setOnClickListener {
            if(validationFields()){
                mLoginErrorTxt.visibility = View.VISIBLE
                return@setOnClickListener
            }
           authUser(initUser())




        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUser(): UserData{
        return  UserData(userId = getUUid().toString(),
            email = mEmailEd.text.toString(), name = mNameEd.text.toString(),
            soname = mSonameEd.text.toString(), role = userRole)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUUid(): String{
        return UUID.randomUUID().toString()
    }

    private fun validationFields(): Boolean{
        return  mEmailEd.text.toString().isEmpty() || mPassEd.text.toString().isEmpty() ||
                !mEmailEd.text.isValidEmail() || mNameEd.text.toString().isEmpty()
                || mSonameEd.text.toString().isEmpty()
    }

    fun authUser(user: UserData){
        CoroutineScope(Dispatchers.IO).launch {
            println("AUTH USER: START")
            val result = getUser(user.email)
            println("AUTH USER: RESULT: ${result}")
            if(result != null){
                currentUser = result
                withContext(Dispatchers.Main){
                    userIsAuth(userData = currentUser!!)
                }
            }else{
                insertUser(user)
                val newUser = getUser(user.email)
                currentUser = newUser
                withContext(Dispatchers.Main){
                    userIsAuth(currentUser!!)
                }

            }
        }

    }

    suspend fun getUser(email: String): UserData? = coroutineScope {
        return@coroutineScope userRepository.getCurrentUser(email)
    }

    suspend fun insertUser(user:UserData): Boolean = coroutineScope {
        return@coroutineScope userRepository.saveUser(user)
    }

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

     fun userIsAuth(userData: UserData) {
        mLoginErrorTxt.visibility = View.GONE
            if(currentUser!!.role == Constants.doctorRole){
                MainActivity.INSTANCE.router.navigateTo(Screens.DocScreen())
                return
            }
            MainActivity.INSTANCE.router.navigateTo(Screens.PatientScreen(userData, false))



    }

}