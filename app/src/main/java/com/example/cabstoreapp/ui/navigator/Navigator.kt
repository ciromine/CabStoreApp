package com.example.cabstoreapp.ui.navigator

import androidx.navigation.*
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.ui.checkout.CheckoutFragmentDirections
import com.example.cabstoreapp.ui.detail.DetailFragmentDirections
import com.example.cabstoreapp.ui.productlist.ProductListFragmentDirections
import com.example.cabstoreapp.ui.success.SuccessFragmentDirections
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun goToProductDetail(navController: NavController, domainRecipe: DomainProduct) {
        val direction =
            ProductListFragmentDirections.actionListFragmentToDetailFragment(domainRecipe)
        safeNavigation(navController, direction)
    }

    fun goToCheckoutDetail(navController: NavController) {
        val direction =
            ProductListFragmentDirections.actionListProductFragmentToCheckoutFragment()
        safeNavigation(navController, direction)
    }

    fun backtoProductListFromDetail(navController: NavController) {
        val direction =
            DetailFragmentDirections.actionDetailFragmentToProductListFragment()
        safeNavigation(navController, direction)
    }

    fun backtoProductListFromCheckout(navController: NavController) {
        val direction =
            CheckoutFragmentDirections.actionCheckoutFragmentToProductListFragment()
        safeNavigation(navController, direction)
    }

    fun goToSuccessFragment(navController: NavController) {
        val direction =
            CheckoutFragmentDirections.actionCheckoutFragmentToSuccessFragment()
        safeNavigation(navController, direction)
    }

    fun backtoProductListFromSuccess(navController: NavController) {
        val direction =
            SuccessFragmentDirections.actionSuccessFragmentToProductListFragment()
        safeNavigation(navController, direction)
    }

    private fun safeNavigation(navController: NavController, direction: NavDirections) {
        navController.currentDestination?.getAction(direction.actionId)
            ?.let { navController.navigate(direction) }
    }
}
