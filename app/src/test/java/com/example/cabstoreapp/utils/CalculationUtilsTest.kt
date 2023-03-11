package com.example.cabstoreapp.utils

import com.example.cabstoreapp.domain.model.DomainProduct
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Test

internal class CalculationUtilsTest {

    @Test
    fun `given DomainProduct List, then return correct Total value test 1`() {
        assertEquals(
            32.50,
            CalculationUtils.calculateTotalPrice(generateMockProducts1().toMutableList())
        )
    }

    @Test
    fun `given DomainProduct List, then return correct Total value test 2`() {
        assertEquals(
            25.00,
            CalculationUtils.calculateTotalPrice(generateMockProducts2().toMutableList())
        )
    }

    @Test
    fun `given DomainProduct List, then return correct Total value test 3`() {
        assertEquals(
            81.00,
            CalculationUtils.calculateTotalPrice(generateMockProducts3().toMutableList())
        )
    }

    @Test
    fun `given DomainProduct List, then return correct Total value test 4`() {
        assertEquals(
            74.5,
            CalculationUtils.calculateTotalPrice(generateMockProducts4().toMutableList())
        )
    }

    @Test
    fun `given DomainProduct List, then return correct wrong Total value`() {
        assertFalse(
            81.00 ==
                    CalculationUtils.calculateTotalPrice(generateMockProducts4().toMutableList())
        )
    }

    fun generateMockProducts1(): List<DomainProduct> {
        return listOf(
            DomainProduct(
                "VOUCHER", "Cabify Voucher", 5.00
            ),
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            ),
            DomainProduct(
                "MUG", "Cabify Coffee Mug", 7.50
            )
        )
    }

    fun generateMockProducts2(): List<DomainProduct> {
        return listOf(
            DomainProduct(
                "VOUCHER", "Cabify Voucher", 5.00
            ),
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            ),
            DomainProduct(
                "VOUCHER", "Cabify Voucher", 5.00
            )
        )
    }

    fun generateMockProducts3(): List<DomainProduct> {
        return listOf(
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            ),
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            ),
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            ),
            DomainProduct(
                "VOUCHER", "Cabify Voucher", 5.00
            ),
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            )
        )
    }

    fun generateMockProducts4(): List<DomainProduct> {
        return listOf(
            DomainProduct(
                "VOUCHER", "Cabify Voucher", 5.00
            ),
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            ),
            DomainProduct(
                "VOUCHER", "Cabify Voucher", 5.00
            ),
            DomainProduct(
                "VOUCHER", "Cabify Voucher", 5.00
            ),
            DomainProduct(
                "MUG", "Cabify Coffee Mug", 7.50
            ),
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            ),
            DomainProduct(
                "TSHIRT", "Cabify T-Shirt", 20.00
            )
        )
    }
}