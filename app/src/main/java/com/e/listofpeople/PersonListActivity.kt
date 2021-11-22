package com.e.listofpeople

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.e.listofpeople.databinding.ActivityPersonListBinding

class PersonListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityPersonListBinding>(this, R.layout.activity_person_list)
    }
}