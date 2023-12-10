package com.example.medical_application.ui.patient_pages.patient

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.medical_application.MyApp
import com.example.medical_application.R
import com.example.medical_application.data.models.UserAnswerData
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.doctor_pages.doc.QuizRecyclerAdapter
import com.example.medical_application.ui.presenter.UserPresenter
import com.example.medical_application.ui.presenter.UserQuizPresenter
import com.example.medical_application.ui.view.UserQuizView
import com.example.medical_application.ui.view.UserView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider


class PatientFragment :  MvpAppCompatFragment(), UserQuizView, UserView {
    lateinit var mQuizRecycler:RecyclerView
    lateinit var mPointsTxt: TextView



    @Inject
    lateinit var mPresenter: UserQuizPresenter

    @InjectPresenter
    lateinit var userQuizPresenter: UserQuizPresenter

    @ProvidePresenter
    fun provideQuizPresenter(): UserQuizPresenter {
        return   mPresenter
    }

    @Inject
    lateinit var mUserPresenter: UserPresenter

    @InjectPresenter
    lateinit var userPresenter: UserPresenter

    @ProvidePresenter
    fun provideUserPresenter(): UserPresenter {
        return  mUserPresenter
    }









    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.INSTANCE.mAppComponent.inject(this)
        mPresenter.initQuizList()
        getPoint()

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mPresenter.initQuizList()
        val view = inflater.inflate(R.layout.fragment_patient, container, false)
        initView(view)
        return view
    }

    fun initView(view: View){
        view.apply {
            mQuizRecycler = findViewById(R.id.quiz_patient_recycler)
            mPointsTxt = findViewById(R.id.pacient_points_txt)
        }
        mQuizRecycler.layoutManager = LinearLayoutManager(this.context,
            RecyclerView.VERTICAL,
            false)
        mQuizRecycler.adapter = QuizRecyclerAdapter(
            true,
            mPresenter.quizList.toSet().toList(),
        )
        getPoint()





    }
   fun getPoint(){
        val id = mUserPresenter.currentUser?.id
        if(id != null){
            CoroutineScope(Dispatchers.IO).launch{
                val point = getAnswerPoint(id)
                withContext(Dispatchers.Main){
                    setUserPoint(point)
                }
            }
        }
    }

    suspend fun getAnswerPoint(userId: Int) = coroutineScope {
        val answers = MainActivity.INSTANCE.mUserAnswerDatabse.userAnswerDao()?.getByUserId(userId)
        println("IN GET ANSWER:${answers?.size}")
        if(!answers.isNullOrEmpty()){
            var points = 0

            for(a: UserAnswerData in answers){
                println("ANSWER POINT:${a.points}")

                    points += a.points

                println("POINTS:${points}")
            }
            return@coroutineScope points
        }
        return@coroutineScope 0
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initQuizList() {
        mPresenter.quizList.toSet().toList()
        mQuizRecycler.adapter?.notifyDataSetChanged()
    }

    override fun answersSaved() {
        getPoint()
    }

    override fun setUserPoint(points: Int) {
        println("SET FRAGEMNT:${points}")
        mPointsTxt.text = points.toString()
    }

    override fun userIsAuth() {}


}