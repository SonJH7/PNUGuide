package com.pnu.pnuguide.data

object CourseData {
    fun loadStamps(): List<Stamp> {
        return List(24) { index ->
            Stamp(id = "spot${index + 1}", name = "Spot ${index + 1}")
        }
    }
}
