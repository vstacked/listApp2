package com.example.listapp2

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp2.adapter.UsersAdapter
import com.example.listapp2.model.UsersModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import kotlin.text.Charsets.UTF_8


class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private var viewItems: ArrayList<UsersModel> = arrayListOf()

    private lateinit var mAdapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle()

        mRecyclerView = findViewById(R.id.rv_list)
        mRecyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager

        mAdapter = UsersAdapter(viewItems)
        mRecyclerView.adapter = mAdapter

        addItemFromJSON()
    }

    private fun setActionBarTitle(){
        if(supportActionBar != null){
            (supportActionBar as ActionBar).title = "Github User's"
        }
    }

    private fun addItemFromJSON(){
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val countries = obj.getJSONArray("users")
            for (i in 0 until countries.length()) {
                val jsonObject = countries.getJSONObject(i)
                val username = jsonObject.getString("username")
                val name = jsonObject.getString("name")
                val avatar = jsonObject.getString("avatar")
                val company = jsonObject.getString("company")
                val location = jsonObject.getString("location")
                val repository = jsonObject.getInt("repository")
                val follower = jsonObject.getInt("follower")
                val following = jsonObject.getInt("following")
                val model = UsersModel(username, name,avatar,company,location, repository, follower,following)
                viewItems.add(model)
            }
        } catch (ex: JSONException) {
            ex.printStackTrace()
        }
    }

    private fun loadJSONFromAsset(): String {
        val json: String

        try {
            val data: InputStream = resources.openRawResource(R.raw.githubuser)
            val size = data.available()
            val buffer = ByteArray(size)
            data.read(buffer)
            data.close()

            json = String(buffer, UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace();
            return "null";
        }
        return json
    }
}
