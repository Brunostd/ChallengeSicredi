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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.challengentconsult.R
import com.example.challengentconsult.databinding.FragmentSelectedEventBinding
import com.squareup.picasso.Picasso

class SelectedEventFragment : Fragment() {
    private var _binding: FragmentSelectedEventBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: SelectedEventFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedEventBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListener()

        return view
    }

    private fun setView() {
        binding.textTitleSelectedEvent.text = args.selectEvent.title
        Picasso.get().load(args.selectEvent.image).into(binding.imageSelectedEvent)
        if (binding.imageSelectedEvent.drawable == null){
            binding.imageSelectedEvent.setImageResource(R.drawable.erro)
        }
        binding.textDescriptionSelectedEvent.text = args.selectEvent.description
        binding.textPriceSelectedEvent.text = args.selectEvent.price.toString()
    }

    private fun setListener() {
        binding.backSelected.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.textRealizarCheckIn.setOnClickListener {
            val action = SelectedEventFragmentDirections.actionSelectedEventFragmentToCheckInFragment(args.selectEvent)
            findNavController().navigate(action)
        }
        binding.textSelectedCompartilhar.setOnClickListener {
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