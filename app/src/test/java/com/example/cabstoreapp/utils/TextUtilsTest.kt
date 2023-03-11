package com.example.cabstoreapp.utils

import com.example.cabstoreapp.domain.model.DomainProduct
import junit.framework.Assert.assertEquals
import org.junit.Test

internal class TextUtilsTest {

    @Test
    fun `given DomainProduct List, then return correct String`() {
        assertEquals("name\nname\nname", TextUtils.generateDetailText(generateMockProducts().toMutableList()))
    }

    fun generateMockProducts(): List<DomainProduct> {
        return listOf(
            DomainProduct(
                "code","name",1.0
            ),
            DomainProduct(
                "code","name",1.0
            ),
            DomainProduct(
                "code","name",1.0
            )
        )
    }
}