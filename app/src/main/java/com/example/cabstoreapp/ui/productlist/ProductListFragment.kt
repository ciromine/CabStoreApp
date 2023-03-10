package com.example.cabstoreapp.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.cabstoreapp.R
import com.example.cabstoreapp.core.ShopCart
import com.example.cabstoreapp.core.mvi.MviUi
import com.example.cabstoreapp.core.mvi.MviUiEffect
import com.example.cabstoreapp.databinding.FragmentProductListBinding
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.presentation.productlist.ProductListUIntent
import com.example.cabstoreapp.presentation.productlist.ProductListUIntent.InitialUIntent
import com.example.cabstoreapp.presentation.productlist.ProductListUIntent.SeeDetailUIntent
import com.example.cabstoreapp.presentation.productlist.ProductListUiEffect
import com.example.cabstoreapp.presentation.productlist.ProductListUiState
import com.example.cabstoreapp.presentation.productlist.ProductListViewModel
import com.example.cabstoreapp.ui.navigator.Navigator
import com.example.cabstoreapp.ui.productlist.adapter.ProductListAdapter
import com.example.cabstoreapp.utils.hide
import com.example.cabstoreapp.utils.show
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class ProductListFragment : Fragment(), MviUi<ProductListUIntent, ProductListUiState>,
    MviUiEffect<ProductListUiEffect> {

    var binding: FragmentProductListBinding? = null

    private val viewModel by viewModels<ProductListViewModel>()

    private val userIntents: MutableSharedFlow<ProductListUIntent> = MutableSharedFlow()

    private var adapter: ProductListAdapter? = null

    private var ProductOriginalList: MutableList<DomainProduct>? = null

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupCollectors()
        statesProcessIntents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentProductListBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding?.btViewShopcart?.setOnClickListener {
            navigator.goToCheckoutDetail(Navigation.findNavController(view))
        }
    }

    private fun statesProcessIntents() {
        viewModel.run {
            viewModel.processUserIntents(userIntents())
        }
    }

    private fun initialUserIntent(): Flow<ProductListUIntent> = flow {
        emit(InitialUIntent)
    }

    private fun setupCollectors() {
        with(viewModel) {
            uiStates().onEach { renderUiStates(it) }.launchIn(lifecycleScope)
            uiEffect().onEach { handleEffect(it) }.launchIn(lifecycleScope)
        }
    }

    override fun userIntents(): Flow<ProductListUIntent> = merge(
        initialUserIntent(),
        userIntents.asSharedFlow()
    )

    override fun renderUiStates(uiState: ProductListUiState) {
        when (uiState) {
            ProductListUiState.LoadingUiState -> showLoading()

            is ProductListUiState.SuccessUiState -> {
                hideLoading()
                showProductList(uiState.products)
            }

            ProductListUiState.ErrorUiState -> {
                hideLoading()
                showError()
            }
        }
    }

    private fun showLoading() {
        binding?.progressBar?.show()
    }

    private fun hideLoading() {
        binding?.progressBar?.hide()
    }

    private fun showError() {
        binding?.root?.let {
            val snackbar = Snackbar
                .make(
                    it,
                    getString(R.string.error_get_product_list),
                    Snackbar.LENGTH_LONG
                )
            snackbar.show()
        }
    }

    private fun showProductList(Products: List<DomainProduct>) {
        ProductOriginalList = Products.toMutableList()
        adapter = ProductListAdapter(Products.toMutableList()) {
            onItemCharacterTapped(it)
        }
        binding?.apply {
            mainRecycler.adapter = adapter
            mainRecycler.show()
        }
    }

    override fun handleEffect(uiEffect: ProductListUiEffect) {
        when (uiEffect) {
            is ProductListUiEffect.NavigateToProductDetailUiEffect -> goToProductDetails(uiEffect.domainProduct)
        }
    }

    private fun onItemCharacterTapped(domainProduct: DomainProduct) {
        viewLifecycleOwner.lifecycleScope.launch {
            userIntents.emit(SeeDetailUIntent(domainProduct))
        }
    }

    private fun goToProductDetails(domainProduct: DomainProduct) {
        binding?.let {
            navigator.goToProductDetail(Navigation.findNavController(it.root), domainProduct)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        val listOfProducts = ShopCart.getProductsFromCart()
        if (listOfProducts.size > 0) {
            showSnackMessage()
            binding?.btViewShopcart?.isEnabled = true
        } else {
            binding?.btViewShopcart?.isEnabled = false
        }
    }

    private fun showSnackMessage() {
        binding?.root?.let {
            val snackbar = Snackbar
                .make(
                    it,
                    getString(R.string.tv_label_checkout),
                    Snackbar.LENGTH_LONG,
                )
            snackbar.show()
        }
    }

    companion object {
        const val BUY_BOTTOM_SHEET_DIALOG = "BUY_BOTTOM_SHEET_DIALOG"
    }
}
