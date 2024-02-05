package com.example.medical_application.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.example.medical_application.R
import com.example.medical_application.RoleFragment
import com.example.medical_application.data.database.AnswerDatabase
import com.example.medical_application.data.database.QuestionDatabase
import com.example.medical_application.data.database.UserAnswerDatabase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    private val navigatorHolder get() = cicerone.getNavigatorHolder()
    private val navigator = AppNavigator(this, R.id.fragment_view_id)
    lateinit var mQuestionDatabase: QuestionDatabase
    lateinit var mAnswerDatabase:AnswerDatabase
    lateinit var mUserAnswerDatabse:UserAnswerDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        INSTANCE = this
        setContentView(R.layout.activity_main)
        initDatabase();
        init(context = this)
        setFragmentOnScreen(RoleFragment())
    }

    private fun initDatabase(){
        mQuestionDatabase = Room.databaseBuilder(this, QuestionDatabase::class.java,
            "questiondata")
            .build()
        mAnswerDatabase = Room.databaseBuilder(this, AnswerDatabase::class.java,
            "answerdata")
            .build()
        mUserAnswerDatabse = Room.databaseBuilder(this, UserAnswerDatabase::class.java,
            "useranswerdatabase").build()

    }

    private fun init(context: Context){
        navigatorHolder.setNavigator(navigator)
        fragmentManager = supportFragmentManager;
    }

    fun setFragmentOnScreen(fr: Fragment){
        fragmentManager.beginTransaction().replace(R.id.fragment_view_id, fr).commit()
    }

    companion object {
        internal lateinit var INSTANCE: MainActivity
            private set
    }

}