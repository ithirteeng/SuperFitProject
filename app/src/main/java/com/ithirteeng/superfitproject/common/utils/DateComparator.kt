package com.ithirteeng.superfitproject.common.utils

import android.annotation.SuppressLint
import com.ithirteeng.superfitproject.common.exercises.domain.entity.TrainingEntity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
val byDateOrderComparatorString =
    compareByDescending<String> {
        val format = SimpleDateFormat("dd.MM.yyyy")
        var convertedDate = Date()
        try {
            convertedDate = it.let { it1 -> format.parse(it1) } as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        convertedDate
    }

@SuppressLint("SimpleDateFormat")
val byDateOrderComparatorTrainingEntity =
    compareByDescending<TrainingEntity> {
        val format = SimpleDateFormat("dd.MM.yyyy")
        var convertedDate = Date()
        try {
            convertedDate = it.date.let { it1 -> format.parse(it1) } as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        convertedDate
    }