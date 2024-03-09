package ru.foxstudios.earthmarstransmitter.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.foxstudios.earthmarstransmitter.model.DataModel
import ru.foxstudios.earthmarstransmitter.model.PeriodModel
import ru.foxstudios.earthmarstransmitter.repositories.IDataRepository
import ru.foxstudios.earthmarstransmitter.service.ScheduleService

@RestController
class ScheduleController(@Autowired var repository: IDataRepository) {
    val service = ScheduleService(repository)
    @PostMapping("add")
    fun addSchedule(@ModelAttribute model: DataModel) : List<PeriodModel> {
        val text =  service.parseMultipart(model)
        val mapper = ObjectMapper().registerKotlinModule()
        val data: List<PeriodModel> = mapper.readValue(text, object : TypeReference<List<PeriodModel>>() {})
        service.sendScheduleToMars(data)
        return data
    }

}
