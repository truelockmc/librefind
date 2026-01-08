package com.jksalcedo.fossia.data.remote.firebase.dto

import com.google.firebase.firestore.PropertyName
import com.jksalcedo.fossia.domain.model.Alternative

/**
 * Data Transfer Object for Firestore "foss_solutions" collection
 * 
 * Firestore Schema:
 * - doc_id: Sanitized package name
 * - name: Human-readable name
 * - license: FOSS license type
 * - repo_url: Source code repository
 * - fdroid_id: F-Droid package ID
 * - votes: Map of vote categories to counts
 */
data class FossSolutionDto(
    @get:PropertyName("name") @set:PropertyName("name")
    var name: String = "",
    
    @get:PropertyName("license") @set:PropertyName("license")
    var license: String = "",
    
    @get:PropertyName("repo_url") @set:PropertyName("repo_url")
    var repoUrl: String = "",
    
    @get:PropertyName("fdroid_id") @set:PropertyName("fdroid_id")
    var fdroidId: String = "",
    
    @get:PropertyName("icon_url") @set:PropertyName("icon_url")
    var iconUrl: String? = null,
    
    @get:PropertyName("package_name") @set:PropertyName("package_name")
    var packageName: String = "",
    
    @get:PropertyName("description") @set:PropertyName("description")
    var description: String = "",
    
    @get:PropertyName("votes") @set:PropertyName("votes")
    var votes: Map<String, Int> = emptyMap()
) {
    /**
     * Convert DTO to domain model
     */
    fun toDomain(id: String): Alternative {
        return Alternative(
            id = id,
            name = name,
            packageName = packageName,
            license = license,
            repoUrl = repoUrl,
            fdroidId = fdroidId,
            iconUrl = iconUrl,
            privacyVotes = votes["privacy"] ?: 0,
            usabilityVotes = votes["usability"] ?: 0,
            description = description
        )
    }
}
