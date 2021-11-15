package com.springweb.mvc.controller

import com.springweb.mvc.model.entity.AddressBookModel
import com.springweb.mvc.service.AddressBookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController {
    private val addressBookService: AddressBookService? = null

    @get:GetMapping("/list")
    val addresses: ResponseEntity<List<AddressBookModel?>?>
        get() = ResponseEntity.ok(addressBookService!!.allAddress())

    @GetMapping("/{id}/view")
    fun getAddress(@PathVariable id: Int?): ResponseEntity<AddressBookModel> {
        val addressBookModel = addressBookService!!.findOne(id)
        return if (addressBookModel!!.isPresent) ResponseEntity.ok(addressBookModel.get()) else ResponseEntity.notFound()
            .build()
    }

    @PostMapping("/{id}/edit")
    fun editAddress(
        @PathVariable id: Int,
        @RequestBody addressBookModel: AddressBookModel
    ): ResponseEntity<AddressBookModel?> {
        if (id != addressBookModel.id) {
            return ResponseEntity.badRequest().build()
        }
        return if (!addressBookService!!.isExist(id)) {
            ResponseEntity.notFound().build()
        } else ResponseEntity.ok(addressBookService.editAddress(addressBookModel))
    }

    @GetMapping("/{id}/delete")
    fun deleteAddress(@PathVariable id: Int?): ResponseEntity<Void> {
        if (!addressBookService!!.isExist(id)) {
            return ResponseEntity.notFound().build()
        }
        addressBookService.deleteAddress(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/add")
    fun addAddress(@RequestBody addressBookModel: AddressBookModel?): ResponseEntity<AddressBookModel?> {
        return ResponseEntity.ok(addressBookService!!.addNewEntry(addressBookModel))
    }
}