package ucsal.br.api.management_service.controller;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ucsal.br.api.management_service.service.DBService;

@Controller
public class DBController {
    private final DBService dbService;
    public DBController(DBService dbService) {
        this.dbService = dbService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public String populateTheDataBase(){
        boolean success = dbService.populateTheDatabase();
        if (success) {
            return "✅ DataBase successfully populated!";
        } else {
            return "⚠️ Popular database failure.";
        }
    }
}
