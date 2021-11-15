package com.springweb.mvc.controller

import com.springweb.mvc.model.entity.AddressBookModel
import com.springweb.mvc.service.AddressBookService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/app")
class MvcController {
    private val addressBookService: AddressBookService? = null
    @GetMapping("/list")
    fun getAddresses(model: Model): String {
        model.addAttribute("addresses", addressBookService!!.allAddress())
        return "list"
    }

    @GetMapping("/{id}/view")
    fun getAddress(@PathVariable id: Int?, model: Model): String {
        val addressBookModel = addressBookService!!.findOne(id)
        if (addressBookModel==null) {
            return "not_found"
        }
        model.addAttribute("addressBook", addressBookModel.get())
        return "view"
    }

    @GetMapping("/{id}/edit")
    fun editAddress(@PathVariable id: Int?, model: Model): String {
        val addressBookModel = addressBookService!!.findOne(id)
        if (addressBookModel==null) {
            return "not_found"
        }
        model.addAttribute("addressBook", addressBookModel.get())
        return "edit"
    }

    @PostMapping(value = ["/{id}/edit"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun editAddress(addressBookModel: AddressBookModel, model: Model): String {
        if (!addressBookService!!.isExist(addressBookModel.id)) {
            return "not_found"
        }
        model.addAttribute("addressBook", addressBookService.editAddress(addressBookModel))
        return "view"
    }

    @GetMapping("/{id}/delete")
    fun deleteAddress(@PathVariable id: Int?): String {
        if (!addressBookService!!.isExist(id)) {
            return "not_found"
        }
        addressBookService.deleteAddress(id)
        return "deleted"
    }

    @GetMapping("/add")
    fun addAddress(): String {
        return "add"
    }

    @PostMapping(value = ["/add"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun addAddress(addressBookModel: AddressBookModel?, model: Model): String {
        model.addAttribute("addressBook", addressBookService!!.addNewEntry(addressBookModel))
        return "view"
    }
}