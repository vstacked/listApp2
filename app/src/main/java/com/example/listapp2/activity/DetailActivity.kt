package com.example.listapp2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.listapp2.R
import com.example.listapp2.model.UsersModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val model = intent.getParcelableExtra(EXTRA_USER) as UsersModel

        setActionBarTitle(model.username);

        Glide.with(applicationContext)
            .load(applicationContext.resources.getIdentifier(model.avatar, "", applicationContext.packageName))
            .apply(RequestOptions().override(60, 60))
            .into(civ_detail_avatar)
        tv_detail_name.text = "${model.name} \nUsername : ${model.username}"
        tv_detail_company.text = model.company
        tv_detail_location.text = model.location
        tv_detail_repository.text = "${model.repository}\nRepository"
        tv_detail_follower.text = "${model.follower}\nFollower"
        tv_detail_following.text = "${model.following}\nFollowing"
    }

    private fun setActionBarTitle(title: String?){
        if(supportActionBar != null){
            (supportActionBar as ActionBar).title = title
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}
