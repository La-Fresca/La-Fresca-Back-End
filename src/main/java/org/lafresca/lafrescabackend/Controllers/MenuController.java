package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.lafresca.lafrescabackend.Models.Menu;
import org.lafresca.lafrescabackend.Services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/lafresca/menu")
@Tag(name="Menu Controller")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Add new menu item
    @PostMapping
    @Operation(
            description = "Add new menu",
            summary = "Add new menu to the branch",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public String addNewMenu(@RequestBody Menu menu) {
        return menuService.addNewMenu(menu);
    }

    // Retrieve all menu items
    @GetMapping
    @Operation(
            description = "Get all menus",
            summary = "Retrieve all menus in the branch",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public List<Menu> getMenus(){
        return menuService.getMenus();
    }

    // Search menu item
    @GetMapping(path = "{id}")
    @Operation(
            description = "Search menu by id",
            summary = "Retrieve menus by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public Optional<Menu> getMenu(@PathVariable("id") String id){
        return menuService.getMenu(id);
    }

    // Delete menu item
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete menu by id",
            summary = "Delete menus by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public void deleteMenu(@PathVariable("id") String id){
        menuService.deleteMenu(id);
    }

    // Update menu item
    @PutMapping(path = "{id}")
    @Operation(
            description = "Update menu by id",
            summary = "Update menus by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public void updateMenu(@PathVariable("id") String id, @RequestBody Menu menu){
        menuService.updateMenu(id, menu);
    }
}
