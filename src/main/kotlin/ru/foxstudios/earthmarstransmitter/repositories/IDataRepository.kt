package ru.foxstudios.earthmarstransmitter.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.foxstudios.earthmarstransmitter.model.PeriodSave

@Repository
interface IDataRepository : JpaRepository<PeriodSave,String>{
    fun findAllBySended(sended:Boolean) : List<PeriodSave>
}
