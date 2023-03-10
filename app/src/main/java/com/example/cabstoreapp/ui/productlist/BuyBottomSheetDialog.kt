package com.example.cabstoreapp.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cabstoreapp.databinding.BottomSheetDialogLayoutBinding
import com.example.cabstoreapp.ui.navigator.Navigator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class BuyBottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDialogLayoutBinding

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            navigator.goToCheckoutDetail(it.rootView)
        }
    }
}