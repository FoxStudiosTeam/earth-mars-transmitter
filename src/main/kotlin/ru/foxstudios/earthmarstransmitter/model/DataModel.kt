package ru.foxstudios.earthmarstransmitter.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.web.multipart.MultipartFile

data class DataModel (var data : MultipartFile)
