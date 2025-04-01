package com.trollMarket.controller;

import com.trollMarket.dto.Merchandise.MerchandiseUpsertDTO;
import com.trollMarket.service.CategoryService;
import com.trollMarket.service.MerchandiseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("merchandise")
public class MerchandiseController {
    private final MerchandiseService service;
    private final CategoryService categoryService;

    @Autowired
    public MerchandiseController(MerchandiseService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }

    @GetMapping("index")
    public String index(String username, Model model){
        var gridMerchandise = this.service.get(username);
        model.addAttribute("gridMerchandise", gridMerchandise);
        return "merchandise/merchandise-index";
    }

    @GetMapping("upsert")
    public String upsert(@RequestParam String username,
                         @RequestParam(defaultValue = "0") Integer id,
                         Model model){
        var detail = this.service.getMerchandise(id, username);
        model.addAttribute("detailMerchandise", detail);
        model.addAttribute("listCategory", detail.getListCategory());
        model.addAttribute("usernameSeller",username);
        return "merchandise/merchandise-form";
    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("detailMerchandise") MerchandiseUpsertDTO dto,
                         BindingResult validation,
                         Model model){
        if(validation.hasErrors()){
            model.addAttribute("detailMerchandise", dto);
            model.addAttribute("listCategory", categoryService.get(dto.getCategoryId()));
            model.addAttribute("usernameSeller",dto.getUsername());
            return "merchandise/merchandise-form";
        }else {
            this.service.save(dto);
            return String.format("redirect:/merchandise/index?username=%s", dto.getUsername());
        }
    }

    @GetMapping("discontinue")
    public String discontinue(@RequestParam String username,
                              @RequestParam Integer id){
        this.service.setDiscontinue(id);
        return String.format("redirect:/merchandise/index?username=%s", username);
    }

    @GetMapping("delete")
    public String delete(@RequestParam String username,
                       @RequestParam Integer id){
        this.service.delete(id);
        return String.format("redirect:/merchandise/index?username=%s", username);
    }
}
