package br.com.application.mapper.custom

import br.com.application.data.vo.v1.PersonVO
import br.com.application.model.Person
import org.springframework.stereotype.Service


@Service
class PersonMapper {

    fun mapEntityToVo(person: Person): PersonVO {
        val vo = PersonVO()
        vo.key = person.id
        vo.address = person.address
        vo.firstName = person.firstName
        vo.lastName= person.lastName
        vo.gender = person.gender
        return vo

    }
}