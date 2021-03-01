package org.itacademy.sample.android2fragments.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_second.*
import org.itacademy.sample.android2fragments.R

class FragmentAdd : Fragment(R.layout.fragment_second) {
    val TAG = "FragmentAdd"

    val SELECT_IMAGE_CODE = 2

    var imgUri: Uri? = null

    var model: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bundle = arguments
        bundle?.let {
            model = bundle.getString("EXTRA_model")
            Log.d(TAG, "Model: $model")
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNoteModel.setText(model)

        ivNoteImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_IMAGE_CODE)
        }

        btnAddNew.setOnClickListener {
            val firstFragment = FragmentList()
            val bundle = Bundle()
            bundle.putString("EXTRA_model", etNoteModel.text.toString())
            bundle.putString("EXTRA_screen", etNoteScreen.text.toString())
            bundle.putString("EXTRA_hardware", etNoteHardware.text.toString())
            bundle.putString("EXTRA_image", imgUri.toString())
            firstFragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.let {
                transaction.replace(R.id.flFragment, firstFragment)
                transaction.commit()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == AppCompatActivity.RESULT_OK && requestCode == SELECT_IMAGE_CODE) {
            imgUri = data?.data
            ivNoteImg.setImageURI(imgUri)
        }
    }
}