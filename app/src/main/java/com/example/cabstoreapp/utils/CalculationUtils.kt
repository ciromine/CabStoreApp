package com.example.cabstoreapp.utils

import com.example.cabstoreapp.domain.model.DomainProduct

class CalculationUtils {
    companion object {
        fun calculateTotalPrice(products: MutableList<DomainProduct>): Double {
            var total = 0.0
            var countVouchers = 0
            var countTshirt = 0
            var valueVoucher = 0.0
            var valueTshirt = 0.0
            products.forEach {
                if (it.code == VOUCHER) {
                    countVouchers++
                    valueVoucher = it.price
                } else if (it.code == TSHIRT) {
                    countTshirt++
                    valueTshirt = it.price
                }
                total += it.price
            }
            if (countVouchers >= 2) {
                total -= ((countVouchers / 2) * valueVoucher)
            }
            if (countTshirt > 3) {
                total = total - (valueTshirt * countTshirt) + (19.00 * countTshirt)
            }
            return total
        }

        const val VOUCHER = "VOUCHER"
        const val TSHIRT = "TSHIRT"
    }
}