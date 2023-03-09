package com.example.cabstoreapp.ui.navigator

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.ui.productlist.ProductListFragmentDirections
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun goToProductDetail(view: View, domainRecipe: DomainProduct) {
        val direction =
            ProductListFragmentDirections.actionListFragmentToDetailFragment(domainRecipe)
        safeNavigation(view, direction)
    }

    private fun safeNavigation(view: View, direction: NavDirections) {
        view.findNavController().currentDestination?.getAction(direction.actionId)
            ?.let { view.findNavController().navigate(direction) }
    }
}
