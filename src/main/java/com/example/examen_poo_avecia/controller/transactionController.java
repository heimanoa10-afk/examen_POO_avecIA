import com.example.examen_poo_avecia.model.transaction;
import com.example.examen_poo_avecia.model.transactionType;
import com.example.examen_poo_avecia.service.transactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class transactionController {

    private final transactionService transactionService;

    @GetMapping
    public List<transaction> getTransactions(
            @RequestParam(required = false) transactionType type
    ) {

        if (type == null) {
            return List.of();
        }

        return transactionService.gettransactionsByType(type);
    }
}
