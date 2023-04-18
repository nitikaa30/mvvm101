package com.example.mvvm.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.adapter.MyAdapter
import com.example.mvvm.databinding.FragmentHomeBinding
import com.example.mvvm.model.details.DataX
import com.example.mvvm.model.details.Details
import com.example.mvvm.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : Fragment() {
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        binding?.recycle?.layoutManager= LinearLayoutManager(context)
        val token=getToken()
        Log.d("fetche1", getToken().toString())



        Retrofit.apiInterface.getInfo("Bearer $token").enqueue(object : Callback<Details> {
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                Toast.makeText(context,getToken(), Toast.LENGTH_LONG).show()
                Log.d("yo","$token")
                val list: ArrayList<DataX>?=response.body()?.data
                val id=list?.let{ arrayOfNulls<String>(it.size) }
                val createdat = list?.let { arrayOfNulls<String>(it.size) }
                val email = list?.let { arrayOfNulls<String>(it.size) }
                val location = list?.let { arrayOfNulls<String>(it.size) }
                val name = list?.let { arrayOfNulls<String>(it.size) }
                val profilepicture = list?.let { arrayOfNulls<String>(it.size) }

                if (list != null) {
                    for (i in list.indices){
                        id?.set(i, list[i].id.toString())
                        createdat?.set(i, list[i].createdat)
                        email?.set(i, list[i].email)
                        location?.set(i, list[i].location)
                        name?.set(i, list[i].name)
                        profilepicture?.set(i, list[i].profilepicture)
                    }
                }
                val adapter= MyAdapter(email,name, imageList =profilepicture )
                binding?.recycle?.adapter=adapter

            }
            override fun onFailure(call: Call<Details>, t: Throwable) {
                Toast.makeText(context,"nooo", Toast.LENGTH_LONG).show()

            }


        })
        return binding?.root

    }

    private fun getToken() : String? {
        val sharedPreferences = activity?.getSharedPreferences("Token", Context.MODE_PRIVATE)
        return  sharedPreferences?.getString("Token", null)
    }

}