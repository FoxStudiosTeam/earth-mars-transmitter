package ru.foxstudios.earthmarstransmitter.service

import lombok.extern.slf4j.Slf4j
import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import reactor.netty.udp.UdpClient
import ru.foxstudios.earthmarstransmitter.model.DataModel
import ru.foxstudios.earthmarstransmitter.model.PeriodModel
import ru.foxstudios.earthmarstransmitter.model.PeriodSave
import ru.foxstudios.earthmarstransmitter.repositories.IDataRepository
import java.io.File
import java.nio.charset.StandardCharsets
import java.util.*

@Service
@Slf4j
class ScheduleService(var repository: IDataRepository) {

    fun sendScheduleToMars(data:List<PeriodModel>) {
        val logger = LoggerFactory.getLogger(this.javaClass)
        for(elem in data){
            repository.save(PeriodSave(UUID.randomUUID().toString(),elem.speed,elem.from,elem.to,false))
        }
        logger.info("save!")
    }

    fun parseMultipart(model: DataModel): String {
        val uuid = UUID.randomUUID()
        val file = File("tmp/$uuid.json")
        FileUtils.touch(file)
        FileUtils.writeByteArrayToFile(file, model.data.bytes)
        val text = file.readText(StandardCharsets.UTF_8)
        FileUtils.delete(file)
        return text
    }
}
