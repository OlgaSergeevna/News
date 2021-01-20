package com.sylko.news.utils

import java.time.LocalDate

fun convertPublishedAtToDate(publishedAt: String) =
    LocalDate.parse(publishedAt.substringBefore("T")).toString()
