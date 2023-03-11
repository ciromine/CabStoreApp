package com.example.cabstoreapp.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cabstoreapp.core.ShopCart
import com.example.cabstoreapp.databinding.FragmentCheckoutBinding
import com.example.cabstoreapp.ui.navigator.Navigator
import com.example.cabstoreapp.utils.CalculationUtils
import com.example.cabstoreapp.utils.TextUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class CheckoutFragment : Fragment() {
    var binding: FragmentCheckoutBinding? = null

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productList = ShopCart.getProductsFromCart()
        binding?.tvDetail?.text = TextUtils.generateDetailText(productList)
        binding?.tvTotalValue?.text = CalculationUtils.calculateTotalPrice(productList).toString()
        binding?.btCleanCheckout?.setOnClickListener {
            ShopCart.cleanCart()
            navigator.backtoProductListFromCheckout(Navigation.findNavController(view))
        }
        binding?.btBuy?.setOnClickListener {
            ShopCart.cleanCart()
            navigator.goToSuccessFragment(Navigation.findNavController(view))
        }
    }
}
