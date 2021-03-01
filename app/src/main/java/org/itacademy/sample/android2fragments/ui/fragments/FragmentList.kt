package org.itacademy.sample.android2fragments.ui.fragments

import android.content.ContentResolver
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_first.*
import org.itacademy.sample.android2fragments.R
import org.itacademy.sample.android2fragments.adaptors.NotebooksAdaptor
import org.itacademy.sample.android2fragments.data.NotebookItem

class FragmentList : Fragment(R.layout.fragment_first) {

    val ADD_NOTEBOOK_CODE = 1
    val TAG = "FirstFragment"

    var itemsList = mutableListOf<NotebookItem>()
    lateinit var adapter: NotebooksAdaptor
    private lateinit var fContext: Context
    private lateinit var fContentResolver: ContentResolver

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fContext = context
        this.fContentResolver = fContext.contentResolver
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsList = mutableListOf(
            NotebookItem(
                ContextCompat.getDrawable(fContext, R.drawable.asus_tuf),
                "Asus TUF Gaming F15",
                "15.6, 1920 x 1080",
                "Intel Core I5, 16Gb RAM, 512Gb SSD, 4Gb Video"
            ),
            NotebookItem(
                ContextCompat.getDrawable(fContext, R.drawable.honor_magic_book),
                "Honor Magic 14 2020 53010 VTY",
                "14.0, 1920 x 1080",
                "AMD Ryzen 5, 8Gb RAM, 512Gb SSD"
            ),
            NotebookItem(
                ContextCompat.getDrawable(fContext, R.drawable.apple_mac_book),
                "Apple Macbook Air 13 M1 2020",
                "13.3, 2560 x 1600",
                "Apple M1, 8Gb RAM, 256Gb SSD, M1 GPU Video"
            )
        )

        adapter = NotebooksAdaptor(itemsList)
        rvNotes.adapter = adapter
        rvNotes.layoutManager = LinearLayoutManager(fContext)

        btnAdd.setOnClickListener {
            val secondFragment = FragmentAdd()
            val bundle = Bundle()
            bundle.putString("EXTRA_model", "HP")
            secondFragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.let {
                transaction.replace(R.id.flFragment, secondFragment)
                transaction.commit()
            }
        }

        // If transfered from second fragment
        val bundle = arguments
        bundle?.let {
            val model = bundle.getString("EXTRA_model")
            val screen = bundle.getString("EXTRA_screen")
            val hardware = bundle.getString("EXTRA_hardware")
            val imgUri = bundle.getString("EXTRA_image")
            val drawable = imgUri?.let {uri ->
                uriToDrawable(uri)
            }
            val newNotebook = NotebookItem(drawable, model!!, screen, hardware)
            itemsList.add(newNotebook)
            adapter.notifyItemInserted(itemsList.size-1)
        }
    }

    private fun uriToDrawable(uri: String): Drawable {
        val inputStream = fContentResolver.openInputStream(Uri.parse(uri))
        return Drawable.createFromStream(inputStream, uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}