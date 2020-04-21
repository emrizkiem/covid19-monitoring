package dev.emrizkiem.covid19.data.model.global

data class CaseUpdate(var mainData: DataResponse?, var locations: Array<Location>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CaseUpdate

        if (mainData != other.mainData) return false
        if (!locations.contentEquals(other.locations)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mainData?.hashCode() ?: 0
        result = 31 * result + locations.contentHashCode()
        return result
    }
}