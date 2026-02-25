package com.example.apiappactividad.data.BD
import com.example.apiappactividad.data.model.Result


fun Result.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        img = img,
        gender = gender,
        age = age,
        status = status,
        birthplace = birthplace,
        isFavorite = favorites
    )
}