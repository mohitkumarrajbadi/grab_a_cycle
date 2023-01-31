package net.javaguides.springboot.web;

import net.javaguides.springboot.model.Bicycle;
import net.javaguides.springboot.repository.BicycleRepository;
import net.javaguides.springboot.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController {

    @Autowired
    FilesStorageService storageService;
    @Autowired
    private BicycleRepository bicycleRepository;

    @GetMapping("/images/new")
    public String newImage(Model model) {
        return "upload_form";
    }

    @PostMapping("/addBicycle")
    public ModelAndView addBicycle(Model model, @RequestParam("file") MultipartFile file, @ModelAttribute Bicycle bicycle) {
        String message = "";
        bicycle.setImage("images/"+file.getOriginalFilename());
        try {

            storageService.save(file);
            bicycleRepository.save(bicycle);

            message = "Added Data Successfully!!!";
            model.addAttribute("message", message);
        } catch (Exception e) {
            message = "Error Found on the way : " + e;
            model.addAttribute("message", message);
        }

        Bicycle newBicycle = new Bicycle();
        model.addAttribute("bicycle", newBicycle);
        ModelAndView mav = new ModelAndView("admin/views/admin_bicycle_view");
        mav.addObject("bicycles", bicycleRepository.findAll());
        return mav;
    }
}