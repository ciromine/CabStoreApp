package com.example.cabstoreapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cabstoreapp.R
import com.example.cabstoreapp.core.ShopCart
import com.example.cabstoreapp.databinding.FragmentDetailBinding
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.ui.navigator.Navigator
import com.example.cabstoreapp.utils.show
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

    private var currentQuantityValue = 0

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
            tvPriceValue.text = "$${product?.price}€"

            if (product?.code == VOUCHER) {
                tvPromoText.text = getString(R.string.tv_voucher)
                cardView.show()
            } else if (product?.code == TSHIRT) {
                tvPromoText.text = getString(R.string.tv_tshirt)
                cardView.show()
            }

            val values =
                arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
            numberQuantity.maxValue = values.size - 1
            numberQuantity.displayedValues = values
            numberQuantity.wrapSelectorWheel = true

            btBuy.setOnClickListener {
                product?.let { itProduct ->
                    ShopCart.addProductToCart(itProduct, numberQuantity.value+1)
                    navigator.backtoProductListFromDetail(Navigation.findNavController(view))
                }
            }
        }
    }

    companion object {
        const val VOUCHER = "VOUCHER"
        const val TSHIRT = "TSHIRT"
    }
}
