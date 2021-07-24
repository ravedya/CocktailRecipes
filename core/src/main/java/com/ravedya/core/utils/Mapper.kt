package com.ravedya.core.utils

interface Mapper <Entity, DomainModel> {

    fun mapFromEntity(entity: Entity?): DomainModel

    fun mapToEntity(domainModel: DomainModel?): Entity
}