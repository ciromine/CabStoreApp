package com.example.cabstoreapp.ui.navigator

import android.view.View
import androidx.navigation.*
import com.example.cabstoreapp.R
import com.example.cabstoreapp.domain.model.DomainProduct
import com.example.cabstoreapp.ui.checkout.CheckoutFragmentDirections
import com.example.cabstoreapp.ui.detail.DetailFragment
import com.example.cabstoreapp.ui.detail.DetailFragmentDirections
import com.example.cabstoreapp.ui.productlist.ProductListFragmentDirections
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

    private fun safeNavigation(navController: NavController, direction: NavDirections) {
        navController.currentDestination?.getAction(direction.actionId)
            ?.let { navController.navigate(direction) }
    }

    /*fun goToBackView(view: View) {
        val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(R.id.detailFragment, true).build()
        Navigation.findNavController(view).navigate(R.id.productListFragment,null, navOptions)
    }*/
}
