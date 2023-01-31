package net.javaguides.springboot.web;


import net.javaguides.springboot.model.*;
import net.javaguides.springboot.repository.BicycleRepository;
import net.javaguides.springboot.repository.CartItemsRepository;
import net.javaguides.springboot.repository.CustomCartItemRepository;
import net.javaguides.springboot.repository.UserRepository;

import net.javaguides.springboot.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    @Autowired
    CartItemsRepository cartItemsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BicycleRepository bicycleRepository;

    @Autowired
    private CustomCartItemRepository customCartItemRepository;

    @Autowired
    private FilesStorageService storageService;

    @GetMapping({"/", "/index"})
    public String showHomepage() {
        return "index";
    }

    @GetMapping({"/account"})
    public String showAccountPage() {
        return "account";
    }

    @GetMapping("/user")
    public String showUserHome() {
        return "user/index";
    }

    @GetMapping("/products")
    public ModelAndView showProductPage(Model model) {

        Bicycle bicycle = new Bicycle();
        model.addAttribute("bicycle", bicycle);
        ModelAndView mav = new ModelAndView("user/pages/product_page");
        mav.addObject("bicycles", bicycleRepository.findAll());
        return mav;
    }


    @GetMapping("/login")
    public String login() {
        return "account";
    }


    @GetMapping("/admin")
    public ModelAndView getAdminDashboard(Model model) {
        Bicycle bicycle = new Bicycle();
        model.addAttribute("bicycle", bicycle);
        ModelAndView mav = new ModelAndView("admin/admin_dashboard");
        mav.addObject("bicycles", bicycleRepository.findAll());
        return mav;
    }

    @GetMapping("/admin_bicycle_view")
    public ModelAndView getAdminBicycleView(Model model) {
        Bicycle bicycle = new Bicycle();
        model.addAttribute("bicycle", bicycle);
        ModelAndView mav = new ModelAndView("admin/views/admin_bicycle_view");
        mav.addObject("bicycles", bicycleRepository.findAll());
        return mav;
    }

    @PostMapping("/admin_reg")
    public String submitForm(@ModelAttribute("user") User user) {
        System.out.println("User : " + user.getFirstName());
        return "admin/admin_dash";
    }



    // CRUD Operation mapping on the Admin Page;

    @RequestMapping(value = "delete_bicycle", method = RequestMethod.GET)
    public ModelAndView handleDeleteBicycle(@RequestParam(name = "bicycle_id") String bicycle_id,@RequestParam(name = "file_name") String file_name,Model model) {


        String[] filename = file_name.split("/");

        storageService.delete(filename[1]);
        bicycleRepository.deleteById(Integer.parseInt(bicycle_id));

        Bicycle bicycle = new Bicycle();
        model.addAttribute("bicycle", bicycle);
        ModelAndView mav = new ModelAndView("admin/views/admin_bicycle_view");
        mav.addObject("bicycles", bicycleRepository.findAll());
        return mav;
    }

    @GetMapping("/cart")
    public ModelAndView showCartPage(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User testingUser = userRepository.findByEmail(username);
        CustomCartModel cartItems = new CustomCartModel();
        model.addAttribute("cartItem", cartItems);
        ModelAndView mav = new ModelAndView("/user/pages/cart_page");
        mav.addObject("cartItems", customCartItemRepository.findAllWithUserId(Long.parseLong(testingUser.getId().toString())));
        return mav;
    }

    @GetMapping("/addBicycle")
    public String showAddBicyclePage(){
        return "/admin/views/add_bicycle_page";
    }


}
