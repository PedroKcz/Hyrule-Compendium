package com.hyrule.features.category.entries.domain

import com.hyrule.features.category.entries.domain.entity.Entry

object EntryFixtures {
    val dummyEntries = listOf(
        Entry("Master Sword", "", listOf("a", "b")),
        Entry("Bow Of Light", "a", listOf("a")),
        Entry("Sand Seal", "b", listOf()),
    )
}
