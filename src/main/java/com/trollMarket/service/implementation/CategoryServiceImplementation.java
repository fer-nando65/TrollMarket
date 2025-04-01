package com.trollMarket.service.implementation;

import com.trollMarket.dto.Option.OptionDTO;
import com.trollMarket.repository.CategoryRepository;
import com.trollMarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OptionDTO> get(Integer id){
        var categories = this.repository.get();
        List<OptionDTO> optionCategory = new LinkedList<>();
        for(var cat : categories){
            boolean selected = false;
            if(cat.getCategoryId() == id){
                selected = true;
            }
            var opt = new OptionDTO(cat.getCategoryName(), Integer.toString(cat.getCategoryId()), selected);
            optionCategory.add(opt);
        }
        return optionCategory;
    }
}
