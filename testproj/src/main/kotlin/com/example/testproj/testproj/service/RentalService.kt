package com.example.testproj.testproj.service

import com.example.testproj.testproj.entity.Rental
import com.example.testproj.testproj.repository.RentalRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RentalService(private val rentalRepository: RentalRepository){
    fun getRental(): ResponseEntity<*> {
        val rental = rentalRepository.findAll()
        return ResponseEntity.ok(rental);
    }

    fun setRental(inRental : Rental): ResponseEntity<*>{
        var rental = Rental(inRental.id,inRental.userId,inRental.bookId, LocalDate.now(),null,null)
        val src = rentalRepository.save(rental)
        return ResponseEntity.ok(src)
    }

    
}