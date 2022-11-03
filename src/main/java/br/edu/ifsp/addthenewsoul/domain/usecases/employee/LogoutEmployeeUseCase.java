package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.usecases.utils.AccessLevel;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;

public class LogoutEmployeeUseCase {

    public void logout () {
        Session.logout();
    }
}
