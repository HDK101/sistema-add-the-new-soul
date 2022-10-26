package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.usecases.utils.AccessLevel;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Singleton;

public class LogoutEmployeeUseCase {

    public void logout () {
        if (Singleton.getInstance().getAccessLevel() == AccessLevel.RELEASED)
            Singleton.getInstance().setAccessLevel(AccessLevel.DENIED);
    }
}
