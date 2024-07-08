package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Services.DiscountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/lafresca/discount")
@AllArgsConstructor
@Tag(name = "Discount Controller")
public class DiscountController {
    private final DiscountService discountService;

}
