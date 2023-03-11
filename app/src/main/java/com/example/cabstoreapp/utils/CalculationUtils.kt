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
            total = subtractVoucherDiscount(total, countVouchers, valueVoucher)
            total = subtractTshirtDiscount(total, countTshirt, valueTshirt)
            return total
        }

        private fun subtractVoucherDiscount(
            total: Double,
            countVouchers: Int,
            valueVoucher: Double
        ): Double {
            var result = total
            if (countVouchers >= 2) {
                result -= if (countVouchers % 2 == 0) {
                    ((countVouchers / 2) * valueVoucher)
                } else {
                    (((countVouchers - 1) / 2) * valueVoucher)
                }
            }
            return result
        }

        private fun subtractTshirtDiscount(
            total: Double,
            countTshirt: Int,
            valueTshirt: Double
        ): Double {
            var result = total
            if (countTshirt >= 3) {
                result = result - (valueTshirt * countTshirt) + (TSHIRT_PROMO_PRICE * countTshirt)
            }
            return result
        }

        const val VOUCHER = "VOUCHER"
        const val TSHIRT = "TSHIRT"
        const val TSHIRT_PROMO_PRICE = 19.00
    }
}