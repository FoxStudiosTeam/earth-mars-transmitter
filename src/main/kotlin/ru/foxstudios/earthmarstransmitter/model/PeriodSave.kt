package ru.foxstudios.earthmarstransmitter.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "schedule")
data class PeriodSave(@Id var uuid:String = "", var speed:Double = 0.0, var fromD:String = "", var toD:String = "", var sended:Boolean = false)
