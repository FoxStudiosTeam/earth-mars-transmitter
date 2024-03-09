package ru.foxstudios.earthmarstransmitter.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import reactor.core.publisher.Mono
import reactor.netty.udp.UdpClient
import ru.foxstudios.earthmarstransmitter.repositories.IDataRepository
@Configuration
@EnableScheduling
class ScheduleConfig(@Autowired var repository : IDataRepository) {
    @Scheduled(fixedDelay = 1000)
    fun sendDataScheduled(){
        val logger = LoggerFactory.getLogger(this.javaClass)
        val list = repository.findAllBySended(false)
        val mapper = ObjectMapper().registerKotlinModule()
        for (elem in list){
            val text = mapper.writeValueAsString(elem)
            val client = UdpClient.create().port(25578).host("host.docker.internal").wiretap(true).connectNow()
            client.outbound().sendString(Mono.just(text)).then().subscribe()
            client.inbound().receive().asString().doOnNext{next ->
                if(next == "ok"){
                    logger.info("send")
                    elem.sended = true
                    repository.save(elem)
                }
            }.then().subscribe()
        }
    }
}
