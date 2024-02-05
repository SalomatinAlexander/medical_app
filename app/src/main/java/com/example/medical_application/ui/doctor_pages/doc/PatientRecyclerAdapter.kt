package com.example.medical_application.ui.doctor_pages.doc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medical_application.R
import com.example.medical_application.data.models.UserData
import com.example.medical_application.ui.MainActivity
import com.example.medical_application.ui.Screens

class PatientRecyclerAdapter (
    val userList:List<UserData>):
    RecyclerView.Adapter<PatientRecyclerAdapter.PatientHolder>() {

    class PatientHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var mName:TextView
        lateinit var mSoname:TextView
        lateinit var mPatientLayout:LinearLayoutCompat


        fun bind(user: UserData) {
            initView()
            mName.text = user.name
            mSoname.text = user.soname

            mPatientLayout.setOnClickListener {
                MainActivity.INSTANCE.router.navigateTo(Screens.PatientScreen(user, true))
            }


        }

        private fun initView() {
            itemView.apply {
                mName = findViewById(R.id.patient_name_id)
                mSoname = findViewById(R.id.patient_soname_id)
                mPatientLayout = findViewById(R.id.quiz_setting_layout)
            }


        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.patient_item, parent, false)
        return PatientHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder:PatientHolder, position: Int) {
        holder.bind(userList[position])

    }


}
