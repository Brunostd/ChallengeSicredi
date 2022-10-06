package com.example.challengentconsult.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challengentconsult.R
import com.example.challengentconsult.databinding.FragmentSuccessCheckInBinding

class SuccessCheckInFragment : Fragment() {
    private var _binding: FragmentSuccessCheckInBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: SuccessCheckInFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessCheckInBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListener()

        return view
    }

    private fun setView() {
        binding.textIdComprovante.text      = args.checkInComprovante.id
        binding.textNameComprovante.text    = args.checkInComprovante.name
        binding.textEmailComprovante.text   = args.checkInComprovante.email
    }

    private fun setListener() {
        binding.backComprovante.setOnClickListener {
            findNavController().navigate(R.id.action_successCheckInFragment_to_homeFragment)
        }
        binding.buttonCompartilhar.setOnClickListener {
            screenShot(requireView())?.let { it1 -> share(it1) }
        }
    }

    private fun screenShot(view: View): Bitmap? {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun share(bitmap: Bitmap) {
        val pathofBmp = MediaStore.Images.Media.insertImage(
            requireContext().contentResolver,
            bitmap, "title", null
        )
        val uri: Uri = Uri.parse(pathofBmp)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Star App")
        shareIntent.putExtra(Intent.EXTRA_TEXT, "")
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        requireContext().startActivity(Intent.createChooser(shareIntent, "Check-In"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}