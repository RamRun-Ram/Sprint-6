package com.springweb.mvc.controller

import com.springweb.mvc.model.AddressPerson
import com.springweb.mvc.service.AddressBookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/app")
class MvcController {
    @Autowired
    private lateinit var addressBookService: AddressBookService

    @GetMapping("/add")
    fun getAddress(): String {
        return "add"
    }

    @GetMapping("/list")
    fun getAllAddress(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) address: String?,
        model: Model,
    ): String {
        model.addAttribute("allAddress", addressBookService.getAllAddress())
        return "list"
    }

    @PostMapping("/add")
    fun addAddress(@ModelAttribute("address") addressPerson: AddressPerson, model: Model): String {
        addressBookService.addAddress(addressPerson)
        return "list"
    }

    @GetMapping("/{id}/view")
    fun getAddressId(@PathVariable id: Int, model: Model): String {
        model.addAttribute("address", addressBookService.getAddress(id))
        return "view"
    }


    @GetMapping("/{id}/edit")
    fun getAddressEdit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("address", addressBookService.getAddress(id))
        return "edit"
    }

    @PostMapping("/{id}/edit")
    fun postAddressEdit(@PathVariable id: Int, @ModelAttribute("address") addressPerson: AddressPerson): String {
        addressBookService.updateAddress(id, addressPerson)
        return "list"
    }

    @GetMapping("/{id}/delete")
    fun removeAddress(@PathVariable id: Int): String {
        addressBookService.removeAddress(id)
        return "list"
    }
}
