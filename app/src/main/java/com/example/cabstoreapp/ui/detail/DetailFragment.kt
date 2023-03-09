package com.example.cabstoreapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cabstoreapp.databinding.FragmentDetailBinding
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.ui.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject


@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class DetailFragment : Fragment() {

    var binding: FragmentDetailBinding? = null

    var product: DomainProduct? = null

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.let { DetailFragmentArgs.fromBundle(it).domainProduct }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentDetailBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            tvNameValue.text =
                product?.name
            tvPriceValue.text = "$${product?.price}"

            val values =
                arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
            numberQunatity.maxValue = values.size - 1
            numberQunatity.displayedValues = values
            numberQunatity.wrapSelectorWheel = true
            numberQunatity.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            }

            btBuy.setOnClickListener {
                product?.let {
                    //goToCharacterEdit(it)
                }
            }
        }
    }

    /*private fun goToCharacterEdit(domainRecipe: DomainProduct) {
        binding?.let {
            navigator.goToRecipeMap(it.root, domainRecipe)
        }
    }*/
}
