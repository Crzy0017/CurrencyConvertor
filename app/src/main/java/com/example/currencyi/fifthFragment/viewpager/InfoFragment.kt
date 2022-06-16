package com.example.currencyi.fifthFragment.viewpager

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.currencyi.R

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val share: Button = view.findViewById(R.id.share)
        share.setOnClickListener() {
            val shareInt = Intent(Intent.ACTION_SEND)
            shareInt.type = "text/plain"
            shareInt.putExtra(Intent.EXTRA_TEXT, "full-name: ${getString(R.string.fullname)} " +
                    "gmail: ${getString(R.string.gmail)}  telephone-number: 8-777-555-1188")
            startActivity(Intent.createChooser(shareInt, "share"))
        }

        val gmail: Button = view.findViewById(R.id.mail)
        gmail.setOnClickListener() {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            startActivity(Intent.createChooser(emailIntent, "Gmail"))
        }

        val call: Button = view.findViewById(R.id.call)
        call.setOnClickListener() {
            val callIntent = Intent(Intent.ACTION_DIAL)
            startActivity(callIntent)
        }

        val cam: Button = view.findViewById(R.id.cam)
        cam.setOnClickListener() {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(cameraIntent)
        }
    }

}
