package org.lafresca.lafrescabackend.Controllers;

import org.lafresca.lafrescabackend.Models.Menu;
import org.lafresca.lafrescabackend.Services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/lafresca/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Add new menu item
    @PostMapping
    public String addNewMenu(@RequestBody Menu menu) {
        return menuService.addNewMenu(menu);
    }

    // Retrieve all menu items
    @GetMapping
    public List<Menu> getMenus(){
        return menuService.getMenus();
    }

    // Search menu item
    @GetMapping(path = "{id}")
    public Optional<Menu> getMenu(@PathVariable("id") String id){
        return menuService.getMenu(id);
    }

    // Delete menu item
    @DeleteMapping(path = "{id}")
    public void deleteMenu(@PathVariable("id") String id){
        menuService.deleteMenu(id);
    }

    // Update menu item
    @PutMapping(path = "{id}")
    public void updateMenu(@PathVariable("id") String id, @RequestBody Menu menu){
        menuService.updateMenu(id, menu);
    }
}
